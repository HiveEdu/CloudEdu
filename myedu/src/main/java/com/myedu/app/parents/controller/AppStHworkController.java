package com.myedu.app.parents.controller;

import com.myedu.common.utils.SecurityUtils;
import com.myedu.common.utils.StringUtils;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.parents.domain.YunStuHwork;
import com.myedu.project.parents.domain.vo.YunStuHworkVo;
import com.myedu.project.parents.domain.vo.YunStudentVo;
import com.myedu.project.parents.service.IYunStuHworkService;
import com.myedu.project.parents.service.IYunStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学生作业Controller
 * 
 * @author 梁少鹏
 * @date 2019-12-29
 */
@Api("APP学生作业管理")
@RestController
@RequestMapping("/app/parents/hwork")
public class AppStHworkController extends BaseController
{
    @Autowired
    private IYunStuHworkService yunStuHworkService;
    @Autowired
    private IYunStudentService yunStudentService;
    /**
     * 查询学生作业列表
     */
    @ApiOperation("查询学生作业列表")
    @ApiImplicitParam(name = "yunStuHwork", value = "查询学生作业列表",
            dataType = "YunStuHworkVo")
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
    @ApiOperation("导出学生作业列表")
    @ApiImplicitParam(name = "yunStuHwork", value = "查询学生作业列表",
            dataType = "YunStuHworkVo")
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
    @ApiOperation("获取学生作业详细信息")
    @ApiImplicitParam(name = "id", value = "获取学生作业详细信息",
            dataType = "Long")
    @PreAuthorize("@ss.hasPermi('parents:hwork:query')")
    @GetMapping(value = { "/", "/{id}" })
    public AjaxResult getInfo(@PathVariable(value ="id", required = false) Long id)
    {
        AjaxResult ajax = AjaxResult.success();
        YunStudentVo yunStudentVo=new YunStudentVo();
        yunStudentVo.setCreateById(SecurityUtils.getUserId());
        List<YunStudentVo> list = yunStudentService.selectYunStudentList(yunStudentVo);
        ajax.put("studentLists", list);
        if (StringUtils.isNotNull(id))
        {
            ajax.put(AjaxResult.DATA_TAG, yunStuHworkService.selectYunStuHworkById(id));
        }
        return ajax;
    }

    /**
     * 新增学生作业
     */
    @ApiOperation("新增学生作业")
    @ApiImplicitParam(name = "yunStuHwork", value = "新增学生作业",
            dataType = "YunStuHwork")
    @PreAuthorize("@ss.hasPermi('parents:hwork:add')")
    @Log(title = "学生作业", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunStuHwork yunStuHwork)
    {   yunStuHwork.setCreateById(SecurityUtils.getUserId());
        yunStuHwork.setCreateBy(SecurityUtils.getUsername());
        return toAjax(yunStuHworkService.insertYunStuHwork(yunStuHwork));
    }

    /**
     * 修改学生作业
     */
    @ApiOperation("修改学生作业")
    @ApiImplicitParam(name = "yunStuHwork", value = "修改学生作业",
            dataType = "YunStuHwork")
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
    @ApiOperation("删除学生作业")
    @ApiImplicitParam(name = "ids", value = "删除学生作业",
            dataType = "Long[]")
    @PreAuthorize("@ss.hasPermi('parents:hwork:remove')")
    @Log(title = "学生作业", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunStuHworkService.deleteYunStuHworkByIds(ids));
    }
}
