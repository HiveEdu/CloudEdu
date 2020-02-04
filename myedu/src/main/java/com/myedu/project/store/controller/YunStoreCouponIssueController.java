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
import com.myedu.project.store.domain.YunStoreCouponIssue;
import com.myedu.project.store.service.IYunStoreCouponIssueService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.web.page.TableDataInfo;

/**
 * 店铺优惠券发布Controller
 * 
 * @author 梁少鹏
 * @date 2020-02-03
 */
@RestController
@RequestMapping("/store/publishCoupon")
public class YunStoreCouponIssueController extends BaseController
{
    @Autowired
    private IYunStoreCouponIssueService yunStoreCouponIssueService;

    /**
     * 查询店铺优惠券发布列表
     */
    @PreAuthorize("@ss.hasPermi('store:publishCoupon:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunStoreCouponIssue yunStoreCouponIssue)
    {
        startPage();
        List<YunStoreCouponIssue> list = yunStoreCouponIssueService.selectYunStoreCouponIssueList(yunStoreCouponIssue);
        return getDataTable(list);
    }

    /**
     * 导出店铺优惠券发布列表
     */
    @PreAuthorize("@ss.hasPermi('store:publishCoupon:export')")
    @Log(title = "店铺优惠券发布", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunStoreCouponIssue yunStoreCouponIssue)
    {
        List<YunStoreCouponIssue> list = yunStoreCouponIssueService.selectYunStoreCouponIssueList(yunStoreCouponIssue);
        ExcelUtil<YunStoreCouponIssue> util = new ExcelUtil<YunStoreCouponIssue>(YunStoreCouponIssue.class);
        return util.exportExcel(list, "issue");
    }

    /**
     * 获取店铺优惠券发布详细信息
     */
    @PreAuthorize("@ss.hasPermi('store:publishCoupon:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(yunStoreCouponIssueService.selectYunStoreCouponIssueById(id));
    }

    /**
     * 新增店铺优惠券发布
     */
    @PreAuthorize("@ss.hasPermi('store:publishCoupon:add')")
    @Log(title = "店铺优惠券发布", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunStoreCouponIssue yunStoreCouponIssue)
    {
        return toAjax(yunStoreCouponIssueService.insertYunStoreCouponIssue(yunStoreCouponIssue));
    }

    /**
     * 修改店铺优惠券发布
     */
    @PreAuthorize("@ss.hasPermi('store:publishCoupon:edit')")
    @Log(title = "店铺优惠券发布", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunStoreCouponIssue yunStoreCouponIssue)
    {
        return toAjax(yunStoreCouponIssueService.updateYunStoreCouponIssue(yunStoreCouponIssue));
    }

    /**
     * 删除店铺优惠券发布
     */
    @PreAuthorize("@ss.hasPermi('store:publishCoupon:remove')")
    @Log(title = "店铺优惠券发布", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(yunStoreCouponIssueService.deleteYunStoreCouponIssueByIds(ids));
    }
}
