package com.myedu.project.store.controller;

import java.util.List;

import com.myedu.common.utils.DateUtils;
import com.myedu.common.utils.SecurityUtils;
import com.myedu.common.utils.StringUtils;
import com.myedu.project.store.domain.vo.YunStoreVo;
import com.myedu.project.store.service.IYunStoreService;
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
import com.myedu.project.store.domain.YunStoreEqu;
import com.myedu.project.store.service.IYunStoreEquService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.web.page.TableDataInfo;

/**
 * 门店设备Controller
 * 
 * @author lsp
 * @date 2020-05-04
 */
@RestController
@RequestMapping("/store/equ")
public class YunStoreEquController extends BaseController
{
    @Autowired
    private IYunStoreEquService yunStoreEquService;
    @Autowired
    private IYunStoreService yunStoreService;
    /**
     * 查询门店设备列表
     */
    @PreAuthorize("@ss.hasPermi('store:equ:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunStoreEqu yunStoreEqu)
    {
        startPage();
        List<YunStoreEqu> list = yunStoreEquService.selectYunStoreEquList(yunStoreEqu);
        return getDataTable(list);
    }

    /**
     * 导出门店设备列表
     */
    @PreAuthorize("@ss.hasPermi('store:equ:export')")
    @Log(title = "门店设备", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunStoreEqu yunStoreEqu)
    {
        List<YunStoreEqu> list = yunStoreEquService.selectYunStoreEquList(yunStoreEqu);
        ExcelUtil<YunStoreEqu> util = new ExcelUtil<YunStoreEqu>(YunStoreEqu.class);
        return util.exportExcel(list, "equ");
    }

    /**
     * 获取门店设备详细信息
     */
    @PreAuthorize("@ss.hasPermi('store:equ:query')")
    @GetMapping(value = { "/", "/{id}" })
    public AjaxResult getInfo(@PathVariable(value = "id", required = false) Long id)
    {
        AjaxResult ajax = AjaxResult.success();
        YunStoreVo yunStore=new YunStoreVo();
        yunStore.setCreateById(SecurityUtils.getUserId());
        List<YunStoreVo> stores=yunStoreService.selectYunStoreList(yunStore);
        ajax.put("stores", stores);
        if (StringUtils.isNotNull(id))
        {
            ajax.put(AjaxResult.DATA_TAG,yunStoreEquService.selectYunStoreEquById(id));
        }
        return ajax;
    }

    /**
     * 新增门店设备
     */
    @PreAuthorize("@ss.hasPermi('store:equ:add')")
    @Log(title = "门店设备", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunStoreEqu yunStoreEqu)
    {
        yunStoreEqu.setCreateById(SecurityUtils.getUserId());
        yunStoreEqu.setCreateBy(SecurityUtils.getUsername());
        yunStoreEqu.setCreateTime(DateUtils.getNowDate());
        return toAjax(yunStoreEquService.insertYunStoreEqu(yunStoreEqu));
    }

    /**
     * 修改门店设备
     */
    @PreAuthorize("@ss.hasPermi('store:equ:edit')")
    @Log(title = "门店设备", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunStoreEqu yunStoreEqu)
    {
        yunStoreEqu.setUpdateTime(DateUtils.getNowDate());
        return toAjax(yunStoreEquService.updateYunStoreEqu(yunStoreEqu));
    }

    /**
     * 删除门店设备
     */
    @PreAuthorize("@ss.hasPermi('store:equ:remove')")
    @Log(title = "门店设备", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunStoreEquService.deleteYunStoreEquByIds(ids));
    }
}
