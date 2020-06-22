package com.myedu.app.common.controller;

import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.aspectj.lang.annotation.AutoIdempotent;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.dataBasic.domain.SysMemberLevel;
import com.myedu.project.dataBasic.service.ISysMemberLevelService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员等级Controller
 * 
 * @author 梁少鹏
 * @date 2020-03-14
 */
@Api("会员等级基础接口")
@RestController
@RequestMapping("/app/dataBasic/leave")
public class APPSysMemberLevelController extends BaseController
{
    @Autowired
    private ISysMemberLevelService sysMemberLevelService;

    /**
     * 查询会员等级列表
     */
    @AutoIdempotent
    @GetMapping("/list")
    public TableDataInfo list(SysMemberLevel sysMemberLevel)
    {
        startPage();
        List<SysMemberLevel> list = sysMemberLevelService.selectSysMemberLevelList(sysMemberLevel);
        return getDataTable(list);
    }

    /**
     * 导出会员等级列表
     */
    @PreAuthorize("@ss.hasPermi('dataBasic:leave:export')")
    @Log(title = "会员等级", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysMemberLevel sysMemberLevel)
    {
        List<SysMemberLevel> list = sysMemberLevelService.selectSysMemberLevelList(sysMemberLevel);
        ExcelUtil<SysMemberLevel> util = new ExcelUtil<SysMemberLevel>(SysMemberLevel.class);
        return util.exportExcel(list, "memberLevel");
    }

    /**
     * 获取会员等级详细信息
     */
    @PreAuthorize("@ss.hasPermi('dataBasic:leave:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysMemberLevelService.selectSysMemberLevelById(id));
    }

    /**
     * 新增会员等级
     */
    @PreAuthorize("@ss.hasPermi('dataBasic:leave:add')")
    @Log(title = "会员等级", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysMemberLevel sysMemberLevel)
    {
        return toAjax(sysMemberLevelService.insertSysMemberLevel(sysMemberLevel));
    }

    /**
     * 修改会员等级
     */
    @PreAuthorize("@ss.hasPermi('dataBasic:leave:edit')")
    @Log(title = "会员等级", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysMemberLevel sysMemberLevel)
    {
        return toAjax(sysMemberLevelService.updateSysMemberLevel(sysMemberLevel));
    }

    /**
     * 删除会员等级
     */
    @PreAuthorize("@ss.hasPermi('dataBasic:leave:remove')")
    @Log(title = "会员等级", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysMemberLevelService.deleteSysMemberLevelByIds(ids));
    }
}
