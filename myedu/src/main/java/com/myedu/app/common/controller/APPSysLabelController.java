package com.myedu.app.common.controller;

import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.dataBasic.domain.SysLabel;
import com.myedu.project.dataBasic.service.ISysLabelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 标签管理Controller
 * 
 * @author 梁龙飞
 * @date 2020-01-04
 */
@Api("门店标签基础接口")
@RestController
@RequestMapping("/app/dataBasic/label")
public class APPSysLabelController extends BaseController
{
    @Autowired
    private ISysLabelService sysLabelService;

    /**
     * 查询标签管理列表
     */
    @ApiOperation("查询门店标签基础列表")
    @ApiImplicitParam(name = "sysLabel", value = "查询门店标签基础列表",
            dataType = "SysLabel")
    @GetMapping("/list")
    public TableDataInfo list(SysLabel sysLabel)
    {
        startPage();
        List<SysLabel> list = sysLabelService.selectSysLabelList(sysLabel);
        return getDataTable(list);
    }

//    /**
//     * 导出标签管理列表
//     */
//    @PreAuthorize("@ss.hasPermi('dataBasic:label:export')")
//    @Log(title = "标签管理", businessType = BusinessType.EXPORT)
//    @GetMapping("/export")
//    public AjaxResult export(SysLabel sysLabel)
//    {
//        List<SysLabel> list = sysLabelService.selectSysLabelList(sysLabel);
//        ExcelUtil<SysLabel> util = new ExcelUtil<SysLabel>(SysLabel.class);
//        return util.exportExcel(list, "label");
//    }

//    /**
//     * 获取标签管理详细信息
//     */
//    @PreAuthorize("@ss.hasPermi('dataBasic:label:query')")
//    @GetMapping(value = "/{id}")
//    public AjaxResult getInfo(@PathVariable("id") Long id)
//    {
//        return AjaxResult.success(sysLabelService.selectSysLabelById(id));
//    }
//
//    /**
//     * 新增标签管理
//     */
//    @PreAuthorize("@ss.hasPermi('dataBasic:label:add')")
//    @Log(title = "标签管理", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody SysLabel sysLabel)
//    {
//        sysLabel.setCreateBy(SecurityUtils.getUsername());
//        sysLabel.setCreateById(SecurityUtils.getUserId());
//        return toAjax(sysLabelService.insertSysLabel(sysLabel));
//    }
//
//    /**
//     * 修改标签管理
//     */
//    @PreAuthorize("@ss.hasPermi('dataBasic:label:edit')")
//    @Log(title = "标签管理", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@RequestBody SysLabel sysLabel)
//    {
//        sysLabel.setUpdateBy(
//                SecurityUtils.getUsername());
//        return toAjax(sysLabelService.updateSysLabel(sysLabel));
//    }
//
//    /**
//     * 删除标签管理
//     */
//    @PreAuthorize("@ss.hasPermi('dataBasic:label:remove')")
//    @Log(title = "标签管理", businessType = BusinessType.DELETE)
//	@DeleteMapping("/{ids}")
//    public AjaxResult remove(@PathVariable Long[] ids)
//    {
//        return toAjax(sysLabelService.deleteSysLabelByIds(ids));
//    }
}
