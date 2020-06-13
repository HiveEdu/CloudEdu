package com.myedu.app.parents.controller;

import com.myedu.common.utils.ServletUtils;
import com.myedu.common.utils.StringUtils;
import com.myedu.framework.aspectj.lang.annotation.AutoIdempotent;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
import com.myedu.framework.security.LoginUser;
import com.myedu.framework.security.service.TokenService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.dataBasic.domain.SysCourse;
import com.myedu.project.dataBasic.service.ISysCourseService;
import com.myedu.project.parents.domain.YunStuScore;
import com.myedu.project.parents.domain.vo.YunStuScoreVo;
import com.myedu.project.parents.domain.vo.YunStudentVo;
import com.myedu.project.parents.service.IYunStuScoreService;
import com.myedu.project.parents.service.IYunStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 学生成绩Controller
 * 
 * @author 梁龙飞
 * @date 2019-12-30
 */
@Api("APP学生成绩管理")
@RestController
@RequestMapping("/app/parents/score")
public class AppStScoreController extends BaseController
{
    @Autowired
    private IYunStuScoreService yunStuScoreService;
    @Autowired
    private IYunStudentService yunStudentService;

    @Autowired
    private ISysCourseService sysCourseService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询学生成绩列表
     */
    @AutoIdempotent
    @ApiOperation("查询学生成绩列表")
    @ApiImplicitParam(name = "yunStuScore", value = "查询学生成绩列表",
            dataType = "YunStuScoreVo")
    @GetMapping("/list")
    public TableDataInfo list(YunStuScoreVo yunStuScore)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        startPage();
        yunStuScore.setCreateById(loginUser.getUser().getUserId());
        List<YunStuScoreVo> list = yunStuScoreService.selectYunStuScoreList(yunStuScore);
        return getDataTable(list);
    }

    /**
     * 获取学生成绩详细信息
     */
    @AutoIdempotent
    @ApiOperation("获取学生成绩详细信息")
    @ApiImplicitParam(name = "scoreId", value = "获取学生成绩详细信息",
            dataType = "Long")
    @GetMapping(value = { "/", "/{scoreId}" })
    public AjaxResult getInfo(@PathVariable(value ="scoreId", required = false)  Long scoreId)
    {
        AjaxResult ajax = AjaxResult.success();
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        List<SysCourse> list1 = sysCourseService.selectSysCourseList(new SysCourse());
        ajax.put("courseList", list1);
        YunStudentVo yunStudentVo = new YunStudentVo();
        yunStudentVo.setCreateById(loginUser.getUser().getUserId());
        List<YunStudentVo> list = yunStudentService.selectYunStudentList(yunStudentVo);
        ajax.put("studentLists", list);
        if (StringUtils.isNotNull(scoreId)) {
            ajax.put(AjaxResult.DATA_TAG, yunStuScoreService.selectYunStuScoreById(scoreId));
        }
        return ajax;
    }

    /**
     * 新增学生成绩
     */
    @AutoIdempotent
    @ApiOperation("新增学生成绩")
    @ApiImplicitParam(name = "yunStuScore", value = "新增学生成绩",
            dataType = "YunStuScore")
    @Log(title = "学生成绩", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Valid @RequestBody YunStuScore yunStuScore)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        yunStuScore.setCreateById(loginUser.getUser().getUserId());
        yunStuScore.setCreateBy(loginUser.getUser().getNickName());
        return toAjax(yunStuScoreService.insertYunStuScore(yunStuScore));
    }

    /**
     * 修改学生成绩
     */
    @AutoIdempotent
    @ApiOperation("修改学生成绩")
    @ApiImplicitParam(name = "yunStuScore", value = "修改学生成绩",
            dataType = "YunStuScore")
    @Log(title = "学生成绩", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunStuScore yunStuScore)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        yunStuScore.setUpdateBy(loginUser.getUser().getNickName());
        return toAjax(yunStuScoreService.updateYunStuScore(yunStuScore));
    }

    /**
     * 删除学生成绩
     */
    @AutoIdempotent
    @ApiOperation("删除学生成绩")
    @ApiImplicitParam(name = "scoreIds", value = "删除学生成绩",
            dataType = "Long[]")
    @Log(title = "学生成绩", businessType = BusinessType.DELETE)
	@DeleteMapping("/{scoreIds}")
    public AjaxResult remove(@PathVariable Long[] scoreIds)
    {
      return toAjax(yunStuScoreService.deleteYunStuScoreByIds(scoreIds));
    }
}
