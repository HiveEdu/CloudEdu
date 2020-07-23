package com.myedu.app.common.controller;

import com.myedu.common.utils.ServletUtils;
import com.myedu.framework.aspectj.lang.annotation.AutoIdempotent;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
import com.myedu.framework.security.LoginUser;
import com.myedu.framework.security.service.TokenService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.system.domain.SysActivity;
import com.myedu.project.system.service.ISysActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 活动管理Controller
 * 
 * @author myedu
 * @date 2020-07-22
 */
@Api("活动管理")
@RestController
@RequestMapping("/app/system/activity")
public class APPSysActivityController extends BaseController
{
    @Autowired
    private ISysActivityService sysActivityService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询活动管理列表
     */
    @AutoIdempotent
    @ApiOperation("查询活动管理列表")
    @ApiImplicitParam(name = "sysActivity", value = "查询门店类型基础列表",
            dataType = "SysActivity")
    @GetMapping("/list")
    public TableDataInfo list(SysActivity sysActivity)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null){
            startPage();
            List<SysActivity> list = sysActivityService.selectSysActivityList(sysActivity);
            return getDataTable(list);
        }else{
            return getDataTableLose(null);
        }

    }



    /**
     * 获取活动管理详细信息
     */
    @AutoIdempotent
    @ApiOperation("获取活动管理详细信息")
    @ApiImplicitParam(name = "id", value = "获取活动管理详细信息",
            dataType = "Long")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysActivityService.selectSysActivityById(id));
    }

    /**
     * 新增活动管理
     */
    @AutoIdempotent
    @ApiOperation("新增活动管理")
    @ApiImplicitParam(name = "sysActivity", value = "新增活动管理",
            dataType = "SysActivity")
    @Log(title = "活动管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysActivity sysActivity)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysActivity.setCreateBy(loginUser.getUser().getNickName());
        return toAjax(sysActivityService.insertSysActivity(sysActivity));
    }

    /**
     * 修改活动管理
     */
    @AutoIdempotent
    @ApiOperation("修改活动管理")
    @ApiImplicitParam(name = "sysActivity", value = "修改活动管理",
            dataType = "SysActivity")
    @Log(title = "活动管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysActivity sysActivity)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        sysActivity.setUpdateBy(loginUser.getUser().getNickName());
        return toAjax(sysActivityService.updateSysActivity(sysActivity));
    }

    /**
     * 删除活动管理
     */
    @AutoIdempotent
    @ApiOperation("删除活动管理")
    @ApiImplicitParam(name = "ids", value = "删除活动管理",
            dataType = "Long[]")
    @Log(title = "活动管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysActivityService.deleteSysActivityByIds(ids));
    }
}
