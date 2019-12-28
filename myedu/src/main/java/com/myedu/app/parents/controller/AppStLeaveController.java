package com.myedu.app.parents.controller;

import com.myedu.common.utils.SecurityUtils;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.parents.domain.YunStuLeave;
import com.myedu.project.parents.service.IYunStuLeaveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ${梁少鹏}
 * Date: 2019/12/28
 * Time: 21:20
 * Description:学生请假APP接口
 */
@Api("APP学生请假信息信息管理")
@RestController
@RequestMapping("/app/parents/stLeave")
public class AppStLeaveController extends BaseController {
    @Autowired
    private IYunStuLeaveService yunStuLeaveService;

    @ApiOperation("查询当前用户下的学生请假列表")
    @ApiImplicitParam(name = "yunStuLeave", value = "查询当前用户下的学生请假列表",
            dataType = "YunStuLeave")
    @GetMapping("/list")
    public TableDataInfo list(YunStuLeave yunStuLeave)
    {
        startPage();
        List<YunStuLeave> list = (List<YunStuLeave>) yunStuLeaveService.selectYunStuLeaveList(yunStuLeave).
                stream().filter(item -> item.getCreateById().equals(SecurityUtils.getUserId()));
        return getDataTable(list);
    }
}