package com.myedu.project.parents.controller;

import com.myedu.common.utils.SecurityUtils;
import com.myedu.common.utils.StringUtils;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 学生成绩Controller
 * 
 * @author 梁龙飞
 * @date 2019-12-30
 */
@RestController
@RequestMapping("/parents/score")
public class YunStuScoreController extends BaseController
{
    @Autowired
    private IYunStuScoreService yunStuScoreService;
    @Autowired
    private IYunStudentService yunStudentService;

    @Autowired
    private ISysCourseService sysCourseService;

    /**
     * 查询学生成绩列表
     */
    @PreAuthorize("@ss.hasPermi('parents:score:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunStuScoreVo yunStuScore)
    {
        startPage();
        List<YunStuScoreVo> list = yunStuScoreService.selectYunStuScoreList(yunStuScore);
        return getDataTable(list);
    }

    /**
     * 导出学生成绩列表
     */
    @PreAuthorize("@ss.hasPermi('parents:score:export')")
    @Log(title = "学生成绩", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunStuScoreVo yunStuScore)
    {
        List<YunStuScoreVo> list = yunStuScoreService.selectYunStuScoreList(yunStuScore);
        ExcelUtil<YunStuScoreVo> util = new ExcelUtil<YunStuScoreVo>(YunStuScoreVo.class);
        return util.exportExcel(list, "score");
    }

    /**
     * 获取学生成绩详细信息
     */
    @PreAuthorize("@ss.hasPermi('parents:score:query')")
    @GetMapping(value = { "/", "/{scoreId}" })
    public AjaxResult getInfo(@PathVariable(value ="scoreId", required = false)  Long scoreId)
    {
        AjaxResult ajax = AjaxResult.success();
        List<SysCourse> list1 = sysCourseService.selectSysCourseList(new SysCourse());
        ajax.put("courseList", list1);
        YunStudentVo yunStudentVo=new YunStudentVo();
        yunStudentVo.setCreateById(SecurityUtils.getUserId());
        List<YunStudentVo> list = yunStudentService.selectYunStudentList(yunStudentVo);
        ajax.put("studentLists", list);
        if (StringUtils.isNotNull(scoreId))
        {
            ajax.put(AjaxResult.DATA_TAG, yunStuScoreService.selectYunStuScoreById(scoreId));
        }
        return ajax;
    }

    /**
     * 新增学生成绩
     */
    @PreAuthorize("@ss.hasPermi('parents:score:add')")
    @Log(title = "学生成绩", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Valid @RequestBody YunStuScore yunStuScore)
    {   yunStuScore.setCreateById(SecurityUtils.getUserId());
        yunStuScore.setCreateBy(SecurityUtils.getUsername());
        return toAjax(yunStuScoreService.insertYunStuScore(yunStuScore));
    }

    /**
     * 修改学生成绩
     */
    @PreAuthorize("@ss.hasPermi('parents:score:edit')")
    @Log(title = "学生成绩", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunStuScore yunStuScore)
    {   yunStuScore.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(yunStuScoreService.updateYunStuScore(yunStuScore));
    }

    /**
     * 删除学生成绩
     */
    @PreAuthorize("@ss.hasPermi('parents:score:remove')")
    @Log(title = "学生成绩", businessType = BusinessType.DELETE)
	@DeleteMapping("/{scoreIds}")
    public AjaxResult remove(@PathVariable Long[] scoreIds)
    {
        return toAjax(yunStuScoreService.deleteYunStuScoreByIds(scoreIds));
    }
}
