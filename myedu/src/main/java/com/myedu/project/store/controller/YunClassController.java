package com.myedu.project.store.controller;

import java.util.List;
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
import com.myedu.project.store.domain.YunClass;
import com.myedu.project.store.service.IYunClassService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.web.page.TableDataInfo;

/**
 * 课程设置Controller
 * 
 * @author myedu
 * @date 2020-01-11
 */
@RestController
@RequestMapping("/yun_class/class")
public class YunClassController extends BaseController
{
    @Autowired
    private IYunClassService yunClassService;

    /**
     * 查询课程设置列表
     */
    @PreAuthorize("@ss.hasPermi('yun_class:class:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunClass yunClass)
    {
        startPage();
        List<YunClass> list = yunClassService.selectYunClassList(yunClass);
        return getDataTable(list);
    }

    /**
     * 导出课程设置列表
     */
    @PreAuthorize("@ss.hasPermi('yun_class:class:export')")
    @Log(title = "课程设置", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunClass yunClass)
    {
        List<YunClass> list = yunClassService.selectYunClassList(yunClass);
        ExcelUtil<YunClass> util = new ExcelUtil<YunClass>(YunClass.class);
        return util.exportExcel(list, "class");
    }

    /**
     * 获取课程设置详细信息
     */
    @PreAuthorize("@ss.hasPermi('yun_class:class:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yunClassService.selectYunClassById(id));
    }

    /**
     * 新增课程设置
     */
    @PreAuthorize("@ss.hasPermi('yun_class:class:add')")
    @Log(title = "课程设置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunClass yunClass)
    {
        return toAjax(yunClassService.insertYunClass(yunClass));
    }

    /**
     * 修改课程设置
     */
    @PreAuthorize("@ss.hasPermi('yun_class:class:edit')")
    @Log(title = "课程设置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunClass yunClass)
    {
        return toAjax(yunClassService.updateYunClass(yunClass));
    }

    /**
     * 删除课程设置
     */
    @PreAuthorize("@ss.hasPermi('yun_class:class:remove')")
    @Log(title = "课程设置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunClassService.deleteYunClassByIds(ids));
    }
}
