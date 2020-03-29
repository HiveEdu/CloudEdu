package com.myedu.app.dynamic.controller;

import com.myedu.common.utils.ServletUtils;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
import com.myedu.framework.security.LoginUser;
import com.myedu.framework.security.service.TokenService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.dynamic.domain.YunDyLikes;
import com.myedu.project.dynamic.service.IYunDyLikesService;
import com.myedu.project.parents.domain.YunComplaint;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 动态点赞Controller
 * 
 * @author 梁少鹏
 * @date 2020-01-01
 */
@Api("动态点赞")
@RestController
@RequestMapping("/app/dynamic/likes")
public class AppDyLikesController extends BaseController
{
    @Autowired
    private IYunDyLikesService yunDyLikesService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询动态点赞列表
     */
    @ApiOperation("查询动态点赞列表")
    @ApiImplicitParam(name = "yunDyLikes", value = "查询动态点赞列表",
            dataType = "YunDyLikes")
    @GetMapping("/list")
    public TableDataInfo list(YunDyLikes yunDyLikes)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
            startPage();
            List<YunDyLikes> list = yunDyLikesService.selectYunDyLikesList(yunDyLikes);
            return getDataTable(list);
        }else {
            return getDataTableLose(null);
        }

    }

    /**
     * 导出动态点赞列表
     */
    @ApiOperation("导出动态点赞列表")
    @ApiImplicitParam(name = "yunDyLikes", value = "导出动态点赞列表",
            dataType = "YunDyLikes")
    @Log(title = "动态点赞", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunDyLikes yunDyLikes)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
            List<YunDyLikes> list = yunDyLikesService.selectYunDyLikesList(yunDyLikes);
            ExcelUtil<YunDyLikes> util = new ExcelUtil<YunDyLikes>(YunDyLikes.class);
            return util.exportExcel(list, "likes");
        }else {
            return AjaxResult.error("token无效");
        }
    }

    /**
     * 获取动态点赞详细信息
     */
    @ApiOperation("获取动态点赞详细信息")
    @ApiImplicitParam(name = "id", value = "获取动态点赞详细信息",
            dataType = "Long")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
            return AjaxResult.success(yunDyLikesService.selectYunDyLikesById(id));
        }else {
            return AjaxResult.error("token无效");
        }

    }

    /**
     * 新增动态点赞
     */
    @ApiOperation("新增动态点赞")
    @ApiImplicitParam(name = "yunDyLikes", value = "新增动态点赞",
            dataType = "YunDyLikes")
    @Log(title = "动态点赞", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunDyLikes yunDyLikes)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
            return toAjax(yunDyLikesService.insertYunDyLikes(yunDyLikes));
        }else {
            return AjaxResult.error("token无效");
        }

    }

    /**
     * 修改动态点赞
     */
    @ApiOperation("修改动态点赞")
    @ApiImplicitParam(name = "yunDyLikes", value = "修改动态点赞",
            dataType = "YunDyLikes")
    @Log(title = "动态点赞", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunDyLikes yunDyLikes)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
            return toAjax(yunDyLikesService.updateYunDyLikes(yunDyLikes));
        }else {
            return AjaxResult.error("token无效");
        }
    }

    /**
     * 删除动态点赞
     */
    @ApiOperation("删除动态点赞")
    @ApiImplicitParam(name = "ids", value = "删除动态点赞",
            dataType = "Long[]")
    @Log(title = "删除动态点赞", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
            return toAjax(yunDyLikesService.deleteYunDyLikesByIds(ids));
        }else {
            return AjaxResult.error("token无效");
        }

    }
}
