package com.myedu.project.parents.controller;

import com.myedu.common.utils.SecurityUtils;
import com.myedu.common.utils.StringUtils;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.parents.domain.YunStuHw;
import com.myedu.project.parents.domain.vo.YunStuHwVo;
import com.myedu.project.parents.domain.vo.YunStudentVo;
import com.myedu.project.parents.service.IYunStuHwService;
import com.myedu.project.parents.service.IYunStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 学生身高体重记录Controller
 * 
 * @author  llf
 * @date 2019-12-29
 */
@RestController
@RequestMapping("/parents/hw")
public class YunStuHwController extends BaseController
{
    @Autowired
    private IYunStuHwService yunStuHwService;
    @Autowired
    private IYunStudentService yunStudentService;
    /**
     * 查询学生身高体重记录列表
     */
    @PreAuthorize("@ss.hasPermi('parents:hw:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunStuHwVo yunStuHw)
    {
        startPage();
        List<YunStuHwVo> list = yunStuHwService.selectYunStuHwList(yunStuHw);
        return getDataTable(list);
    }

    /**
     * 导出学生身高体重记录列表
     */
    @PreAuthorize("@ss.hasPermi('parents:hw:export')")
    @Log(title = "学生身高体重记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunStuHwVo yunStuHw)
    {
        List<YunStuHwVo> list = yunStuHwService.selectYunStuHwList(yunStuHw);
        ExcelUtil<YunStuHwVo> util = new ExcelUtil<YunStuHwVo>(YunStuHwVo.class);
        return util.exportExcel(list, "hw");
    }

    /**
     * 获取学生身高体重记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('parents:hw:query')")
    @GetMapping(value = { "/", "/{id}" })
    public AjaxResult getInfo(@PathVariable(value ="id", required = false) Long id)
    {    AjaxResult ajax = AjaxResult.success();
        YunStudentVo yunStudentVo=new YunStudentVo();
        yunStudentVo.setCreateById(SecurityUtils.getUserId());
        List<YunStudentVo> list = yunStudentService.selectYunStudentList(yunStudentVo);
        ajax.put("studentLists", list);
        if (StringUtils.isNotNull(id))
        {
            ajax.put(AjaxResult.DATA_TAG, yunStuHwService.selectYunStuHwById(id));
        }
        return ajax;
    }

    /**
     * 新增学生身高体重记录
     */
    @PreAuthorize("@ss.hasPermi('parents:hw:add')")
    @Log(title = "学生身高体重记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Valid @RequestBody YunStuHw yunStuHw)

    {   yunStuHw.setCreateById(SecurityUtils.getUserId());
        yunStuHw.setCreateBy(SecurityUtils.getUsername());
        return toAjax(yunStuHwService.insertYunStuHw(yunStuHw));
    }

    /**
     * 修改学生身高体重记录
     */
    @PreAuthorize("@ss.hasPermi('parents:hw:edit')")
    @Log(title = "学生身高体重记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunStuHw yunStuHw)
    {
        return toAjax(yunStuHwService.updateYunStuHw(yunStuHw));
    }

    /**
     * 删除学生身高体重记录
     */
    @PreAuthorize("@ss.hasPermi('parents:hw:remove')")
    @Log(title = "学生身高体重记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunStuHwService.deleteYunStuHwByIds(ids));
    }
}
