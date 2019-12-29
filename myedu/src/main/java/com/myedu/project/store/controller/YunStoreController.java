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
import com.myedu.project.store.domain.YunStore;
import com.myedu.project.store.service.IYunStoreService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.web.page.TableDataInfo;

/**
 * 门店Controller
 * 
 * @author 梁龙飞
 * @date 2019-12-29
 */
@RestController
@RequestMapping("/store/store")
public class YunStoreController extends BaseController
{
    @Autowired
    private IYunStoreService yunStoreService;

    /**
     * 查询门店列表
     */
    @PreAuthorize("@ss.hasPermi('store:store:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunStore yunStore)
    {
        startPage();
        List<YunStore> list = yunStoreService.selectYunStoreList(yunStore);
        return getDataTable(list);
    }

    /**
     * 导出门店列表
     */
    @PreAuthorize("@ss.hasPermi('store:store:export')")
    @Log(title = "门店", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunStore yunStore)
    {
        List<YunStore> list = yunStoreService.selectYunStoreList(yunStore);
        ExcelUtil<YunStore> util = new ExcelUtil<YunStore>(YunStore.class);
        return util.exportExcel(list, "store");
    }

    /**
     * 获取门店详细信息
     */
    @PreAuthorize("@ss.hasPermi('store:store:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yunStoreService.selectYunStoreById(id));
    }

    /**
     * 新增门店
     */
    @PreAuthorize("@ss.hasPermi('store:store:add')")
    @Log(title = "门店", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunStore yunStore)
    {
        return toAjax(yunStoreService.insertYunStore(yunStore));
    }

    /**
     * 修改门店
     */
    @PreAuthorize("@ss.hasPermi('store:store:edit')")
    @Log(title = "门店", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunStore yunStore)
    {
        return toAjax(yunStoreService.updateYunStore(yunStore));
    }

    /**
     * 删除门店
     */
    @PreAuthorize("@ss.hasPermi('store:store:remove')")
    @Log(title = "门店", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunStoreService.deleteYunStoreByIds(ids));
    }
}
