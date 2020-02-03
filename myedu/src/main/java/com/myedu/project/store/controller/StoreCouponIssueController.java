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
import com.myedu.project.store.domain.StoreCouponIssue;
import com.myedu.project.store.service.IStoreCouponIssueService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.web.page.TableDataInfo;

/**
 * 优惠券制作Controller
 * 
 * @author 梁龙飞
 * @date 2020-02-02
 */
@RestController
@RequestMapping("/store/coupon_issue")
public class StoreCouponIssueController extends BaseController
{
    @Autowired
    private IStoreCouponIssueService storeCouponIssueService;

    /**
     * 查询优惠券制作列表
     */
    @PreAuthorize("@ss.hasPermi('store:coupon_issue:list')")
    @GetMapping("/list")
    public TableDataInfo list(StoreCouponIssue storeCouponIssue)
    {
        startPage();
        List<StoreCouponIssue> list = storeCouponIssueService.selectStoreCouponIssueList(storeCouponIssue);
        return getDataTable(list);
    }

    /**
     * 导出优惠券制作列表
     */
    @PreAuthorize("@ss.hasPermi('store:coupon_issue:export')")
    @Log(title = "优惠券制作", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(StoreCouponIssue storeCouponIssue)
    {
        List<StoreCouponIssue> list = storeCouponIssueService.selectStoreCouponIssueList(storeCouponIssue);
        ExcelUtil<StoreCouponIssue> util = new ExcelUtil<StoreCouponIssue>(StoreCouponIssue.class);
        return util.exportExcel(list, "coupon_issue");
    }

    /**
     * 获取优惠券制作详细信息
     */
    @PreAuthorize("@ss.hasPermi('store:coupon_issue:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(storeCouponIssueService.selectStoreCouponIssueById(id));
    }

    /**
     * 新增优惠券制作
     */
    @PreAuthorize("@ss.hasPermi('store:coupon_issue:add')")
    @Log(title = "优惠券制作", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StoreCouponIssue storeCouponIssue)
    {
        return toAjax(storeCouponIssueService.insertStoreCouponIssue(storeCouponIssue));
    }

    /**
     * 修改优惠券制作
     */
    @PreAuthorize("@ss.hasPermi('store:coupon_issue:edit')")
    @Log(title = "优惠券制作", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StoreCouponIssue storeCouponIssue)
    {
        return toAjax(storeCouponIssueService.updateStoreCouponIssue(storeCouponIssue));
    }

    /**
     * 删除优惠券制作
     */
    @PreAuthorize("@ss.hasPermi('store:coupon_issue:remove')")
    @Log(title = "优惠券制作", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(storeCouponIssueService.deleteStoreCouponIssueByIds(ids));
    }
}
