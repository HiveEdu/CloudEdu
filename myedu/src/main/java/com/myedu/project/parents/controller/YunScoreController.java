package com.myedu.project.parents.controller;

import java.util.List;
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
import com.myedu.project.parents.domain.YunScore;
import com.myedu.project.parents.service.IYunScoreService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.web.page.TableDataInfo;

/**
 * scoreController
 * 
 * @author 梁龙飞
 * @date 2019-12-30
 */
@RestController
@RequestMapping("/score/score")
public class YunScoreController extends BaseController
{
    @Autowired
    private IYunScoreService yunScoreService;

    /**
     * 查询score列表
     */
    @PreAuthorize("@ss.hasPermi('score:score:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunScore yunScore)
    {
        startPage();
        List<YunScore> list = yunScoreService.selectYunScoreList(yunScore);
        return getDataTable(list);
    }

    /**
     * 导出score列表
     */
    @PreAuthorize("@ss.hasPermi('score:score:export')")
    @Log(title = "score", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunScore yunScore)
    {
        List<YunScore> list = yunScoreService.selectYunScoreList(yunScore);
        ExcelUtil<YunScore> util = new ExcelUtil<YunScore>(YunScore.class);
        return util.exportExcel(list, "score");
    }

    /**
     * 获取score详细信息
     */
    @PreAuthorize("@ss.hasPermi('score:score:query')")
    @GetMapping(value = "/{scoreId}")
    public AjaxResult getInfo(@PathVariable("scoreId") Long scoreId)
    {
        return AjaxResult.success(yunScoreService.selectYunScoreById(scoreId));
    }

    /**
     * 新增score
     */
    @PreAuthorize("@ss.hasPermi('score:score:add')")
    @Log(title = "score", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunScore yunScore)
    {
        return toAjax(yunScoreService.insertYunScore(yunScore));
    }

    /**
     * 修改score
     */
    @PreAuthorize("@ss.hasPermi('score:score:edit')")
    @Log(title = "score", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunScore yunScore)
    {
        return toAjax(yunScoreService.updateYunScore(yunScore));
    }

    /**
     * 删除score
     */
    @PreAuthorize("@ss.hasPermi('score:score:remove')")
    @Log(title = "score", businessType = BusinessType.DELETE)
	@DeleteMapping("/{scoreIds}")
    public AjaxResult remove(@PathVariable Long[] scoreIds)
    {
        return toAjax(yunScoreService.deleteYunScoreByIds(scoreIds));
    }
}
