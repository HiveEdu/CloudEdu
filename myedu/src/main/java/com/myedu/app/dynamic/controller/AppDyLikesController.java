package com.myedu.app.dynamic.controller;

import com.myedu.framework.aspectj.lang.annotation.AutoIdempotent;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
import com.myedu.framework.security.service.TokenService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.dynamic.domain.YunDyLikes;
import com.myedu.project.dynamic.service.IYunDyLikesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
    @AutoIdempotent
    @ApiOperation("查询动态点赞列表")
    @ApiImplicitParam(name = "yunDyLikes", value = "查询动态点赞列表",
            dataType = "YunDyLikes")
    @GetMapping("/list")
    public TableDataInfo list(YunDyLikes yunDyLikes)
    {
        startPage();
        List<YunDyLikes> list = yunDyLikesService.selectYunDyLikesList(yunDyLikes);
        return getDataTable(list);
    }


    /**
     * 获取动态点赞详细信息
     */
    @AutoIdempotent
    @ApiOperation("获取动态点赞详细信息")
    @ApiImplicitParam(name = "id", value = "获取动态点赞详细信息",
            dataType = "Long")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
       return AjaxResult.success(yunDyLikesService.selectYunDyLikesById(id));
    }

    /**
     * 新增动态点赞
     */
    @AutoIdempotent
    @ApiOperation("新增动态点赞")
    @ApiImplicitParam(name = "yunDyLikes", value = "新增动态点赞",
            dataType = "YunDyLikes")
    @Log(title = "动态点赞", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunDyLikes yunDyLikes)
    {
        return toAjax(yunDyLikesService.insertYunDyLikes(yunDyLikes));
    }

    /**
     * 修改动态点赞
     */
    @AutoIdempotent
    @ApiOperation("修改动态点赞")
    @ApiImplicitParam(name = "yunDyLikes", value = "修改动态点赞",
            dataType = "YunDyLikes")
    @Log(title = "动态点赞", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunDyLikes yunDyLikes) {
        return toAjax(yunDyLikesService.updateYunDyLikes(yunDyLikes));
    }
    /**
     * 删除动态点赞
     */
    @AutoIdempotent
    @ApiOperation("删除动态点赞")
    @ApiImplicitParam(name = "ids", value = "删除动态点赞",
            dataType = "Long[]")
    @Log(title = "删除动态点赞", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
            return toAjax(yunDyLikesService.deleteYunDyLikesByIds(ids));
    }
}
