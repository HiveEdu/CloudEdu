package com.myedu.app.parents.controller;

import com.myedu.common.utils.SecurityUtils;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.project.parents.domain.YunStudent;
import com.myedu.project.parents.service.IYunStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: ${梁少鹏}
 * Date: 2019/12/28
 * Time: 18:45
 * Description:App家长端学生管理
 */
@Api("APP用户信息管理")
@RestController
@RequestMapping("/app/parents/student")
public class AppStudentController extends BaseController {
    @Autowired
    private IYunStudentService yunStudentService;
    /*
     * @Description :家长端增加学生
     * @Author : 梁少鹏
     * @Date : 2019/12/28 18:49
     */
    @ApiOperation("增加学生")
    @ApiImplicitParam(name = "SysUser", value = "增加学生", dataType = "YunStudent")
    @PostMapping("/addStudent")
    public AjaxResult addStudent(@RequestBody YunStudent yunStudent)
    {
        yunStudent.setCreateById(SecurityUtils.getUserId());
        yunStudent.setCreateBy(SecurityUtils.getUseNickName());
        return toAjax(yunStudentService.insertYunStudent(yunStudent));
    }

}