package com.myedu.project.dynamic.controller;

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
import com.myedu.project.dynamic.domain.YunDyLikes;
import com.myedu.project.dynamic.service.IYunDyLikesService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.web.page.TableDataInfo;

/**
 * 动态点赞Controller
 * 
 * @author 梁少鹏
 * @date 2020-01-01
 */
@RestController
@RequestMapping("/dynamic/likes")
public class YunDyLikesController extends BaseController
{
    @Autowired
    private IYunDyLikesService yunDyLikesService;

    /**
     * 查询动态点赞列表
     */
    @PreAuthorize("@ss.hasPermi('dynamic:likes:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunDyLikes yunDyLikes)
    {
        startPage();
        List<YunDyLikes> list = yunDyLikesService.selectYunDyLikesList(yunDyLikes);
        return getDataTable(list);
    }

    /**
     * 导出动态点赞列表
     */
    @PreAuthorize("@ss.hasPermi('dynamic:likes:export')")
    @Log(title = "动态点赞", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunDyLikes yunDyLikes)
    {
        List<YunDyLikes> list = yunDyLikesService.selectYunDyLikesList(yunDyLikes);
        ExcelUtil<YunDyLikes> util = new ExcelUtil<YunDyLikes>(YunDyLikes.class);
        return util.exportExcel(list, "likes");
    }

    /**
     * 获取动态点赞详细信息
     */
    @PreAuthorize("@ss.hasPermi('dynamic:likes:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yunDyLikesService.selectYunDyLikesById(id));
    }

    /**
     * 新增动态点赞
     */
    @PreAuthorize("@ss.hasPermi('dynamic:likes:add')")
    @Log(title = "动态点赞", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunDyLikes yunDyLikes)
    {
        return toAjax(yunDyLikesService.insertYunDyLikes(yunDyLikes));
    }

    /**
     * 修改动态点赞
     */
    @PreAuthorize("@ss.hasPermi('dynamic:likes:edit')")
    @Log(title = "动态点赞", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunDyLikes yunDyLikes)
    {
        return toAjax(yunDyLikesService.updateYunDyLikes(yunDyLikes));
    }

    /**
     * 删除动态点赞
     */
    @PreAuthorize("@ss.hasPermi('dynamic:likes:remove')")
    @Log(title = "动态点赞", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunDyLikesService.deleteYunDyLikesByIds(ids));
    }
}
