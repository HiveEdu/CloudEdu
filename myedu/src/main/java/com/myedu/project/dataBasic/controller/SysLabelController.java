package com.myedu.project.dataBasic.controller;

import java.util.List;

import com.myedu.common.utils.SecurityUtils;
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
import com.myedu.project.dataBasic.domain.SysLabel;
import com.myedu.project.dataBasic.service.ISysLabelService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.web.page.TableDataInfo;

/**
 * 标签管理Controller
 * 
 * @author 梁龙飞
 * @date 2020-01-04
 */
@RestController
@RequestMapping("/dataBasic/label")
public class SysLabelController extends BaseController
{
    @Autowired
    private ISysLabelService sysLabelService;

    /**
     * 查询标签管理列表
     */
    @PreAuthorize("@ss.hasPermi('dataBasic:label:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysLabel sysLabel)
    {
        startPage();
        List<SysLabel> list = sysLabelService.selectSysLabelList(sysLabel);
        return getDataTable(list);
    }

    /**
     * 导出标签管理列表
     */
    @PreAuthorize("@ss.hasPermi('dataBasic:label:export')")
    @Log(title = "标签管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysLabel sysLabel)
    {
        List<SysLabel> list = sysLabelService.selectSysLabelList(sysLabel);
        ExcelUtil<SysLabel> util = new ExcelUtil<SysLabel>(SysLabel.class);
        return util.exportExcel(list, "label");
    }

    /**
     * 获取标签管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('dataBasic:label:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysLabelService.selectSysLabelById(id));
    }

    /**
     * 新增标签管理
     */
    @PreAuthorize("@ss.hasPermi('dataBasic:label:add')")
    @Log(title = "标签管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysLabel sysLabel)
    {
        sysLabel.setCreateBy(SecurityUtils.getUsername());
        sysLabel.setCreateById(SecurityUtils.getUserId());
        return toAjax(sysLabelService.insertSysLabel(sysLabel));
    }

    /**
     * 修改标签管理
     */
    @PreAuthorize("@ss.hasPermi('dataBasic:label:edit')")
    @Log(title = "标签管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysLabel sysLabel)
    {
        sysLabel.setUpdateBy(
                SecurityUtils.getUsername());
        return toAjax(sysLabelService.updateSysLabel(sysLabel));
    }

    /**
     * 删除标签管理
     */
    @PreAuthorize("@ss.hasPermi('dataBasic:label:remove')")
    @Log(title = "标签管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysLabelService.deleteSysLabelByIds(ids));
    }
}
