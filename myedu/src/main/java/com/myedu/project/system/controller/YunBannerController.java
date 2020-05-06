package com.myedu.project.system.controller;

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
import com.myedu.project.system.domain.YunBanner;
import com.myedu.project.system.service.IYunBannerService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.web.page.TableDataInfo;

/**
 * 轮廓图设置Controller
 * 
 * @author lsp
 * @date 2020-05-06
 */
@RestController
@RequestMapping("/system/banner")
public class YunBannerController extends BaseController
{
    @Autowired
    private IYunBannerService yunBannerService;

    /**
     * 查询轮廓图设置列表
     */
    @PreAuthorize("@ss.hasPermi('system:banner:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunBanner yunBanner)
    {
        startPage();
        List<YunBanner> list = yunBannerService.selectYunBannerList(yunBanner);
        return getDataTable(list);
    }

    /**
     * 导出轮廓图设置列表
     */
    @PreAuthorize("@ss.hasPermi('system:banner:export')")
    @Log(title = "轮廓图设置", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunBanner yunBanner)
    {
        List<YunBanner> list = yunBannerService.selectYunBannerList(yunBanner);
        ExcelUtil<YunBanner> util = new ExcelUtil<YunBanner>(YunBanner.class);
        return util.exportExcel(list, "banner");
    }

    /**
     * 获取轮廓图设置详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:banner:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yunBannerService.selectYunBannerById(id));
    }

    /**
     * 新增轮廓图设置
     */
    @PreAuthorize("@ss.hasPermi('system:banner:add')")
    @Log(title = "轮廓图设置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunBanner yunBanner)
    {
        yunBanner.setCreateById(SecurityUtils.getUserId());
        yunBanner.setCreateBy(SecurityUtils.getUsername());
        return toAjax(yunBannerService.insertYunBanner(yunBanner));
    }

    /**
     * 修改轮廓图设置
     */
    @PreAuthorize("@ss.hasPermi('system:banner:edit')")
    @Log(title = "轮廓图设置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunBanner yunBanner)
    {
        return toAjax(yunBannerService.updateYunBanner(yunBanner));
    }

    /**
     * 删除轮廓图设置
     */
    @PreAuthorize("@ss.hasPermi('system:banner:remove')")
    @Log(title = "轮廓图设置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunBannerService.deleteYunBannerByIds(ids));
    }
}
