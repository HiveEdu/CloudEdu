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
import com.myedu.project.dataBasic.domain.SysStoreType;
import com.myedu.project.dataBasic.service.ISysStoreTypeService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.web.page.TableDataInfo;

/**
 * 门店类型Controller
 * 
 * @author 梁少鹏
 * @date 2019-12-29
 */
@RestController
@RequestMapping("/dataBasic/storeType")
public class SysStoreTypeController extends BaseController
{
    @Autowired
    private ISysStoreTypeService sysStoreTypeService;

    /**
     * 查询门店类型列表
     */
    @PreAuthorize("@ss.hasPermi('dataBasic:storeType:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysStoreType sysStoreType)
    {
        startPage();
        List<SysStoreType> list = sysStoreTypeService.selectSysStoreTypeList(sysStoreType);
        return getDataTable(list);
    }

    /**
     * 导出门店类型列表
     */
    @PreAuthorize("@ss.hasPermi('dataBasic:storeType:export')")
    @Log(title = "门店类型", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysStoreType sysStoreType)
    {
        List<SysStoreType> list = sysStoreTypeService.selectSysStoreTypeList(sysStoreType);
        ExcelUtil<SysStoreType> util = new ExcelUtil<SysStoreType>(SysStoreType.class);
        return util.exportExcel(list, "storeType");
    }

    /**
     * 获取门店类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('dataBasic:storeType:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysStoreTypeService.selectSysStoreTypeById(id));
    }

    /**
     * 新增门店类型
     */
    @PreAuthorize("@ss.hasPermi('dataBasic:storeType:add')")
    @Log(title = "门店类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysStoreType sysStoreType)
    {
        sysStoreType.setCreateBy(SecurityUtils.getUsername());
        return toAjax(sysStoreTypeService.insertSysStoreType(sysStoreType));
    }

    /**
     * 修改门店类型
     */
    @PreAuthorize("@ss.hasPermi('dataBasic:storeType:edit')")
    @Log(title = "门店类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysStoreType sysStoreType)
    {
        sysStoreType.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(sysStoreTypeService.updateSysStoreType(sysStoreType));
    }

    /**
     * 删除门店类型
     */
    @PreAuthorize("@ss.hasPermi('dataBasic:storeType:remove')")
    @Log(title = "门店类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysStoreTypeService.deleteSysStoreTypeByIds(ids));
    }
}
