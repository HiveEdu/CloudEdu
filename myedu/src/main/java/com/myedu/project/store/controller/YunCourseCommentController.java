package com.myedu.project.store.controller;

import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.store.domain.YunCourseComment;
import com.myedu.project.store.service.IYunCourseCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 课程评论Controller
 * 
 * @author 梁龙飞
 * @date 2020-04-13
 */
@RestController
@RequestMapping("/store/comment")
public class YunCourseCommentController extends BaseController
{
    @Autowired
    private IYunCourseCommentService yunCourseCommentService;

    /**
     * 查询课程评论列表
     */
    @PreAuthorize("@ss.hasPermi('store:comment:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunCourseComment yunCourseComment)
    {
        startPage();
        List<YunCourseComment> list = yunCourseCommentService.selectYunCourseCommentList(yunCourseComment);
        return getDataTable(list);
    }

    /**
     * 导出课程评论列表
     */
    @PreAuthorize("@ss.hasPermi('store:comment:export')")
    @Log(title = "课程评论", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunCourseComment yunCourseComment)
    {
        List<YunCourseComment> list = yunCourseCommentService.selectYunCourseCommentList(yunCourseComment);
        ExcelUtil<YunCourseComment> util = new ExcelUtil<YunCourseComment>(YunCourseComment.class);
        return util.exportExcel(list, "comment");
    }

    /**
     * 获取课程评论详细信息
     */
    @PreAuthorize("@ss.hasPermi('store:comment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yunCourseCommentService.selectYunCourseCommentById(id));
    }

    /**
     * 新增课程评论
     */
    @PreAuthorize("@ss.hasPermi('store:comment:add')")
    @Log(title = "课程评论", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunCourseComment yunCourseComment)
    {
        return toAjax(yunCourseCommentService.insertYunCourseComment(yunCourseComment));
    }

    /**
     * 修改课程评论
     */
    @PreAuthorize("@ss.hasPermi('store:comment:edit')")
    @Log(title = "课程评论", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunCourseComment yunCourseComment)
    {
        return toAjax(yunCourseCommentService.updateYunCourseComment(yunCourseComment));
    }

    /**
     * 删除课程评论
     */
    @PreAuthorize("@ss.hasPermi('store:comment:remove')")
    @Log(title = "课程评论", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunCourseCommentService.deleteYunCourseCommentByIds(ids));
    }
}
