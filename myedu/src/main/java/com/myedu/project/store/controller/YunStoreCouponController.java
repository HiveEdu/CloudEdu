package com.myedu.project.store.controller;

import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

import com.myedu.common.utils.DateUtils;
import com.myedu.common.utils.SecurityUtils;
import com.myedu.common.utils.StringUtils;
import com.myedu.project.store.domain.YunStore;
import com.myedu.project.store.domain.YunStoreCouponIssue;
import com.myedu.project.store.domain.vo.YunStoreVo;
import com.myedu.project.store.enums.CouponIssueStatus;
import com.myedu.project.store.service.IYunStoreCouponIssueService;
import com.myedu.project.store.service.IYunStoreService;
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
import com.myedu.project.store.domain.YunStoreCoupon;
import com.myedu.project.store.service.IYunStoreCouponService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.web.page.TableDataInfo;

/**
 * 店铺优惠券Controller
 * 
 * @author 梁少鹏
 * @date 2020-02-03
 */
@RestController
@RequestMapping("/store/coupon")
public class YunStoreCouponController extends BaseController
{
    @Autowired
    private IYunStoreCouponService yunStoreCouponService;
    @Autowired
    private IYunStoreCouponIssueService yunStoreCouponIssueService;
    @Autowired
    private IYunStoreService yunStoreService;
    /**
     * 查询店铺优惠券列表
     */
    @PreAuthorize("@ss.hasPermi('store:coupon:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunStoreCoupon yunStoreCoupon)
    {
        startPage();
        List<YunStoreCoupon> list = yunStoreCouponService.selectYunStoreCouponList(yunStoreCoupon);
        return getDataTable(list);
    }

    /**
     * 导出店铺优惠券列表
     */
    @PreAuthorize("@ss.hasPermi('store:coupon:export')")
    @Log(title = "店铺优惠券", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunStoreCoupon yunStoreCoupon)
    {
        List<YunStoreCoupon> list = yunStoreCouponService.selectYunStoreCouponList(yunStoreCoupon);
        ExcelUtil<YunStoreCoupon> util = new ExcelUtil<YunStoreCoupon>(YunStoreCoupon.class);
        return util.exportExcel(list, "coupon");
    }

    /**
     * 获取店铺优惠券详细信息
     */
    @PreAuthorize("@ss.hasPermi('store:coupon:query')")
    @GetMapping(value = { "/", "/{id}" })
    public AjaxResult getInfo(@PathVariable(value = "id", required = false) Long id)
    {
        AjaxResult ajax = AjaxResult.success();
        YunStoreVo yunStore=new YunStoreVo();
        yunStore.setCreateById(SecurityUtils.getUserId());
        List<YunStoreVo> stores=yunStoreService.selectYunStoreList(yunStore);
        ajax.put("stores", stores);
        if (StringUtils.isNotNull(id))
        {
            ajax.put(AjaxResult.DATA_TAG,yunStoreCouponService.selectYunStoreCouponById(id));
        }
        return ajax;
    }

    /**
     * 新增店铺优惠券
     */
    @PreAuthorize("@ss.hasPermi('store:coupon:add')")
    @Log(title = "店铺优惠券", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunStoreCoupon yunStoreCoupon)
    {
        return toAjax(yunStoreCouponService.insertYunStoreCoupon(yunStoreCoupon));
    }

    /**
     * 修改店铺优惠券
     */
    @PreAuthorize("@ss.hasPermi('store:coupon:edit')")
    @Log(title = "店铺优惠券", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunStoreCoupon yunStoreCoupon)
    {
        return toAjax(yunStoreCouponService.updateYunStoreCoupon(yunStoreCoupon));
    }

    /**
     * 删除店铺优惠券
     */
    @PreAuthorize("@ss.hasPermi('store:coupon:remove')")
    @Log(title = "店铺优惠券", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunStoreCouponService.deleteYunStoreCouponByIds(ids));
    }



    @PreAuthorize("@ss.hasPermi('store:coupon:publish')")
    @Log(title = "店铺优惠券发布", businessType = BusinessType.INSERT)
    @PostMapping("/publish")
    public AjaxResult publish(@RequestBody YunStoreCouponIssue yunStoreCouponIssue)
    {
        Long couponId=yunStoreCouponIssue.getCid();
        YunStoreCoupon yunStoreCoupon=yunStoreCouponService.selectYunStoreCouponById(couponId);
        if(yunStoreCoupon.getStatus().equals(CouponIssueStatus.CLOSE.getCode())){
            return AjaxResult.error(500,"此优惠卷未开启");
        }else if(yunStoreCoupon.getStatus().equals(CouponIssueStatus.INVA.getCode())){
            return AjaxResult.error(500,"此优惠卷无效");
        }else if(DateUtils.getDay(new Date(),yunStoreCoupon.getCreateTime())>yunStoreCoupon.getCouponTime() ){
            return AjaxResult.error(500,"此优惠卷已经过期");
        }else{
            yunStoreCoupon.setPublishNum(yunStoreCoupon.getPublishNum()+1);
            yunStoreCouponService.updateYunStoreCoupon(yunStoreCoupon);
            yunStoreCouponIssueService.insertYunStoreCouponIssue(yunStoreCouponIssue);
            return AjaxResult.success("发布成功");
        }
    }

    /*
     * @Description :校验是否可以发布
     * @Author : 梁少鹏
     * @Date : 2020/2/27 17:24
     */
    @PreAuthorize("@ss.hasPermi('store:coupon:ispublish')")
    @GetMapping(value = { "/ispublish", "/ispublish/{id}" })
    public AjaxResult ispublish(@PathVariable(value = "id", required = false) Long id)
    {
        YunStoreCoupon yunStoreCoupon=yunStoreCouponService.selectYunStoreCouponById(id);
        if(yunStoreCoupon.getStatus().equals(CouponIssueStatus.CLOSE.getCode())){
            return AjaxResult.error(500,"此优惠卷未开启");
        }else if(yunStoreCoupon.getStatus().equals(CouponIssueStatus.INVA.getCode())){
            return AjaxResult.error(500,"此优惠卷无效");
        }else if(DateUtils.getDay(new Date(),yunStoreCoupon.getCreateTime())>yunStoreCoupon.getCouponTime() ){
            return AjaxResult.error(500,"此优惠卷已经过期");
        }else{
            return AjaxResult.success("可以发布");
        }
    }
}
