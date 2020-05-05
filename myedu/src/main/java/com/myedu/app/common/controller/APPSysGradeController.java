package com.myedu.app.common.controller;

import com.myedu.common.utils.StringUtils;
import com.myedu.framework.aspectj.lang.annotation.AutoIdempotent;
import com.myedu.framework.security.service.TokenService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.dataBasic.domain.SysGrade;
import com.myedu.project.dataBasic.service.ISysGradeService;
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
 * 年级基础Controller
 * 
 * @author myedu
 * @date 2019-12-26
 */
@Api("年级基础接口")
@RestController
@RequestMapping("/app/dataBasic/grade")
public class APPSysGradeController extends BaseController
{
    @Autowired
    private ISysGradeService sysGradeService;
    @Autowired
    private TokenService tokenService;
    /**
     * 查询年级基础列表
     */
    @AutoIdempotent
    @GetMapping("/list")
    @ApiOperation("查询年级基础列表")
    @ApiImplicitParam(name = "sysGrade", value = "查询订单列表",
            dataType = "SysGrade")
    public TableDataInfo list(SysGrade sysGrade)
    {
        startPage();
        List<SysGrade> list = sysGradeService.selectSysGradeList(sysGrade);
        return getDataTable(list);
    }

    /**
     * 获取年级基础详细信息
     */
    @AutoIdempotent
    @ApiOperation("查询年级基础列表")
    @ApiImplicitParam(name = "id", value = "查询订单列表",
            dataType = "Long")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        AjaxResult ajax = AjaxResult.success();
        SysGrade sysGrade = new SysGrade();
        ajax.put("sysGrades", sysGradeService.selectSysGradeList(sysGrade));
        if (StringUtils.isNotNull(id)){
            ajax.put(AjaxResult.DATA_TAG,sysGradeService.selectSysGradeById(id));
        }
        return ajax;
    }

}
