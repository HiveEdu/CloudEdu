package com.myedu.app.parents.controller;

import com.myedu.common.utils.SecurityUtils;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.parents.domain.YunStuMistake;
import com.myedu.project.parents.domain.vo.YunStuLeaveVo;
import com.myedu.project.parents.domain.vo.YunStuMistakeVo;
import com.myedu.project.parents.service.IYunStuMistakeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ${梁少鹏}
 * Date: 2019/12/29
 * Time: 17:19
 * Description:
 */
@Api("APP学生错题记录信息")
@RestController
@RequestMapping("/app/parents/mistake")
public class AppStMistakeController extends BaseController {

    @Autowired
    private IYunStuMistakeService yunStuMistakeService;

    /*
     * @Description :查询当前用户下的学生错题记录
     * @Author : 梁少鹏
     * @Date : 2019/12/29 17:24
     */
    @ApiOperation("查询当前用户下的学生错题记录")
    @ApiImplicitParam(name = "yunStuMistakeVo", value = "查询当前用户下的学生错题记录",
            dataType = "YunStuMistakeVo")
    @GetMapping("/getMyStudentMistake")
    public TableDataInfo getMyStudentMistake(YunStuMistakeVo yunStuMistakeVo)
    {
        startPage();
        List<YunStuMistakeVo> list = (List<YunStuMistakeVo>) yunStuMistakeService.selectYunStuMistakeList(yunStuMistakeVo).
                stream().filter(item -> item.getCreateById().equals(SecurityUtils.getUserId()));
        return getDataTable(list);
    }

    @ApiOperation("获取错题详情记录")
    @ApiImplicitParam(name = "id", value = "获取错题详情记录",
            dataType = "Long")
    @GetMapping(value = "/getStudentMistakeById/{id}")
    public AjaxResult getStudentMistakeById(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yunStuMistakeService.selectYunStuMistakeById(id));
    }

    /*
     * @Description :添加学生错题记录
     * @Author : 梁少鹏
     * @Date : 2019/12/29 17:26
     */
    @ApiOperation("添加学生错题记录")
    @ApiImplicitParam(name = "yunStuMistake", value = "添加学生错题记录",
            dataType = "YunStuMistake")
    @PostMapping("/addStudentMistake")
    public AjaxResult addStudentMistake(@RequestBody YunStuMistake yunStuMistake)
    {
        yunStuMistake.setCreateById(SecurityUtils.getUserId());
        yunStuMistake.setCreateBy(SecurityUtils.getUsername());
        return toAjax(yunStuMistakeService.insertYunStuMistake(yunStuMistake));
    }
    /*
     * @Description :修改学生错题记录
     * @Author : 梁少鹏
     * @Date : 2019/12/29 17:31
     */
    @ApiOperation("修改学生错题记录")
    @ApiImplicitParam(name = "yunStuMistake", value = "修改学生错题记录",
            dataType = "YunStuMistake")
    @PostMapping("/editStudentMistake")
    public AjaxResult editStudentMistake(@RequestBody YunStuMistake yunStuMistake)
    {
        yunStuMistake.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(yunStuMistakeService.updateYunStuMistake(yunStuMistake));
    }
    /*
     * @Description :删除学生错题记录
     * @Author : 梁少鹏
     * @Date : 2019/12/29 17:32
     */
    @ApiOperation("删除学生错题记录")
    @ApiImplicitParam(name = "ids", value = "删除学生错题记录",
            dataType = "Long[]")
    @DeleteMapping("/deletStudentMistakeByIds/{ids}")
    public AjaxResult deletStudentMistakeByIds(@PathVariable Long[] ids)
    {
        return toAjax(yunStuMistakeService.deleteYunStuMistakeByIds(ids));
    }

}