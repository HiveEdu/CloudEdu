package com.myedu.project.parents.controller;

import java.util.List;

import com.myedu.common.utils.SecurityUtils;
import com.myedu.project.parents.domain.vo.YunStuScoreVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
import com.myedu.project.parents.domain.YunStuScore;
import com.myedu.project.parents.service.IYunStuScoreService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.web.page.TableDataInfo;

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
    @GetMapping(value = "/{scoreId}")
    public AjaxResult getInfo(@PathVariable("scoreId") Long scoreId)
    {
        return AjaxResult.success(yunStuScoreService.selectYunStuScoreById(scoreId));
    }

    /**
     * 新增学生成绩
     */
    @PreAuthorize("@ss.hasPermi('parents:score:add')")
    @Log(title = "学生成绩", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunStuScore yunStuScore)
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
