package com.myedu.project.order.controller;

import java.util.List;

import com.myedu.common.utils.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
import com.myedu.project.order.domain.YunOrder;
import com.myedu.project.order.service.IYunOrderService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.web.page.TableDataInfo;

/**
 * 订单Controller
 * 
 * @author 梁少鹏
 * @date 2020-01-22
 */
@RestController
@RequestMapping("/order/order")
public class YunOrderController extends BaseController
{
    @Autowired
    private IYunOrderService yunOrderService;

    /**
     * 查询订单列表
     */
    @PreAuthorize("@ss.hasPermi('order:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunOrder yunOrder)
    {
        startPage();
        List<YunOrder> list = yunOrderService.selectYunOrderList(yunOrder);
        return getDataTable(list);
    }

    /**
     * 导出订单列表
     */
    @PreAuthorize("@ss.hasPermi('order:order:export')")
    @Log(title = "订单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunOrder yunOrder)
    {
        List<YunOrder> list = yunOrderService.selectYunOrderList(yunOrder);
        ExcelUtil<YunOrder> util = new ExcelUtil<YunOrder>(YunOrder.class);
        return util.exportExcel(list, "order");
    }

    /**
     * 获取订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('order:order:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yunOrderService.selectYunOrderById(id));
    }

    /**
     * 新增订单
     */
    @PreAuthorize("@ss.hasPermi('order:order:add')")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunOrder yunOrder)
    {
        return toAjax(yunOrderService.insertYunOrder(yunOrder));
    }

    /**
     * 修改订单
     */
    @PreAuthorize("@ss.hasPermi('order:order:edit')")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunOrder yunOrder)
    {
        return toAjax(yunOrderService.updateYunOrder(yunOrder));
    }

    /**
     * 删除订单
     */
    @PreAuthorize("@ss.hasPermi('order:order:remove')")
    @Log(title = "订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunOrderService.deleteYunOrderByIds(ids));
    }
}
