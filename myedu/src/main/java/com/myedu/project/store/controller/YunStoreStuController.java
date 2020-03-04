package com.myedu.project.store.controller;

import java.util.List;

import com.myedu.project.store.domain.vo.YunStoreStuVo;
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
import com.myedu.project.store.domain.YunStoreStu;
import com.myedu.project.store.service.IYunStoreStuService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.web.page.TableDataInfo;

/**
 * 门店学生管理Controller
 * 
 * @author 梁少鹏
 * @date 2020-03-03
 */
@RestController
@RequestMapping("/store/storeStudent")
public class YunStoreStuController extends BaseController
{
    @Autowired
    private IYunStoreStuService yunStoreStuService;

    /**
     * 查询门店学生管理列表
     */
    @PreAuthorize("@ss.hasPermi('store:storeStudent:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunStoreStuVo yunStoreStu)
    {
        startPage();
        List<YunStoreStuVo> list = yunStoreStuService.selectYunStoreStuList(yunStoreStu);
        return getDataTable(list);
    }

    /**
     * 导出门店学生管理列表
     */
    @PreAuthorize("@ss.hasPermi('store:storeStudent:export')")
    @Log(title = "门店学生管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunStoreStuVo yunStoreStu)
    {
        List<YunStoreStuVo> list = yunStoreStuService.selectYunStoreStuList(yunStoreStu);
        ExcelUtil<YunStoreStuVo> util = new ExcelUtil<YunStoreStuVo>(YunStoreStuVo.class);
        return util.exportExcel(list, "storeStudent");
    }

    /**
     * 获取门店学生管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('store:storeStudent:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yunStoreStuService.selectYunStoreStuById(id));
    }

    /**
     * 新增门店学生管理
     */
    @PreAuthorize("@ss.hasPermi('store:storeStudent:add')")
    @Log(title = "门店学生管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunStoreStu yunStoreStu)
    {
        return toAjax(yunStoreStuService.insertYunStoreStu(yunStoreStu));
    }

    /**
     * 修改门店学生管理
     */
    @PreAuthorize("@ss.hasPermi('store:storeStudent:edit')")
    @Log(title = "门店学生管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunStoreStu yunStoreStu)
    {
        return toAjax(yunStoreStuService.updateYunStoreStu(yunStoreStu));
    }

    /**
     * 删除门店学生管理
     */
    @PreAuthorize("@ss.hasPermi('store:storeStudent:remove')")
    @Log(title = "门店学生管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunStoreStuService.deleteYunStoreStuByIds(ids));
    }
}
