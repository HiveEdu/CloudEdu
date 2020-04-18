package com.myedu.project.store.controller;

import com.myedu.common.utils.SecurityUtils;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.store.domain.YunStoreClass;
import com.myedu.project.store.domain.vo.YunStoreClassVo;
import com.myedu.project.store.service.IYunStoreClassService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分班管理Controller
 * 
 * @author 梁龙飞
 * @date 2020-04-18
 */
@RestController
@RequestMapping("/store/class")
public class YunStoreClassController extends BaseController
{
    @Autowired
    private IYunStoreClassService yunStoreClassService;

    /**
     * 查询分班管理列表
     */
    @ApiOperation("查询分班管理列表")
    @ApiImplicitParam(name = "yunStoreClass", value = "查询分班管理列表",
            dataType = "YunStoreClass")
    @GetMapping("/list")
    public TableDataInfo list(YunStoreClass yunStoreClass)
    {
        startPage();
        List<YunStoreClassVo> list = yunStoreClassService.selectYunStoreClassList(yunStoreClass);
        return getDataTable(list);
    }

    /**
     * 导出分班管理列表
     */
    @ApiOperation("导出分班管理列表")
    @ApiImplicitParam(name = "yunStoreClass", value = "导出分班管理列表",
            dataType = "YunStoreClass")
    @Log(title = "分班管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunStoreClass yunStoreClass)
    {
        List<YunStoreClassVo> list = yunStoreClassService.selectYunStoreClassList(yunStoreClass);
        ExcelUtil<YunStoreClassVo> util = new ExcelUtil<YunStoreClassVo>(YunStoreClassVo.class);
        return util.exportExcel(list, "class");
    }

    /**
     * 获取分班管理详细信息
     */
    @ApiOperation("获取分班管理详细信息")
    @ApiImplicitParam(name = "id", value = "获取分班管理详细信息",
            dataType = "Long", required = true, paramType = "path")
    @GetMapping(value = { "/", "/{id}" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yunStoreClassService.selectYunStoreClassById(id));
    }

    /**
     * 新增分班管理
     */
    @ApiOperation("新增分班管理")
    @ApiImplicitParam(name = "yunStoreClass", value = "新增分班管理",
            dataType = "YunStoreClass")
    @Log(title = "分班管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunStoreClass yunStoreClass)
    {
        yunStoreClass.setCreateById(SecurityUtils.getUserId());
        yunStoreClass.setCreateBy(SecurityUtils.getUsername());
        return toAjax(yunStoreClassService.insertYunStoreClass(yunStoreClass));
    }

    /**
     * 修改分班管理
     */
    @ApiOperation("修改分班管理")
    @ApiImplicitParam(name = "yunStoreClass", value = "修改分班管理",
            dataType = "YunStoreClass")
    @Log(title = "分班管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunStoreClass yunStoreClass)
    {
        yunStoreClass.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(yunStoreClassService.updateYunStoreClass(yunStoreClass));
    }

    /**
     * 删除分班管理
     */
    @ApiOperation("删除分班管理")
    @ApiImplicitParam(name = "ids", value = "删除分班管理",
            dataType = "Long[]")
    @Log(title = "分班管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunStoreClassService.deleteYunStoreClassByIds(ids));
    }
}
