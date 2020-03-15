package com.myedu.project.store.activity.controller;

import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import com.myedu.common.utils.OrderUtil;
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
import com.myedu.project.store.activity.domain.YunStoreBargain;
import com.myedu.project.store.activity.service.IYunStoreBargainService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.web.page.TableDataInfo;

/**
 * 门店砍价活动Controller
 * 
 * @author 梁龙飞
 * @date 2020-03-14
 */
@RestController
@RequestMapping("/store/bargain")
public class YunStoreBargainController extends BaseController
{
    @Autowired
    private IYunStoreBargainService yunStoreBargainService;

    /**
     * 查询门店砍价活动列表
     */
    @PreAuthorize("@ss.hasPermi('store:bargain:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunStoreBargain yunStoreBargain)
    {
        startPage();
        List<YunStoreBargain> list = yunStoreBargainService.selectYunStoreBargainList(yunStoreBargain);
        return getDataTable(list);
    }

    /**
     * 导出门店砍价活动列表
     */
    @PreAuthorize("@ss.hasPermi('store:bargain:export')")
    @Log(title = "门店砍价活动", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunStoreBargain yunStoreBargain)
    {
        List<YunStoreBargain> list = yunStoreBargainService.selectYunStoreBargainList(yunStoreBargain);
        ExcelUtil<YunStoreBargain> util = new ExcelUtil<YunStoreBargain>(YunStoreBargain.class);
        return util.exportExcel(list, "bargain");
    }

    /**
     * 获取门店砍价活动详细信息
     */
    @PreAuthorize("@ss.hasPermi('store:bargain:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yunStoreBargainService.selectYunStoreBargainById(id));
    }

    /**
     * 新增门店砍价活动
     */
    @PreAuthorize("@ss.hasPermi('store:bargain:add')")
    @Log(title = "门店砍价活动", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunStoreBargain yunStoreBargain)
    {
        return toAjax(yunStoreBargainService.insertYunStoreBargain(yunStoreBargain));
    }

    /**
     * 修改门店砍价活动
     */
    @PreAuthorize("@ss.hasPermi('store:bargain:edit')")
    @Log(title = "门店砍价活动", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunStoreBargain yunStoreBargain)
    {
        return toAjax(yunStoreBargainService.updateYunStoreBargain(yunStoreBargain));
    }

    /**
     * 删除门店砍价活动
     */
    @PreAuthorize("@ss.hasPermi('store:bargain:remove')")
    @Log(title = "门店砍价活动", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunStoreBargainService.deleteYunStoreBargainByIds(ids));
    }
}
