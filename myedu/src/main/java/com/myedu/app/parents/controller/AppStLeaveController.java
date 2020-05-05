package com.myedu.app.parents.controller;

import com.myedu.common.utils.ServletUtils;
import com.myedu.framework.aspectj.lang.annotation.AutoIdempotent;
import com.myedu.framework.security.LoginUser;
import com.myedu.framework.security.service.TokenService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.parents.domain.YunStuLeave;
import com.myedu.project.parents.domain.vo.YunStuLeaveVo;
import com.myedu.project.parents.service.IYunStuLeaveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private TokenService tokenService;

    @AutoIdempotent
    @ApiOperation("查询当前用户下的学生请假列表")
    @ApiImplicitParam(name = "yunStuLeaveVo", value = "查询当前用户下的学生请假列表",
            dataType = "YunStuLeaveVo")
    @GetMapping("/getMyStudentLeave")
    public TableDataInfo getMyStudentLeave(YunStuLeaveVo yunStuLeaveVo)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        startPage();
        yunStuLeaveVo.setCreateById(loginUser.getUser().getUserId());
        List<YunStuLeaveVo> list = yunStuLeaveService.selectYunStuLeaveList(yunStuLeaveVo);
        return getDataTable(list);
    }

    /**
     * @Description :查询当前用户下的学生请假详情
     * @Author : 梁少鹏
     * @Date : 2019/12/29 15:18
     */
    @AutoIdempotent
    @ApiOperation("查询当前用户下的学生请假详情")
    @ApiImplicitParam(name = "id", value = "查询当前用户下的学生请假详情",
            dataType = "Long")
    @GetMapping("/getMyStudentLeaveById/{id}")
    public AjaxResult getMyStudentLeaveById(@PathVariable("id") Long id)
    {
       return AjaxResult.success(yunStuLeaveService.selectYunStuLeaveById(id));
    }

    /**
     * @Description :添加学生请假
     * @Author : 梁少鹏
     * @Date : 2019/12/29 15:19
     */
    @AutoIdempotent
    @ApiOperation("添加学生请假")
    @ApiImplicitParam(name = "yunStuLeave", value = "添加学生请假",
            dataType = "YunStuLeave")
    @PostMapping("/addStudentLeave")
    public AjaxResult addStudentLeave(@RequestBody YunStuLeave yunStuLeave)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        yunStuLeave.setCreateById(loginUser.getUser().getUserId());
        yunStuLeave.setCreateBy(loginUser.getUser().getNickName());
        return toAjax(yunStuLeaveService.insertYunStuLeave(yunStuLeave));
    }
    /**
     * @Description :修改学生请假
     * @Author : 梁少鹏
     * @Date : 2019/12/29 15:29
     */
    @AutoIdempotent
    @ApiOperation("修改学生请假")
    @ApiImplicitParam(name = "yunStuLeave", value = "修改学生请假",
            dataType = "YunStuLeave")
    @PostMapping("/editStudentLeave")
    public AjaxResult editStudentLeave(@RequestBody YunStuLeave yunStuLeave)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        yunStuLeave.setUpdateBy(loginUser.getUser().getNickName());
        return toAjax(yunStuLeaveService.updateYunStuLeave(yunStuLeave));
    }
    /**
     * @Description :删除学生请假
     * @Author : 梁少鹏
     * @Date : 2019/12/29 15:31
     */
    @AutoIdempotent
    @ApiOperation("删除学生请假")
    @ApiImplicitParam(name = "ids", value = "删除学生请假",
            dataType = "Long[]")
    @DeleteMapping("/deletStudentLeaveByIds/{ids}")
    public AjaxResult deletStudentLeaveByIds(@PathVariable Long[] ids)
    {
        return toAjax(yunStuLeaveService.deleteYunStuLeaveByIds(ids));
    }
}
