package com.myedu.app.parents.controller;

import com.myedu.common.utils.ServletUtils;
import com.myedu.framework.aspectj.lang.annotation.AutoIdempotent;
import com.myedu.framework.security.LoginUser;
import com.myedu.framework.security.service.TokenService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.parents.domain.YunStuMistake;
import com.myedu.project.parents.domain.vo.YunStuMistakeVo;
import com.myedu.project.parents.service.IYunStuMistakeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @Autowired
    private TokenService tokenService;

    /**
     * @Description :查询当前用户下的学生错题记录
     * @Author : 梁少鹏
     * @Date : 2019/12/29 17:24
     */
    @AutoIdempotent
    @ApiOperation("查询当前用户下的学生错题记录")
    @ApiImplicitParam(name = "yunStuMistakeVo", value = "查询当前用户下的学生错题记录",
            dataType = "YunStuMistakeVo")
    @GetMapping("/getMyStudentMistake")
    public TableDataInfo getMyStudentMistake(YunStuMistakeVo yunStuMistakeVo)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null){
            startPage();
            yunStuMistakeVo.setCreateById(loginUser.getUser().getUserId());
            List<YunStuMistakeVo> list =  yunStuMistakeService.selectYunStuMistakeList(yunStuMistakeVo);
            return getDataTable(list);
        }else{
            return getDataTableLose(null);
        }
    }
    @AutoIdempotent
    @ApiOperation("获取错题详情记录")
    @ApiImplicitParam(name = "id", value = "获取错题详情记录",
            dataType = "Long")
    @GetMapping(value = "/getStudentMistakeById/{id}")
    public AjaxResult getStudentMistakeById(@PathVariable("id") Long id)
    {
      return AjaxResult.success(yunStuMistakeService.selectYunStuMistakeById(id));
    }

    /**
     * @Description :添加学生错题记录
     * @Author : 梁少鹏
     * @Date : 2019/12/29 17:26
     */
    @AutoIdempotent
    @ApiOperation("添加学生错题记录")
    @ApiImplicitParam(name = "yunStuMistake", value = "添加学生错题记录",
            dataType = "YunStuMistake")
    @PostMapping("/addStudentMistake")
    public AjaxResult addStudentMistake(@Valid @RequestBody YunStuMistake yunStuMistake)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        yunStuMistake.setCreateById(loginUser.getUser().getUserId());
        yunStuMistake.setCreateBy(loginUser.getUser().getNickName());
        return toAjax(yunStuMistakeService.insertYunStuMistake(yunStuMistake));
    }
    /**
     * @Description :修改学生错题记录
     * @Author : 梁少鹏
     * @Date : 2019/12/29 17:31
     */
    @AutoIdempotent
    @ApiOperation("修改学生错题记录")
    @ApiImplicitParam(name = "yunStuMistake", value = "修改学生错题记录",
            dataType = "YunStuMistake")
    @PostMapping("/editStudentMistake")
    public AjaxResult editStudentMistake(@RequestBody YunStuMistake yunStuMistake)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        yunStuMistake.setUpdateBy(loginUser.getUser().getNickName());
        return toAjax(yunStuMistakeService.updateYunStuMistake(yunStuMistake));
    }
    /**
     * @Description :删除学生错题记录
     * @Author : 梁少鹏
     * @Date : 2019/12/29 17:32
     */
    @AutoIdempotent
    @ApiOperation("删除学生错题记录")
    @ApiImplicitParam(name = "ids", value = "删除学生错题记录",
            dataType = "Long[]")
    @DeleteMapping("/deletStudentMistakeByIds/{ids}")
    public AjaxResult deletStudentMistakeByIds(@PathVariable Long[] ids)
    {
       return toAjax(yunStuMistakeService.deleteYunStuMistakeByIds(ids));
    }

}
