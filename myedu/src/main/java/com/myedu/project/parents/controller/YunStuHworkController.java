package com.myedu.project.parents.controller;

import java.util.List;

import com.myedu.project.parents.domain.vo.YunStuHworkVo;
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
import com.myedu.project.parents.domain.YunStuHwork;
import com.myedu.project.parents.service.IYunStuHworkService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.web.page.TableDataInfo;

/**
 * 学生作业Controller
 * 
 * @author 梁少鹏
 * @date 2019-12-29
 */
@RestController
@RequestMapping("/parents/hwork")
public class YunStuHworkController extends BaseController
{
    @Autowired
    private IYunStuHworkService yunStuHworkService;

    /**
     * 查询学生作业列表
     */
    @PreAuthorize("@ss.hasPermi('parents:hwork:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunStuHworkVo yunStuHwork)
    {
        startPage();
        List<YunStuHworkVo> list = yunStuHworkService.selectYunStuHworkList(yunStuHwork);
        return getDataTable(list);
    }

    /**
     * 导出学生作业列表
     */
    @PreAuthorize("@ss.hasPermi('parents:hwork:export')")
    @Log(title = "学生作业", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunStuHworkVo yunStuHwork)
    {
        List<YunStuHworkVo> list = yunStuHworkService.selectYunStuHworkList(yunStuHwork);
        ExcelUtil<YunStuHworkVo> util = new ExcelUtil<YunStuHworkVo>(YunStuHworkVo.class);
        return util.exportExcel(list, "hwork");
    }

    /**
     * 获取学生作业详细信息
     */
    @PreAuthorize("@ss.hasPermi('parents:hwork:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yunStuHworkService.selectYunStuHworkById(id));
    }

    /**
     * 新增学生作业
     */
    @PreAuthorize("@ss.hasPermi('parents:hwork:add')")
    @Log(title = "学生作业", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunStuHwork yunStuHwork)
    {
        return toAjax(yunStuHworkService.insertYunStuHwork(yunStuHwork));
    }

    /**
     * 修改学生作业
     */
    @PreAuthorize("@ss.hasPermi('parents:hwork:edit')")
    @Log(title = "学生作业", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunStuHwork yunStuHwork)
    {
        return toAjax(yunStuHworkService.updateYunStuHwork(yunStuHwork));
    }

    /**
     * 删除学生作业
     */
    @PreAuthorize("@ss.hasPermi('parents:hwork:remove')")
    @Log(title = "学生作业", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunStuHworkService.deleteYunStuHworkByIds(ids));
    }
}
