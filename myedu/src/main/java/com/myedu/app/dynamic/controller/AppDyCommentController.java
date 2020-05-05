package com.myedu.app.dynamic.controller;

import com.myedu.common.utils.ServletUtils;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.aspectj.lang.annotation.AutoIdempotent;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
import com.myedu.framework.security.LoginUser;
import com.myedu.framework.security.service.TokenService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.dynamic.domain.YunDyComment;
import com.myedu.project.dynamic.service.IYunDyCommentService;
import com.myedu.project.parents.domain.YunComplaint;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 动态评论Controller
 * 
 * @author 梁少鹏
 * @date 2020-01-01
 */
@Api("动态评论")
@RestController
@RequestMapping("/app/dynamic/comment")
public class AppDyCommentController extends BaseController
{
    @Autowired
    private IYunDyCommentService yunDyCommentService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询动态评论列表
     */
    @AutoIdempotent
    @ApiOperation("查询动态评论列表")
    @ApiImplicitParam(name = "yunDyComment", value = "查询动态评论列表",
            dataType = "YunDyComment")
    @GetMapping("/list")
    public TableDataInfo list(YunDyComment yunDyComment)
    {
        startPage();
        List<YunDyComment> list = yunDyCommentService.selectYunDyCommentList(yunDyComment);
        return getDataTable(list);
    }


    /**
     * 获取动态评论详细信息
     */
    @AutoIdempotent
    @ApiOperation("获取动态评论详细信息")
    @ApiImplicitParam(name = "id", value = "获取动态评论详细信息",
            dataType = "Long")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
      return AjaxResult.success(yunDyCommentService.selectYunDyCommentById(id));
    }

    /**
     * 新增动态评论
     */
    @AutoIdempotent
    @ApiOperation("新增动态评论")
    @ApiImplicitParam(name = "yunDyComment", value = "新增动态评论",
            dataType = "YunDyComment")
    @Log(title = "动态评论", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunDyComment yunDyComment)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        yunDyComment.setCreateById(loginUser.getUser().getUserId());
        yunDyComment.setCreateBy(loginUser.getUser().getNickName());
        return toAjax(yunDyCommentService.insertYunDyComment(yunDyComment));
    }

    /**
     * 修改动态评论
     */
    @AutoIdempotent
    @ApiOperation("修改动态评论")
    @ApiImplicitParam(name = "yunDyComment", value = "修改动态评论",
            dataType = "YunDyComment")
    @Log(title = "动态评论", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunDyComment yunDyComment)
    {
       return toAjax(yunDyCommentService.updateYunDyComment(yunDyComment));
    }

    /**
     * 删除动态评论
     */
    @AutoIdempotent
    @ApiOperation("删除动态评论")
    @ApiImplicitParam(name = "ids", value = "删除动态评论",
            dataType = "Long[]")
    @Log(title = "动态评论", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunDyCommentService.deleteYunDyCommentByIds(ids));
    }
}
