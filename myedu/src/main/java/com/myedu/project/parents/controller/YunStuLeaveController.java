package com.myedu.project.parents.controller;

import java.util.List;

import com.myedu.common.utils.SecurityUtils;
import com.myedu.project.parents.domain.YunStudent;
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
import com.myedu.project.parents.domain.YunStuLeave;
import com.myedu.project.parents.service.IYunStuLeaveService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.web.page.TableDataInfo;

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

    /**
     * 查询学生请假列表
     */
    @PreAuthorize("@ss.hasPermi('parents:leave:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunStuLeave yunStuLeave)
    {
        startPage();
        List<YunStuLeave> list = yunStuLeaveService.selectYunStuLeaveList(yunStuLeave);
        return getDataTable(list);
    }

    /**
     * 导出学生请假列表
     */
    @PreAuthorize("@ss.hasPermi('parents:leave:export')")
    @Log(title = "学生请假", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunStuLeave yunStuLeave)
    {
        List<YunStuLeave> list = yunStuLeaveService.selectYunStuLeaveList(yunStuLeave);
        ExcelUtil<YunStuLeave> util = new ExcelUtil<YunStuLeave>(YunStuLeave.class);
        return util.exportExcel(list, "leave");
    }

    /**
     * 获取学生请假详细信息
     */
    @PreAuthorize("@ss.hasPermi('parents:leave:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yunStuLeaveService.selectYunStuLeaveById(id));
    }

    /**
     * 新增学生请假
     */
    @PreAuthorize("@ss.hasPermi('parents:leave:add')")
    @Log(title = "学生请假", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunStuLeave yunStuLeave)
    {
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
