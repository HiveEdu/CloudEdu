package com.myedu.project.store.controller;

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
import com.myedu.project.store.domain.YunStoreType;
import com.myedu.project.store.service.IYunStoreTypeService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.web.page.TableDataInfo;

/**
 * 门店类型关联Controller
 * 
 * @author 梁少鹏
 * @date 2020-01-04
 */
@RestController
@RequestMapping("/store/storeType")
public class YunStoreTypeController extends BaseController
{
    @Autowired
    private IYunStoreTypeService yunStoreTypeService;

    /**
     * 查询门店类型关联列表
     */
    @PreAuthorize("@ss.hasPermi('store:storeType:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunStoreType yunStoreType)
    {
        startPage();
        List<YunStoreType> list = yunStoreTypeService.selectYunStoreTypeList(yunStoreType);
        return getDataTable(list);
    }

    /**
     * 导出门店类型关联列表
     */
    @PreAuthorize("@ss.hasPermi('store:storeType:export')")
    @Log(title = "门店类型关联", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunStoreType yunStoreType)
    {
        List<YunStoreType> list = yunStoreTypeService.selectYunStoreTypeList(yunStoreType);
        ExcelUtil<YunStoreType> util = new ExcelUtil<YunStoreType>(YunStoreType.class);
        return util.exportExcel(list, "storeType");
    }

    /**
     * 获取门店类型关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('store:storeType:query')")
    @GetMapping(value = "/{storeId}")
    public AjaxResult getInfo(@PathVariable("storeId") Long storeId)
    {
        return AjaxResult.success(yunStoreTypeService.selectYunStoreTypeByStoreId(storeId));
    }

    /**
     * 新增门店类型关联
     */
    @PreAuthorize("@ss.hasPermi('store:storeType:add')")
    @Log(title = "门店类型关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunStoreType yunStoreType)
    {
        return toAjax(yunStoreTypeService.insertYunStoreType(yunStoreType));
    }

    /**
     * 修改门店类型关联
     */
    @PreAuthorize("@ss.hasPermi('store:storeType:edit')")
    @Log(title = "门店类型关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunStoreType yunStoreType)
    {
        return toAjax(yunStoreTypeService.updateYunStoreType(yunStoreType));
    }

    /**
     * 删除门店类型关联
     */
    @PreAuthorize("@ss.hasPermi('store:storeType:remove')")
    @Log(title = "门店类型关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{storeIds}")
    public AjaxResult remove(@PathVariable Long[] storeIds)
    {
        return toAjax(yunStoreTypeService.deleteYunStoreTypeByIds(storeIds));
    }
}
