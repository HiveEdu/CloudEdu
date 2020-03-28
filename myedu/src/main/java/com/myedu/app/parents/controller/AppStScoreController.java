package com.myedu.app.parents.controller;

import com.myedu.common.utils.SecurityUtils;
import com.myedu.common.utils.ServletUtils;
import com.myedu.common.utils.StringUtils;
import com.myedu.common.utils.poi.ExcelUtil;
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
import com.myedu.project.parents.domain.vo.YunStuMistakeVo;
import com.myedu.project.parents.domain.vo.YunStuScoreVo;
import com.myedu.project.parents.domain.vo.YunStudentVo;
import com.myedu.project.parents.service.IYunStuScoreService;
import com.myedu.project.parents.service.IYunStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @ApiOperation("查询学生成绩列表")
    @ApiImplicitParam(name = "yunStuScore", value = "查询学生成绩列表",
            dataType = "YunStuScoreVo")
    @GetMapping("/list")
    public TableDataInfo list(YunStuScoreVo yunStuScore)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
            startPage();
            List<YunStuScoreVo> list = yunStuScoreService.selectYunStuScoreList(yunStuScore);
            return getDataTable(list);
        }else {
            return getDataTableLose(null);
        }

    }

    /**
     * 导出学生成绩列表
     */
    @ApiOperation("导出学生成绩列表")
    @ApiImplicitParam(name = "yunStuScore", value = "导出学生成绩列表",
            dataType = "YunStuScoreVo")
    @Log(title = "学生成绩", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunStuScoreVo yunStuScore)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
            List<YunStuScoreVo> list = yunStuScoreService.selectYunStuScoreList(yunStuScore);
            ExcelUtil<YunStuScoreVo> util = new ExcelUtil<YunStuScoreVo>(YunStuScoreVo.class);
            return util.exportExcel(list, "score");
        }else {
            return AjaxResult.error("token无效");
        }

    }

    /**
     * 获取学生成绩详细信息
     */
    @ApiOperation("获取学生成绩详细信息")
    @ApiImplicitParam(name = "scoreId", value = "获取学生成绩详细信息",
            dataType = "Long")
    @GetMapping(value = { "/", "/{scoreId}" })
    public AjaxResult getInfo(@PathVariable(value ="scoreId", required = false)  Long scoreId)
    {
        AjaxResult ajax = AjaxResult.success();
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
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
        }else {
            return AjaxResult.error("token无效");
        }
    }

    /**
     * 新增学生成绩
     */
    @ApiOperation("新增学生成绩")
    @ApiImplicitParam(name = "yunStuScore", value = "新增学生成绩",
            dataType = "YunStuScore")
    @Log(title = "学生成绩", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunStuScore yunStuScore)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
            yunStuScore.setCreateById(loginUser.getUser().getUserId());
            yunStuScore.setCreateBy(loginUser.getUser().getNickName());
            return toAjax(yunStuScoreService.insertYunStuScore(yunStuScore));
        }else {
            return AjaxResult.error("token无效");
        }

    }

    /**
     * 修改学生成绩
     */
    @ApiOperation("修改学生成绩")
    @ApiImplicitParam(name = "yunStuScore", value = "修改学生成绩",
            dataType = "YunStuScore")
    @Log(title = "学生成绩", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunStuScore yunStuScore)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
            yunStuScore.setUpdateBy(loginUser.getUser().getNickName());
            return toAjax(yunStuScoreService.updateYunStuScore(yunStuScore));
        }else {
            return AjaxResult.error("token无效");
        }

    }

    /**
     * 删除学生成绩
     */
    @ApiOperation("删除学生成绩")
    @ApiImplicitParam(name = "scoreIds", value = "删除学生成绩",
            dataType = "Long[]")
    @Log(title = "学生成绩", businessType = BusinessType.DELETE)
	@DeleteMapping("/{scoreIds}")
    public AjaxResult remove(@PathVariable Long[] scoreIds)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
            return toAjax(yunStuScoreService.deleteYunStuScoreByIds(scoreIds));
        }else {
            return AjaxResult.error("token无效");
        }

    }
}
