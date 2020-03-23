package com.myedu.app.parents.controller;

import com.myedu.common.utils.SecurityUtils;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.parents.domain.YunStudent;
import com.myedu.project.parents.domain.vo.YunStudentVo;
import com.myedu.project.parents.service.IYunStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * @Description :查询当前用户下的学生
     * @Author : 梁少鹏
     * @Date : 2019/12/28 20:28
     */
    @ApiOperation("查询当前用户下的学生")
    @ApiImplicitParam(name = "yunStudentVo", value = "查询当前用户下的学生",
            dataType = "YunStudentVo")
    @GetMapping("/getMyStudent")
    public TableDataInfo getMyStudent(YunStudentVo yunStudentVo)
    {
        startPage();
        List<YunStudentVo> list = (List<YunStudentVo>) yunStudentService.selectYunStudentList(yunStudentVo);
//                stream().filter(item -> item.getCreateById().equals(SecurityUtils.getUserId()));
        return getDataTable(list);
    }
    /*
     * @Description :获取学生详情
     * @Author : 梁少鹏
     * @Date : 2019/12/28 20:32
     */
    @ApiOperation("获取学生详情")
    @ApiImplicitParam(name = "id", value = "获取学生详情", dataType = "Long")
    @GetMapping(value = "/getStudnetById/{id}")
    public AjaxResult getStudnetById(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yunStudentService.selectYunStudentById(id));
    }
    /*
     * @Description :家长端增加学生
     * @Author : 梁少鹏
     * @Date : 2019/12/28 18:49
     */
    @ApiOperation("增加学生")
    @ApiImplicitParam(name = "yunStudent", value = "增加学生", dataType = "YunStudent")
    @Log(title = "学生数据", businessType = BusinessType.INSERT)
    @PostMapping("/addStudent")
    public AjaxResult addStudent(@RequestBody YunStudent yunStudent)
    {
        yunStudent.setCreateById(SecurityUtils.getUserId());
        yunStudent.setCreateBy(SecurityUtils.getUseNickName());
        return toAjax(yunStudentService.insertYunStudent(yunStudent));
    }
    /*
     * @Description :修改学生数据
     * @Author : 梁少鹏
     * @Date : 2019/12/28 20:35
     */
    @ApiOperation("修改学生")
    @ApiImplicitParam(name = "yunStudent", value = "修改学生", dataType = "YunStudent")
    @Log(title = "学生数据", businessType = BusinessType.UPDATE)
    @PostMapping("/editStudent")
    public AjaxResult editStudent(@RequestBody YunStudent yunStudent)
    {
        yunStudent.setUpdateBy(SecurityUtils.getUseNickName());
        return toAjax(yunStudentService.updateYunStudent(yunStudent));
    }
    /*
     * @Description :删除学生
     * @Author : 梁少鹏
     * @Date : 2019/12/28 20:39
     */
    @ApiOperation("删除学生")
    @ApiImplicitParam(name = "ids", value = "删除学生", dataType = "Long[]")
    @Log(title = "学生数据", businessType = BusinessType.DELETE)
    @DeleteMapping("/deletStudentByIds/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunStudentService.deleteYunStudentByIds(ids));
    }

}
