package com.myedu.project.dynamic.controller;

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
import com.myedu.project.dynamic.domain.YunDyComment;
import com.myedu.project.dynamic.service.IYunDyCommentService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.web.page.TableDataInfo;

/**
 * 动态评论Controller
 * 
 * @author 梁少鹏
 * @date 2020-01-01
 */
@RestController
@RequestMapping("/dynamic/comment")
public class YunDyCommentController extends BaseController
{
    @Autowired
    private IYunDyCommentService yunDyCommentService;

    /**
     * 查询动态评论列表
     */
    @PreAuthorize("@ss.hasPermi('dynamic:comment:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunDyComment yunDyComment)
    {
        startPage();
        List<YunDyComment> list = yunDyCommentService.selectYunDyCommentList(yunDyComment);
        return getDataTable(list);
    }

    /**
     * 导出动态评论列表
     */
    @PreAuthorize("@ss.hasPermi('dynamic:comment:export')")
    @Log(title = "动态评论", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunDyComment yunDyComment)
    {
        List<YunDyComment> list = yunDyCommentService.selectYunDyCommentList(yunDyComment);
        ExcelUtil<YunDyComment> util = new ExcelUtil<YunDyComment>(YunDyComment.class);
        return util.exportExcel(list, "comment");
    }

    /**
     * 获取动态评论详细信息
     */
    @PreAuthorize("@ss.hasPermi('dynamic:comment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yunDyCommentService.selectYunDyCommentById(id));
    }

    /**
     * 新增动态评论
     */
    @PreAuthorize("@ss.hasPermi('dynamic:comment:add')")
    @Log(title = "动态评论", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunDyComment yunDyComment)
    {
        return toAjax(yunDyCommentService.insertYunDyComment(yunDyComment));
    }

    /**
     * 修改动态评论
     */
    @PreAuthorize("@ss.hasPermi('dynamic:comment:edit')")
    @Log(title = "动态评论", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunDyComment yunDyComment)
    {
        return toAjax(yunDyCommentService.updateYunDyComment(yunDyComment));
    }

    /**
     * 删除动态评论
     */
    @PreAuthorize("@ss.hasPermi('dynamic:comment:remove')")
    @Log(title = "动态评论", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunDyCommentService.deleteYunDyCommentByIds(ids));
    }
}
