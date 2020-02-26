package com.myedu.app.hometeacher;

import com.myedu.common.utils.StringUtils;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.dataBasic.domain.SysGrade;
import com.myedu.project.dataBasic.service.ISysGradeService;
import com.myedu.project.hometeacher.domain.HomeCourse;
import com.myedu.project.hometeacher.service.IHomeCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 家教课程设置Controller
 * 
 * @author 梁龙飞
 * @date 2020-02-12
 */
@Api("家教课程设置")
@RestController
@RequestMapping("/app/hometeacher/course")
public class APPHomeCourseController extends BaseController
{
    @Autowired
    private IHomeCourseService homeCourseService;
    @Autowired
    private ISysGradeService sysGradeService;


    /**
     * 查询课程列表
     */
    @ApiOperation("查询课程列表")
    @ApiImplicitParam(name = "homeCourse", value = "查询课程列表",
            dataType = "HomeCourse")
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
    @ApiOperation("导出课程列表")
    @ApiImplicitParam(name = "homeCourse", value = "导出课程列表",
            dataType = "HomeCourse")
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
    @ApiOperation("获取课程详细信息")
    @ApiImplicitParam(name = "id", value = "获取课程详细信息",
            dataType = "Long")
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
    @ApiOperation("新增课程")
    @ApiImplicitParam(name = "homeCourse", value = "新增课程",
            dataType = "HomeCourse")
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
    @ApiOperation("修改课程")
    @ApiImplicitParam(name = "homeCourse", value = "修改课程",
            dataType = "HomeCourse")
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
    @ApiOperation("删除课程")
    @ApiImplicitParam(name = "ids", value = "删除课程",
            dataType = "Long[]")
    @PreAuthorize("@ss.hasPermi('hometeacher:course:remove')")
    @Log(title = "课程", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(homeCourseService.deleteHomeCourseByIds(ids));
    }
}
