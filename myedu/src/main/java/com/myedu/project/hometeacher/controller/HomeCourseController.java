package com.myedu.project.hometeacher.controller;

import java.util.List;

import com.myedu.common.utils.StringUtils;
import com.myedu.project.dataBasic.domain.SysGrade;
import com.myedu.project.dataBasic.service.ISysGradeService;
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
import com.myedu.project.hometeacher.domain.HomeCourse;
import com.myedu.project.hometeacher.service.IHomeCourseService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.web.page.TableDataInfo;

/**
 * 课程Controller
 * 
 * @author 梁龙飞
 * @date 2020-02-12
 */
@RestController
@RequestMapping("/hometeacher/course")
public class HomeCourseController extends BaseController
{
    @Autowired
    private IHomeCourseService homeCourseService;
    @Autowired
    private ISysGradeService sysGradeService;
    /**
     * 查询课程列表
     */
    @PreAuthorize("@ss.hasPermi('hometeacher:course:list')")
    @GetMapping("/list")
    public TableDataInfo list(HomeCourse homeCourse)
    {
        startPage();
        List<HomeCourse> list = homeCourseService.selectHomeCourseList(homeCourse);
        return getDataTable(list);
    }

    /**
     * 导出课程列表
     */
    @PreAuthorize("@ss.hasPermi('hometeacher:course:export')")
    @Log(title = "课程", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(HomeCourse homeCourse)
    {
        List<HomeCourse> list = homeCourseService.selectHomeCourseList(homeCourse);
        ExcelUtil<HomeCourse> util = new ExcelUtil<HomeCourse>(HomeCourse.class);
        return util.exportExcel(list, "course");
    }

    /**
     * 获取课程详细信息
     */
    @PreAuthorize("@ss.hasPermi('hometeacher:course:query')")
    @GetMapping(value = { "/", "/{id}" })
    public AjaxResult getInfo(@PathVariable(value = "id", required = false) Long id) {
        AjaxResult ajax = AjaxResult.success();
        SysGrade sysGrade = new SysGrade();
        ajax.put("sysGrades", sysGradeService.selectSysGradeList(sysGrade));
        if (StringUtils.isNotNull(id)){

            ajax.put(AjaxResult.DATA_TAG, homeCourseService.selectHomeCourseById(id));

        }
        return ajax;
    }

    /**
     * 新增课程
     */
    @PreAuthorize("@ss.hasPermi('hometeacher:course:add')")
    @Log(title = "课程", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HomeCourse homeCourse)
    {
        return toAjax(homeCourseService.insertHomeCourse(homeCourse));
    }

    /**
     * 修改课程
     */
    @PreAuthorize("@ss.hasPermi('hometeacher:course:edit')")
    @Log(title = "课程", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HomeCourse homeCourse)
    {
        return toAjax(homeCourseService.updateHomeCourse(homeCourse));
    }

    /**
     * 删除课程
     */
    @PreAuthorize("@ss.hasPermi('hometeacher:course:remove')")
    @Log(title = "课程", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(homeCourseService.deleteHomeCourseByIds(ids));
    }
}
