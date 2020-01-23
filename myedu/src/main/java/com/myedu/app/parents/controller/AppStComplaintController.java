package com.myedu.app.parents.controller;

import com.myedu.common.utils.SecurityUtils;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.parents.domain.YunComplaint;
import com.myedu.project.parents.service.IYunComplaintService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 投诉Controller
 * 
 * @author myedu
 * @date 2020-01-18
 */
@Api("APP家长投诉管理")
@RestController
@RequestMapping("/app/parents/complaint")
public class AppStComplaintController extends BaseController
{
    @Autowired
    private IYunComplaintService yunComplaintService;

    /**
     * 查询投诉列表
     */
    @ApiOperation("查询投诉列表")
    @ApiImplicitParam(name = "yunComplaint", value = "查询投诉列表",
            dataType = "YunComplaint")
    @PreAuthorize("@ss.hasPermi('parents:complaint:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunComplaint yunComplaint)
    {
        startPage();
        List<YunComplaint> list = yunComplaintService.selectYunComplaintList(yunComplaint);
        return getDataTable(list);
    }

    /**
     * 导出投诉列表
     */
    @ApiOperation("导出投诉列表")
    @ApiImplicitParam(name = "yunComplaint", value = "导出投诉列表",
            dataType = "YunComplaint")
    @PreAuthorize("@ss.hasPermi('parents:complaint:export')")
    @Log(title = "投诉", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunComplaint yunComplaint)
    {
        List<YunComplaint> list = yunComplaintService.selectYunComplaintList(yunComplaint);
        ExcelUtil<YunComplaint> util = new ExcelUtil<YunComplaint>(YunComplaint.class);
        return util.exportExcel(list, "complaint");
    }

    /**
     * 获取投诉详细信息
     */
    @ApiOperation("获取投诉详细信息")
    @ApiImplicitParam(name = "id", value = "获取投诉详细信息",
            dataType = "Long")
    @PreAuthorize("@ss.hasPermi('parents:complaint:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yunComplaintService.selectYunComplaintById(id));
    }

    /**
     * 新增投诉
     */
    @ApiOperation("新增投诉")
    @ApiImplicitParam(name = "yunComplaint", value = "新增投诉",
            dataType = "YunComplaint")
    @PreAuthorize("@ss.hasPermi('parents:complaint:add')")
    @Log(title = "投诉", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunComplaint yunComplaint)
    {
        yunComplaint.setCreateById(SecurityUtils.getUserId());
        yunComplaint.setCreateBy(SecurityUtils.getUsername());
        return toAjax(yunComplaintService.insertYunComplaint(yunComplaint));
    }

    /**
     * 修改投诉
     */
    @ApiOperation("修改投诉")
    @ApiImplicitParam(name = "yunComplaint", value = "修改投诉",
            dataType = "YunComplaint")
    @PreAuthorize("@ss.hasPermi('parents:complaint:edit')")
    @Log(title = "投诉", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunComplaint yunComplaint)
    {
        return toAjax(yunComplaintService.updateYunComplaint(yunComplaint));
    }

    /**
     * 删除投诉
     */
    @ApiOperation("删除投诉")
    @ApiImplicitParam(name = "ids", value = "删除投诉",
            dataType = "Long[]")
    @PreAuthorize("@ss.hasPermi('parents:complaint:remove')")
    @Log(title = "投诉", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunComplaintService.deleteYunComplaintByIds(ids));
    }
}
