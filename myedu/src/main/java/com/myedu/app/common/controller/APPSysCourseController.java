package com.myedu.app.common.controller;

import com.myedu.common.utils.SecurityUtils;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.aspectj.lang.annotation.AutoIdempotent;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.dataBasic.domain.SysCourse;
import com.myedu.project.dataBasic.service.ISysCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 课程基本数据Controller
 * 
 * @author 梁少鹏
 * @date 2019-12-28
 */
@Api("课程接口")
@RestController
@RequestMapping("/app/dataBasic/course")
public class APPSysCourseController extends BaseController
{
    @Autowired
    private ISysCourseService sysCourseService;

    /**
     * 查询课程基本数据列表
     */
    @AutoIdempotent
    @ApiOperation("查询课程基本数据列表")
    @GetMapping("/list")
    public TableDataInfo list(SysCourse sysCourse)
    {
        startPage();
        List<SysCourse> list = sysCourseService.selectSysCourseList(sysCourse);
        return getDataTable(list);
    }

    /**
     * 导出课程基本数据列表
     */
    @PreAuthorize("@ss.hasPermi('dataBasic:course:export')")
    @Log(title = "课程基本数据", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysCourse sysCourse)
    {
        List<SysCourse> list = sysCourseService.selectSysCourseList(sysCourse);
        ExcelUtil<SysCourse> util = new ExcelUtil<SysCourse>(SysCourse.class);
        return util.exportExcel(list, "course");
    }

    /**
     * 获取课程基本数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('dataBasic:course:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysCourseService.selectSysCourseById(id));
    }

    /**
     * 新增课程基本数据
     */
    @PreAuthorize("@ss.hasPermi('dataBasic:course:add')")
    @Log(title = "课程基本数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysCourse sysCourse)
    {
        sysCourse.setCreateBy(SecurityUtils.getUsername());
        return toAjax(sysCourseService.insertSysCourse(sysCourse));
    }

    /**
     * 修改课程基本数据
     */
    @PreAuthorize("@ss.hasPermi('dataBasic:course:edit')")
    @Log(title = "课程基本数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysCourse sysCourse)
    {
        sysCourse.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(sysCourseService.updateSysCourse(sysCourse));
    }

    /**
     * 删除课程基本数据
     */
    @PreAuthorize("@ss.hasPermi('dataBasic:course:remove')")
    @Log(title = "课程基本数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysCourseService.deleteSysCourseByIds(ids));
    }
}
