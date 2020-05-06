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
import com.myedu.project.store.domain.YunCourseComment;
import com.myedu.project.store.service.IYunCourseCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 课程评论Controller
 * 
 * @author 梁龙飞
 * @date 2020-04-13
 */
@Api("门店课程评价")
@RestController
@RequestMapping("/app/store/comment")
public class APPYunCourseCommentController extends BaseController
{
    @Autowired
    private IYunCourseCommentService yunCourseCommentService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询课程评论列表
     */
    @AutoIdempotent
    @ApiOperation("查询课程评论列表")
    @ApiImplicitParam(name = "yunCourseComment", value = "查询课程评论列表",
            dataType = "YunCourseComment")
    @GetMapping("/list")
    public TableDataInfo list(YunCourseComment yunCourseComment)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        startPage();
        yunCourseComment.setCreateById(loginUser.getUser().getUserId());
        List<YunCourseComment> list = yunCourseCommentService.selectYunCourseCommentList(yunCourseComment);
        return getDataTable(list);
    }


    /**
     * 获取课程评论详细信息
     */
    @AutoIdempotent
    @ApiOperation("获取课程评论详细信息")
    @ApiImplicitParam(name = "id", value = "获取课程评论详细信息",
            dataType = "Long")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yunCourseCommentService.selectYunCourseCommentById(id));
    }

    /**
     * 新增课程评论
     */
    @AutoIdempotent
    @ApiOperation("新增课程评论")
    @ApiImplicitParam(name = "yunCourseComment", value = "新增课程评论",
            dataType = "YunCourseComment")
    @Log(title = "新增课程评论", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunCourseComment yunCourseComment)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        yunCourseComment.setCreateById(loginUser.getUser().getUserId());
        yunCourseComment.setCreateBy(loginUser.getUser().getNickName());
        yunCourseComment.setCreateTime(DateUtils.getNowDate());
        return toAjax(yunCourseCommentService.insertYunCourseComment(yunCourseComment));

    }

    /**
     * 修改课程评论
     */
    @AutoIdempotent
    @ApiOperation("修改课程评论")
    @ApiImplicitParam(name = "yunCourseComment", value = "修改课程评论",
            dataType = "YunCourseComment")
    @Log(title = "课程评论", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunCourseComment yunCourseComment)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        yunCourseComment.setUpdateBy(loginUser.getUser().getNickName());
        yunCourseComment.setUpdateTime(DateUtils.getNowDate());
        return toAjax(yunCourseCommentService.updateYunCourseComment(yunCourseComment));

    }

    /**
     * 删除课程评论
     */
    @AutoIdempotent
    @ApiOperation("删除课程评论")
    @ApiImplicitParam(name = "ids", value = "删除课程评论",
            dataType = "Long[]")
    @Log(title = "课程评论", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunCourseCommentService.deleteYunCourseCommentByIds(ids));
    }
}
