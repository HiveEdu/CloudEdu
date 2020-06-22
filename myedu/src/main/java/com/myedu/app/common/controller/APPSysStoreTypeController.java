package com.myedu.app.common.controller;

import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.dataBasic.domain.SysStoreType;
import com.myedu.project.dataBasic.service.ISysStoreTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 门店类型Controller
 * 
 * @author 梁少鹏
 * @date 2019-12-29
 */
@Api("门店类型基础接口")
@RestController
@RequestMapping("/app/dataBasic/storeType")
public class APPSysStoreTypeController extends BaseController
{
    @Autowired
    private ISysStoreTypeService sysStoreTypeService;

    /**
     * 查询门店类型列表
     */
    @ApiOperation("查询门店类型基础列表")
    @ApiImplicitParam(name = "sysStoreType", value = "查询门店类型基础列表",
            dataType = "SysStoreType")
    @GetMapping("/list")
    public TableDataInfo list(SysStoreType sysStoreType)
    {
        startPage();
        List<SysStoreType> list = sysStoreTypeService.selectSysStoreTypeList(sysStoreType);
        return getDataTable(list);
    }

//    /**
//     * 导出门店类型列表
//     */
//    @PreAuthorize("@ss.hasPermi('dataBasic:storeType:export')")
//    @Log(title = "门店类型", businessType = BusinessType.EXPORT)
//    @GetMapping("/export")
//    public AjaxResult export(SysStoreType sysStoreType)
//    {
//        List<SysStoreType> list = sysStoreTypeService.selectSysStoreTypeList(sysStoreType);
//        ExcelUtil<SysStoreType> util = new ExcelUtil<SysStoreType>(SysStoreType.class);
//        return util.exportExcel(list, "storeType");
//    }

    /**
     * 获取门店类型详细信息
     */
    @ApiOperation("获取门店类型详细信息")
    @ApiImplicitParam(name = "id", value = "获取门店类型详细信息",
            dataType = "Long")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysStoreTypeService.selectSysStoreTypeById(id));
    }

//    /**
//     * 新增门店类型
//     */
//    @ApiOperation("新增门店类型")
//    @ApiImplicitParam(name = "sysStoreType", value = "新增门店类型",
//            dataType = "SysStoreType")
//    @Log(title = "门店类型", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody SysStoreType sysStoreType)
//    {
//        sysStoreType.setCreateBy(SecurityUtils.getUsername());
//        return toAjax(sysStoreTypeService.insertSysStoreType(sysStoreType));
//    }
//
//    /**
//     * 修改门店类型
//     */
//    @ApiOperation("新增门店类型")
//    @ApiImplicitParam(name = "sysStoreType", value = "新增门店类型",
//            dataType = "SysStoreType")
//    @Log(title = "门店类型", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@RequestBody SysStoreType sysStoreType)
//    {
//        sysStoreType.setUpdateBy(SecurityUtils.getUsername());
//        return toAjax(sysStoreTypeService.updateSysStoreType(sysStoreType));
//    }

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
