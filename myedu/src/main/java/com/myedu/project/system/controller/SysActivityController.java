package com.myedu.project.system.controller;

import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.system.domain.SysActivity;
import com.myedu.project.system.service.ISysActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 活动管理Controller
 * 
 * @author myedu
 * @date 2020-07-22
 */
@RestController
@RequestMapping("/system/activity")
public class SysActivityController extends BaseController
{
    @Autowired
    private ISysActivityService sysActivityService;

    /**
     * 查询活动管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:activity:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysActivity sysActivity)
    {
        startPage();
        List<SysActivity> list = sysActivityService.selectSysActivityList(sysActivity);
        return getDataTable(list);
    }

    /**
     * 导出活动管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:activity:export')")
    @Log(title = "活动管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysActivity sysActivity)
    {
        List<SysActivity> list = sysActivityService.selectSysActivityList(sysActivity);
        ExcelUtil<SysActivity> util = new ExcelUtil<SysActivity>(SysActivity.class);
        return util.exportExcel(list, "activity");
    }

    /**
     * 获取活动管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:activity:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysActivityService.selectSysActivityById(id));
    }

    /**
     * 新增活动管理
     */
    @PreAuthorize("@ss.hasPermi('system:activity:add')")
    @Log(title = "活动管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysActivity sysActivity)
    {
        return toAjax(sysActivityService.insertSysActivity(sysActivity));
    }

    /**
     * 修改活动管理
     */
    @PreAuthorize("@ss.hasPermi('system:activity:edit')")
    @Log(title = "活动管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysActivity sysActivity)
    {
        return toAjax(sysActivityService.updateSysActivity(sysActivity));
    }

    /**
     * 删除活动管理
     */
    @PreAuthorize("@ss.hasPermi('system:activity:remove')")
    @Log(title = "活动管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysActivityService.deleteSysActivityByIds(ids));
    }
}
