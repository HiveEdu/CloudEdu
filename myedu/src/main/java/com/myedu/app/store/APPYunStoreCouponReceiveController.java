package com.myedu.app.store;

import com.myedu.common.utils.DateUtils;
import com.myedu.common.utils.ServletUtils;
import com.myedu.framework.aspectj.lang.annotation.AutoIdempotent;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
import com.myedu.framework.security.LoginUser;
import com.myedu.framework.security.service.TokenService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.store.domain.YunStoreCouponReceive;
import com.myedu.project.store.service.IYunStoreCouponReceiveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 优惠券领用Controller
 * 
 * @author 梁少鹏
 * @date 2020-02-24
 */
@Api("优惠券领用")
@RestController
@RequestMapping("/app/store/receive")
public class APPYunStoreCouponReceiveController extends BaseController
{
    @Autowired
    private IYunStoreCouponReceiveService yunStoreCouponReceiveService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询优惠券领用列表
     */
    @ApiOperation("查询优惠券领用列表")
    @AutoIdempotent
    @ApiImplicitParam(name = "yunStoreCouponReceive", value = "查询优惠券领用列表",
            dataType = "YunStoreCouponReceive")
    @PreAuthorize("@ss.hasPermi('store:receive:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunStoreCouponReceive yunStoreCouponReceive)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        startPage();
        yunStoreCouponReceive.setCreateById(loginUser.getUser().getUserId());
        List<YunStoreCouponReceive> list = yunStoreCouponReceiveService.selectYunStoreCouponReceiveList(yunStoreCouponReceive);
        return getDataTable(list);
    }


    /**
     * 获取优惠券领用详细信息
     */
    @ApiOperation("获取优惠券领用详细信息")
    @AutoIdempotent
    @ApiImplicitParam(name = "id", value = "获取优惠券领用详细信息",
            dataType = "Long")
    @PreAuthorize("@ss.hasPermi('store:receive:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yunStoreCouponReceiveService.selectYunStoreCouponReceiveById(id));
    }

    /**
     * 新增优惠券领用
     */
    @ApiOperation("新增优惠券领用")
    @AutoIdempotent
    @ApiImplicitParam(name = "yunStoreCouponReceive", value = "新增优惠券领用",
            dataType = "YunStoreCouponReceive")
    @PreAuthorize("@ss.hasPermi('store:receive:add')")
    @Log(title = "优惠券领用", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunStoreCouponReceive yunStoreCouponReceive)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        yunStoreCouponReceive.setCreateById(loginUser.getUser().getUserId());
        yunStoreCouponReceive.setCreateBy(loginUser.getUser().getNickName());
        yunStoreCouponReceive.setCreateTime(DateUtils.getNowDate());
        return toAjax(yunStoreCouponReceiveService.insertYunStoreCouponReceive(yunStoreCouponReceive));
    }

    /**
     * 修改优惠券领用
     */
    @ApiOperation("修改优惠券领用")
    @AutoIdempotent
    @ApiImplicitParam(name = "yunStoreCouponReceive", value = "修改优惠券领用",
            dataType = "YunStoreCouponReceive")
    @PreAuthorize("@ss.hasPermi('store:receive:edit')")
    @Log(title = "优惠券领用", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunStoreCouponReceive yunStoreCouponReceive)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        yunStoreCouponReceive.setUpdateBy(loginUser.getUser().getNickName());
        yunStoreCouponReceive.setUpdateTime(DateUtils.getNowDate());
        return toAjax(yunStoreCouponReceiveService.updateYunStoreCouponReceive(yunStoreCouponReceive));
    }

    /**
     * 删除优惠券领用
     */
    @ApiOperation("删除优惠券领用")
    @AutoIdempotent
    @ApiImplicitParam(name = "ids", value = "删除优惠券领用",
            dataType = "Long[]")
    @PreAuthorize("@ss.hasPermi('store:receive:remove')")
    @Log(title = "优惠券领用", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunStoreCouponReceiveService.deleteYunStoreCouponReceiveByIds(ids));
    }
}
