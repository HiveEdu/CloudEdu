package com.myedu.project.dataBasic.controller;

import com.myedu.common.utils.SecurityUtils;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.dataBasic.domain.SysGrade;
import com.myedu.project.dataBasic.service.ISysGradeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 年级基础Controller
 * 
 * @author myedu
 * @date 2019-12-26
 */
@RestController
@RequestMapping("/dataBasic/grade")
public class SysGradeController extends BaseController
{
    @Autowired
    private ISysGradeService sysGradeService;

    /**
     * 查询年级基础列表
     */
    @ApiOperation("查询年级基础列表")
    @GetMapping("/list")
    public TableDataInfo list(SysGrade sysGrade)
    {
        startPage();
        List<SysGrade> list = sysGradeService.selectSysGradeList(sysGrade);
        return getDataTable(list);
    }

    /**
     * 导出年级基础列表
     */
    @PreAuthorize("@ss.hasPermi('dataBasic:grade:export')")
    @Log(title = "年级基础", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysGrade sysGrade)
    {
        List<SysGrade> list = sysGradeService.selectSysGradeList(sysGrade);
        ExcelUtil<SysGrade> util = new ExcelUtil<SysGrade>(SysGrade.class);
        return util.exportExcel(list, "grade");
    }

    /**
     * 获取年级基础详细信息
     */
    @ApiOperation("获取年级基础详细信息")
    @PreAuthorize("@ss.hasPermi('dataBasic:grade:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysGradeService.selectSysGradeById(id));
    }

    /**
     * 新增年级基础
     */
    @PreAuthorize("@ss.hasPermi('dataBasic:grade:add')")
    @Log(title = "年级基础", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysGrade sysGrade)
    {
        sysGrade.setCreateBy(SecurityUtils.getUsername());
        return toAjax(sysGradeService.insertSysGrade(sysGrade));
    }

    /**
     * 修改年级基础
     */
    @PreAuthorize("@ss.hasPermi('dataBasic:grade:edit')")
    @Log(title = "年级基础", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysGrade sysGrade)
    {
        sysGrade.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(sysGradeService.updateSysGrade(sysGrade));
    }

    /**
     * 删除年级基础
     */
    @PreAuthorize("@ss.hasPermi('dataBasic:grade:remove')")
    @Log(title = "年级基础", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysGradeService.deleteSysGradeByIds(ids));
    }
}
