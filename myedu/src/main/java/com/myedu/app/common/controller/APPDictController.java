package com.myedu.app.common.controller;

import com.myedu.app.parents.Vo.StoreClassVo;
import com.myedu.framework.aspectj.lang.annotation.AutoIdempotent;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.dataBasic.service.ISysStoreTypeService;
import com.myedu.project.system.domain.SysDictData;
import com.myedu.project.system.service.ISysDictDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ${梁少鹏}
 * Date: 2020/5/3
 * Time: 22:13
 * Description:
 */
@Api("获取字典数据")
@RestController
@RequestMapping("/app/dict")
public class APPDictController extends BaseController {
    @Autowired
    private ISysDictDataService dictDataService;
    @Autowired
    private ISysStoreTypeService sysStoreTypeService;
    /**
     * 根据字典类型查询字典数据信息
     */
    @AutoIdempotent
    @ApiOperation("根据字典类型查询字典数据信息")
    @ApiImplicitParam(name = "dictType", value = "根据字典类型查询字典数据信息",
            dataType = "String")
    @GetMapping(value = "/dictType/{dictType}")
    public AjaxResult dictType(@PathVariable String dictType)
    {
        return AjaxResult.success(dictDataService.selectDictDataByType(dictType));
    }

    /**
     * 根据字典类型查询字典数据信息
     */
    @AutoIdempotent
    @ApiOperation("获取所有字典项")
    @GetMapping(value = "/dictType/all")
    public AjaxResult dictType()
    {
        SysDictData sysDictData=new SysDictData();
        return AjaxResult.success(dictDataService.selectDictDataList(sysDictData));
    }



    /**
     * 查询课程基本数据列表
     */
    @AutoIdempotent
    @ApiOperation("门店类型下的课程字典")
    @ApiImplicitParam(name = "getStoreClass", value = "查询投诉列表")
    @GetMapping("/list")
    public TableDataInfo list()
    {
        startPage();
        List<StoreClassVo> list = sysStoreTypeService.selectStoreClass();
        return getDataTable(list);
    }
}
