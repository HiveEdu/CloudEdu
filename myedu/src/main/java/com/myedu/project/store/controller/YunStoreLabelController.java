package com.myedu.project.store.controller;

import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.store.domain.YunStoreLabel;
import com.myedu.project.store.service.IYunStoreLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 门店标签关联Controller
 * 
 * @author 梁少鹏
 * @date 2020-01-04
 */
@RestController
@RequestMapping("/store/storeLabel")
public class YunStoreLabelController extends BaseController
{
    @Autowired
    private IYunStoreLabelService yunStoreLabelService;

    /**
     * 查询门店类型关联列表
     */
    @PreAuthorize("@ss.hasPermi('store:storeLabel:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunStoreLabel yunStoreLabel)
    {
        startPage();
        List<YunStoreLabel> list = yunStoreLabelService.selectYunStoreLabelList(yunStoreLabel);
        return getDataTable(list);
    }

    /**
     * 导出门店类型关联列表
     */
    @PreAuthorize("@ss.hasPermi('store:storeLabel:export')")
    @Log(title = "门店类型关联", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunStoreLabel yunStoreLabel)
    {
        List<YunStoreLabel> list = yunStoreLabelService.selectYunStoreLabelList(yunStoreLabel);
        ExcelUtil<YunStoreLabel> util = new ExcelUtil<YunStoreLabel>(YunStoreLabel.class);
        return util.exportExcel(list, "storeLabel");
    }

    /**
     * 获取门店类型关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('store:storeLabel:query')")
    @GetMapping(value = "/{storeId}")
    public AjaxResult getInfo(@PathVariable("storeId") Long storeId)
    {
        return AjaxResult.success(yunStoreLabelService.selectYunStoreLabelById(storeId));
    }

    /**
     * 新增门店类型关联
     */
    @PreAuthorize("@ss.hasPermi('store:storeLabel:add')")
    @Log(title = "门店类型关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunStoreLabel yunStoreLabel)
    {
        return toAjax(yunStoreLabelService.insertYunStoreLabel(yunStoreLabel));
    }

    /**
     * 修改门店类型关联
     */
    @PreAuthorize("@ss.hasPermi('store:storeLabel:edit')")
    @Log(title = "门店类型关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunStoreLabel yunStoreLabel)
    {
        return toAjax(yunStoreLabelService.updateYunStoreLabel(yunStoreLabel));
    }

    /**
     * 删除门店类型关联
     */
    @PreAuthorize("@ss.hasPermi('store:storeLabel:remove')")
    @Log(title = "门店类型关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{storeIds}")
    public AjaxResult remove(@PathVariable Long[] storeIds)
    {
        return toAjax(yunStoreLabelService.deleteYunStoreLabelByIds(storeIds));
    }
}
