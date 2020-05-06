package com.myedu.app.common.controller;

import com.myedu.framework.aspectj.lang.annotation.AutoIdempotent;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.hometeacher.domain.vo.YunUserInfoVo;
import com.myedu.project.system.domain.SysDictData;
import com.myedu.project.system.domain.YunBanner;
import com.myedu.project.system.service.ISysDictDataService;
import com.myedu.project.system.service.IYunBannerService;
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
@Api("获取轮廓图")
@RestController
@RequestMapping("/app/banner")
public class APPBannerController extends BaseController {
    @Autowired
    private IYunBannerService yunBannerService;
    /**
     * 获取所有轮廓图
     */
    @AutoIdempotent
    @ApiOperation("获取所有轮廓图")
    @GetMapping(value = "/all")
    public TableDataInfo getAllBanner()
    {
        startPage();
        YunBanner yun=new YunBanner();
        List<YunBanner> list = yunBannerService.selectYunBannerList(yun);
        return getDataTable(list);
    }
}
