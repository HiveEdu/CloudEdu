package com.myedu.project.parents.controller;

import com.myedu.common.utils.SecurityUtils;
import com.myedu.common.utils.StringUtils;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.dataBasic.domain.SysGrade;
import com.myedu.project.dataBasic.service.ISysGradeService;
import com.myedu.project.parents.domain.YunStudent;
import com.myedu.project.parents.domain.vo.YunStudentVo;
import com.myedu.project.parents.service.IYunStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 学生数据Controller
 * 
 * @author 梁少鹏
 * @date 2019-12-28
 */
@RestController
@RequestMapping("/parents/student")
public class YunStudentController extends BaseController
{
    @Autowired
    private IYunStudentService yunStudentService;
    @Autowired
    private ISysGradeService sysGradeService;
    /**
     * 查询学生数据列表
     */
    @PreAuthorize("@ss.hasPermi('parents:student:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunStudentVo yunStudent)
    {
        startPage();
        List<YunStudentVo> list = yunStudentService.selectYunStudentList(yunStudent);
        return getDataTable(list);
    }

    /**
     * 导出学生数据列表
     */
    @PreAuthorize("@ss.hasPermi('parents:student:export')")
    @Log(title = "学生数据", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunStudentVo yunStudent)
    {
        List<YunStudentVo> list = yunStudentService.selectYunStudentList(yunStudent);
        ExcelUtil<YunStudentVo> util = new ExcelUtil<YunStudentVo>(YunStudentVo.class);
        return util.exportExcel(list, "student");
    }

    /**
     * 获取学生数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('parents:student:query')")
    @GetMapping(value = { "/", "/{id}" })
    public AjaxResult getInfo(@PathVariable(value ="id", required = false) Long id)
    {
        AjaxResult ajax = AjaxResult.success();
        SysGrade sysGrade=new SysGrade();
        List<SysGrade> list = sysGradeService.selectSysGradeList(sysGrade);
        ajax.put("gradeLists", list);
        if (StringUtils.isNotNull(id))
        {
            ajax.put(AjaxResult.DATA_TAG, yunStudentService.selectYunStudentById(id));
        }
        return ajax;
    }

    /**
     * 新增学生数据
     */
    @PreAuthorize("@ss.hasPermi('parents:student:add')")
    @Log(title = "学生数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Valid @RequestBody YunStudent yunStudent)
    {
        yunStudent.setCreateById(SecurityUtils.getUserId());
        yunStudent.setCreateBy(SecurityUtils.getUsername());
        return toAjax(yunStudentService.insertYunStudent(yunStudent));
    }

    /**
     * 修改学生数据
     */
    @PreAuthorize("@ss.hasPermi('parents:student:edit')")
    @Log(title = "学生数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunStudent yunStudent)
    {
        yunStudent.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(yunStudentService.updateYunStudent(yunStudent));
    }

    /**
     * 删除学生数据
     */
    @PreAuthorize("@ss.hasPermi('parents:student:remove')")
    @Log(title = "学生数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunStudentService.deleteYunStudentByIds(ids));
    }
}
