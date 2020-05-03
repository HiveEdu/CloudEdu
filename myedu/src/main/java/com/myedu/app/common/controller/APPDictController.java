package com.myedu.app.common.controller;

import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.project.system.service.ISysDictDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: ${梁少鹏}
 * Date: 2020/5/3
 * Time: 22:13
 * Description:
 */
@Api("获取字典数据")
@RestController
@RequestMapping("/dict")
public class APPDictController extends BaseController {
    @Autowired
    private ISysDictDataService dictDataService;
    /**
     * 根据字典类型查询字典数据信息
     */
    @ApiOperation("根据字典类型查询字典数据信息")
    @ApiImplicitParam(name = "dictType", value = "根据字典类型查询字典数据信息",
            dataType = "Long")
    @GetMapping(value = "/dictType/{dictType}")
    public AjaxResult dictType(@PathVariable String dictType)
    {
        return AjaxResult.success(dictDataService.selectDictDataByType(dictType));
    }
}
