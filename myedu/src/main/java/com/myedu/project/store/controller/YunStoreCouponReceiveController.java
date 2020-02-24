package com.myedu.project.store.controller;

import java.util.List;
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
import com.myedu.project.store.domain.YunStoreCouponReceive;
import com.myedu.project.store.service.IYunStoreCouponReceiveService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.web.page.TableDataInfo;

/**
 * 优惠券领用Controller
 * 
 * @author 梁少鹏
 * @date 2020-02-24
 */
@RestController
@RequestMapping("/store/receive")
public class YunStoreCouponReceiveController extends BaseController
{
    @Autowired
    private IYunStoreCouponReceiveService yunStoreCouponReceiveService;

    /**
     * 查询优惠券领用列表
     */
    @PreAuthorize("@ss.hasPermi('store:receive:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunStoreCouponReceive yunStoreCouponReceive)
    {
        startPage();
        List<YunStoreCouponReceive> list = yunStoreCouponReceiveService.selectYunStoreCouponReceiveList(yunStoreCouponReceive);
        return getDataTable(list);
    }

    /**
     * 导出优惠券领用列表
     */
    @PreAuthorize("@ss.hasPermi('store:receive:export')")
    @Log(title = "优惠券领用", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunStoreCouponReceive yunStoreCouponReceive)
    {
        List<YunStoreCouponReceive> list = yunStoreCouponReceiveService.selectYunStoreCouponReceiveList(yunStoreCouponReceive);
        ExcelUtil<YunStoreCouponReceive> util = new ExcelUtil<YunStoreCouponReceive>(YunStoreCouponReceive.class);
        return util.exportExcel(list, "receive");
    }

    /**
     * 获取优惠券领用详细信息
     */
    @PreAuthorize("@ss.hasPermi('store:receive:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yunStoreCouponReceiveService.selectYunStoreCouponReceiveById(id));
    }

    /**
     * 新增优惠券领用
     */
    @PreAuthorize("@ss.hasPermi('store:receive:add')")
    @Log(title = "优惠券领用", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunStoreCouponReceive yunStoreCouponReceive)
    {
        return toAjax(yunStoreCouponReceiveService.insertYunStoreCouponReceive(yunStoreCouponReceive));
    }

    /**
     * 修改优惠券领用
     */
    @PreAuthorize("@ss.hasPermi('store:receive:edit')")
    @Log(title = "优惠券领用", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunStoreCouponReceive yunStoreCouponReceive)
    {
        return toAjax(yunStoreCouponReceiveService.updateYunStoreCouponReceive(yunStoreCouponReceive));
    }

    /**
     * 删除优惠券领用
     */
    @PreAuthorize("@ss.hasPermi('store:receive:remove')")
    @Log(title = "优惠券领用", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunStoreCouponReceiveService.deleteYunStoreCouponReceiveByIds(ids));
    }
}
