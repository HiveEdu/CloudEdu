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
import com.myedu.project.store.domain.StoreCoupon;
import com.myedu.project.store.service.IStoreCouponService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.web.page.TableDataInfo;

/**
 * 优惠券制作Controller
 * 
 * @author 梁龙飞
 * @date 2020-02-03
 */
@RestController
@RequestMapping("/store/coupon")
public class StoreCouponController extends BaseController
{
    @Autowired
    private IStoreCouponService storeCouponService;

    /**
     * 查询优惠券制作列表
     */
    @PreAuthorize("@ss.hasPermi('store:coupon:list')")
    @GetMapping("/list")
    public TableDataInfo list(StoreCoupon storeCoupon)
    {
        startPage();
        List<StoreCoupon> list = storeCouponService.selectStoreCouponList(storeCoupon);
        return getDataTable(list);
    }

    /**
     * 导出优惠券制作列表
     */
    @PreAuthorize("@ss.hasPermi('store:coupon:export')")
    @Log(title = "优惠券制作", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(StoreCoupon storeCoupon)
    {
        List<StoreCoupon> list = storeCouponService.selectStoreCouponList(storeCoupon);
        ExcelUtil<StoreCoupon> util = new ExcelUtil<StoreCoupon>(StoreCoupon.class);
        return util.exportExcel(list, "coupon");
    }

    /**
     * 获取优惠券制作详细信息
     */
    @PreAuthorize("@ss.hasPermi('store:coupon:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(storeCouponService.selectStoreCouponById(id));
    }

    /**
     * 新增优惠券制作
     */
    @PreAuthorize("@ss.hasPermi('store:coupon:add')")
    @Log(title = "优惠券制作", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StoreCoupon storeCoupon)
    {
        return toAjax(storeCouponService.insertStoreCoupon(storeCoupon));
    }

    /**
     * 修改优惠券制作
     */
    @PreAuthorize("@ss.hasPermi('store:coupon:edit')")
    @Log(title = "优惠券制作", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StoreCoupon storeCoupon)
    {
        return toAjax(storeCouponService.updateStoreCoupon(storeCoupon));
    }

    /**
     * 删除优惠券制作
     */
    @PreAuthorize("@ss.hasPermi('store:coupon:remove')")
    @Log(title = "优惠券制作", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(storeCouponService.deleteStoreCouponByIds(ids));
    }
}
