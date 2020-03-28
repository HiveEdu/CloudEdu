package com.myedu.app.store;

import com.myedu.common.utils.DateUtils;
import com.myedu.common.utils.SecurityUtils;
import com.myedu.common.utils.ServletUtils;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
import com.myedu.framework.security.LoginUser;
import com.myedu.framework.security.service.TokenService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.store.domain.YunStoreCoupon;
import com.myedu.project.store.domain.YunStoreCouponIssue;
import com.myedu.project.store.service.IYunStoreCouponIssueService;
import com.myedu.project.store.service.IYunStoreCouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 店铺优惠券Controller
 * 
 * @author 梁少鹏
 * @date 2020-02-03
 */
@Api("店铺优惠券")
@RestController
@RequestMapping("/app/store/coupon")
public class APPYunStoreCouponController extends BaseController
{
    @Autowired
    private IYunStoreCouponService yunStoreCouponService;
    @Autowired
    private IYunStoreCouponIssueService yunStoreCouponIssueService;
    @Autowired
    private TokenService tokenService;
    /**
     * 查询店铺优惠券列表
     */
    @ApiOperation("查询店铺优惠券列表")
    @ApiImplicitParam(name = "yunStoreCoupon", value = "查询店铺优惠券列表",
            dataType = "YunStoreCoupon")
    @GetMapping("/list")
    public TableDataInfo list(YunStoreCoupon yunStoreCoupon)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
        startPage();
        List<YunStoreCoupon> list = yunStoreCouponService.selectYunStoreCouponList(yunStoreCoupon);
        return getDataTable(list);
        }else{
            return getDataTableLose(null);
        }
    }

    /**
     * 导出店铺优惠券列表
     */
    @ApiOperation("导出店铺优惠券列表")
    @ApiImplicitParam(name = "yunStoreCoupon", value = "导出店铺优惠券列表",
            dataType = "YunStoreCoupon")
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
    @ApiOperation("导出店铺优惠券列表")
    @ApiImplicitParam(name = "id", value = "导出店铺优惠券列表",
            dataType = "Long", required = true, paramType = "path")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxResult getInfo(@PathVariable Long id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if(loginUser!=null) {
        return AjaxResult.success(yunStoreCouponService.selectYunStoreCouponById(id));
        }else{
            return AjaxResult.error("token无效");
        }
    }

    /**
     * 新增店铺优惠券
     */
    @ApiOperation("新增店铺优惠券")
    @ApiImplicitParam(name = "yunStoreCoupon", value = "新增店铺优惠券",
            dataType = "YunStoreCoupon")
    @Log(title = "店铺优惠券", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunStoreCoupon yunStoreCoupon)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
            yunStoreCoupon.setCreateById(loginUser.getUser().getUserId());
            yunStoreCoupon.setCreateBy(loginUser.getUser().getNickName());
            yunStoreCoupon.setCreateTime(DateUtils.getNowDate());
        return toAjax(yunStoreCouponService.insertYunStoreCoupon(yunStoreCoupon));
        }else {
            return AjaxResult.error("token无效");
        }
    }

    /**
     * 修改店铺优惠券
     */
    @ApiOperation("修改店铺优惠券")
    @ApiImplicitParam(name = "yunStoreCoupon", value = "修改店铺优惠券",
            dataType = "YunStoreCoupon")
    @Log(title = "店铺优惠券", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunStoreCoupon yunStoreCoupon)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
            yunStoreCoupon.setUpdateTime(DateUtils.getNowDate());
            return toAjax(yunStoreCouponService.updateYunStoreCoupon(yunStoreCoupon));
        }else {
            return AjaxResult.error("token无效");
        }
    }

    /**
     * 删除店铺优惠券
     */
    @ApiOperation("删除店铺优惠券")
    @ApiImplicitParam(name = "ids", value = "删除店铺优惠券",
            dataType = "Long[]")
    @Log(title = "店铺优惠券", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
        return toAjax(yunStoreCouponService.deleteYunStoreCouponByIds(ids));
        }else {
            return AjaxResult.error("token无效");
        }
    }

    /**
     * 发布店铺优惠券
     */
    @ApiOperation("发布店铺优惠券")
    @ApiImplicitParam(name = "yunStoreCouponIssue", value = "发布店铺优惠券",
            dataType = "YunStoreCouponIssue")
    @Log(title = "店铺优惠券", businessType = BusinessType.INSERT)
    @PostMapping("/publish")
    public AjaxResult publish(@RequestBody YunStoreCouponIssue yunStoreCouponIssue)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
        Long couponId=yunStoreCouponIssue.getCid();
        YunStoreCoupon yunStoreCoupon=yunStoreCouponService.selectYunStoreCouponById(couponId);
        yunStoreCoupon.setPublishNum(yunStoreCoupon.getPublishNum()+1);
        yunStoreCouponService.updateYunStoreCoupon(yunStoreCoupon);
        return toAjax(yunStoreCouponIssueService.insertYunStoreCouponIssue(yunStoreCouponIssue));
        }else {
            return AjaxResult.error("token无效");
        }
    }
}
