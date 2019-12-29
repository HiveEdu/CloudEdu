package com.myedu.project.parents.controller;

import java.util.List;

import com.myedu.common.utils.SecurityUtils;
import com.myedu.project.parents.domain.vo.YunStuMistakeVo;
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
import com.myedu.project.parents.domain.YunStuMistake;
import com.myedu.project.parents.service.IYunStuMistakeService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.web.page.TableDataInfo;

/**
 * 学生错题记录Controller
 * 
 * @author 梁少鹏
 * @date 2019-12-29
 */
@RestController
@RequestMapping("/parents/mistake")
public class YunStuMistakeController extends BaseController
{
    @Autowired
    private IYunStuMistakeService yunStuMistakeService;

    /**
     * 查询学生错题记录列表
     */
    @PreAuthorize("@ss.hasPermi('parents:mistake:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunStuMistakeVo yunStuMistake)
    {
        startPage();
        List<YunStuMistakeVo> list = yunStuMistakeService.selectYunStuMistakeList(yunStuMistake);
        return getDataTable(list);
    }

    /**
     * 导出学生错题记录列表
     */
    @PreAuthorize("@ss.hasPermi('parents:mistake:export')")
    @Log(title = "学生错题记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunStuMistakeVo yunStuMistake)
    {
        List<YunStuMistakeVo> list = yunStuMistakeService.selectYunStuMistakeList(yunStuMistake);
        ExcelUtil<YunStuMistakeVo> util = new ExcelUtil<YunStuMistakeVo>(YunStuMistakeVo.class);
        return util.exportExcel(list, "mistake");
    }

    /**
     * 获取学生错题记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('parents:mistake:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yunStuMistakeService.selectYunStuMistakeById(id));
    }

    /**
     * 新增学生错题记录
     */
    @PreAuthorize("@ss.hasPermi('parents:mistake:add')")
    @Log(title = "学生错题记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunStuMistake yunStuMistake)
    {
        yunStuMistake.setCreateById(SecurityUtils.getUserId());
        yunStuMistake.setCreateBy(SecurityUtils.getUsername());
        return toAjax(yunStuMistakeService.insertYunStuMistake(yunStuMistake));
    }

    /**
     * 修改学生错题记录
     */
    @PreAuthorize("@ss.hasPermi('parents:mistake:edit')")
    @Log(title = "学生错题记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunStuMistake yunStuMistake)
    {
        yunStuMistake.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(yunStuMistakeService.updateYunStuMistake(yunStuMistake));
    }

    /**
     * 删除学生错题记录
     */
    @PreAuthorize("@ss.hasPermi('parents:mistake:remove')")
    @Log(title = "学生错题记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunStuMistakeService.deleteYunStuMistakeByIds(ids));
    }
}
