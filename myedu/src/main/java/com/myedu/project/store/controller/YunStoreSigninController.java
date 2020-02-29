package com.myedu.project.store.controller;

import java.util.List;

import com.myedu.common.utils.SecurityUtils;
import com.myedu.common.utils.StringUtils;
import com.myedu.project.parents.domain.vo.YunStudentVo;
import com.myedu.project.parents.service.IYunStudentService;
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
import com.myedu.project.store.domain.YunStoreSignin;
import com.myedu.project.store.service.IYunStoreSigninService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.web.page.TableDataInfo;

/**
 * 点名签到Controller
 * 
 * @author 梁龙飞
 * @date 2020-02-29
 */
@RestController
@RequestMapping("/store/signin")
public class YunStoreSigninController extends BaseController
{
    @Autowired
    private IYunStoreSigninService yunStoreSigninService;
    @Autowired
    private IYunStudentService yunStudentService;
    /**
     * 查询点名签到列表
     */
    @PreAuthorize("@ss.hasPermi('store:signin:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunStoreSignin yunStoreSignin)
    {
        startPage();
        List<YunStoreSignin> list = yunStoreSigninService.selectYunStoreSigninList(yunStoreSignin);
        return getDataTable(list);
    }

    /**
     * 导出点名签到列表
     */
    @PreAuthorize("@ss.hasPermi('store:signin:export')")
    @Log(title = "点名签到", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunStoreSignin yunStoreSignin)
    {
        List<YunStoreSignin> list = yunStoreSigninService.selectYunStoreSigninList(yunStoreSignin);
        ExcelUtil<YunStoreSignin> util = new ExcelUtil<YunStoreSignin>(YunStoreSignin.class);
        return util.exportExcel(list, "signin");
    }

    /**
     * 获取点名签到详细信息
     */
    @PreAuthorize("@ss.hasPermi('store:signin:query')")
    @GetMapping(value = { "/", "/{id}" })
    public AjaxResult getInfo(@PathVariable(value ="id", required = false) Long id)
    {   AjaxResult ajax = AjaxResult.success();
        YunStudentVo yunStudentVo=new YunStudentVo();
        yunStudentVo.setCreateById(SecurityUtils.getUserId());
        List<YunStudentVo> list = yunStudentService.selectYunStudentList(yunStudentVo);
        ajax.put("studentLists", list);
        if (StringUtils.isNotNull(id)) {
            ajax.put(AjaxResult.DATA_TAG,yunStoreSigninService.selectYunStoreSigninById(id));
        }
        return ajax;
    }

    /**
     * 新增点名签到
     */
    @PreAuthorize("@ss.hasPermi('store:signin:add')")
    @Log(title = "点名签到", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunStoreSignin yunStoreSignin)
    {   yunStoreSignin.setCreateById(SecurityUtils.getUserId());
        yunStoreSignin.setCreateBy(SecurityUtils.getUsername());
        return toAjax(yunStoreSigninService.insertYunStoreSignin(yunStoreSignin));
    }

    /**
     * 修改点名签到
     */
    @PreAuthorize("@ss.hasPermi('store:signin:edit')")
    @Log(title = "点名签到", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunStoreSignin yunStoreSignin)
    {
        return toAjax(yunStoreSigninService.updateYunStoreSignin(yunStoreSignin));
    }

    /**
     * 删除点名签到
     */
    @PreAuthorize("@ss.hasPermi('store:signin:remove')")
    @Log(title = "点名签到", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunStoreSigninService.deleteYunStoreSigninByIds(ids));
    }
}
