package com.myedu.project.parents.controller;

import com.myedu.common.utils.SecurityUtils;
import com.myedu.common.utils.StringUtils;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.parents.domain.YunStuLeave;
import com.myedu.project.parents.domain.vo.YunStuLeaveVo;
import com.myedu.project.parents.domain.vo.YunStudentVo;
import com.myedu.project.parents.service.IYunStuLeaveService;
import com.myedu.project.parents.service.IYunStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 学生请假Controller
 * 
 * @author 梁少鹏
 * @date 2019-12-28
 */
@RestController
@RequestMapping("/parents/leave")
public class YunStuLeaveController extends BaseController
{
    @Autowired
    private IYunStuLeaveService yunStuLeaveService;
    @Autowired
    private IYunStudentService yunStudentService;
    /**
     * 查询学生请假列表
     */
    @PreAuthorize("@ss.hasPermi('parents:leave:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunStuLeaveVo yunStuLeave)
    {
        startPage();
        List<YunStuLeaveVo> list = yunStuLeaveService.selectYunStuLeaveList(yunStuLeave);
        return getDataTable(list);
    }

    /**
     * 导出学生请假列表
     */
    @PreAuthorize("@ss.hasPermi('parents:leave:export')")
    @Log(title = "学生请假", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunStuLeaveVo yunStuLeave)
    {
        List<YunStuLeaveVo> list = yunStuLeaveService.selectYunStuLeaveList(yunStuLeave);
        ExcelUtil<YunStuLeaveVo> util = new ExcelUtil<YunStuLeaveVo>(YunStuLeaveVo.class);
        return util.exportExcel(list, "leave");
    }

    /**
     * 获取学生请假详细信息
     */
    @PreAuthorize("@ss.hasPermi('parents:leave:query')")
    @GetMapping(value = { "/", "/{id}" })
    public AjaxResult getInfo(@PathVariable(value ="id", required = false) Long id)
    {
        AjaxResult ajax = AjaxResult.success();
        YunStudentVo yunStudentVo=new YunStudentVo();
        yunStudentVo.setCreateById(SecurityUtils.getUserId());
        List<YunStudentVo> list = yunStudentService.selectYunStudentList(yunStudentVo);
        ajax.put("studentLists", list);
        if (StringUtils.isNotNull(id))
        {
            ajax.put(AjaxResult.DATA_TAG, yunStuLeaveService.selectYunStuLeaveById(id));
        }
        return ajax;
    }

    /**
     * 新增学生请假
     */
    @PreAuthorize("@ss.hasPermi('parents:leave:add')")
    @Log(title = "学生请假", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Valid @RequestBody YunStuLeave yunStuLeave)
    {
        yunStuLeave.setCreateById(SecurityUtils.getUserId());
        yunStuLeave.setCreateBy(SecurityUtils.getUsername());
        return toAjax(yunStuLeaveService.insertYunStuLeave(yunStuLeave));
    }

    /**
     * 修改学生请假
     */
    @PreAuthorize("@ss.hasPermi('parents:leave:edit')")
    @Log(title = "学生请假", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunStuLeave yunStuLeave)
    {
        yunStuLeave.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(yunStuLeaveService.updateYunStuLeave(yunStuLeave));
    }

    /**
     * 删除学生请假
     */
    @PreAuthorize("@ss.hasPermi('parents:leave:remove')")
    @Log(title = "学生请假", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunStuLeaveService.deleteYunStuLeaveByIds(ids));
    }

}
