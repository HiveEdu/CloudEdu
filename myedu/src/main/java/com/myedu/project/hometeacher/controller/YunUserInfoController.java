package com.myedu.project.hometeacher.controller;

import java.util.List;

import com.myedu.common.utils.StringUtils;
import com.myedu.project.account.domain.YunAccount;
import com.myedu.project.dataBasic.domain.SysCourse;
import com.myedu.project.dataBasic.service.ISysCourseService;
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
import com.myedu.project.hometeacher.domain.YunUserInfo;
import com.myedu.project.hometeacher.service.IYunUserInfoService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.web.page.TableDataInfo;

/**
 * 家教老师表Controller
 * 
 * @author 梁龙飞
 * @date 2020-02-10
 */
@RestController
@RequestMapping("/hometeacher/info")
public class YunUserInfoController extends BaseController
{
    @Autowired
    private IYunUserInfoService yunUserInfoService;

    @Autowired
    private ISysCourseService sysCourseService;
    /**
     * 查询家教老师表列表
     */
    @PreAuthorize("@ss.hasPermi('hometeacher:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunUserInfo yunUserInfo)
    {
        startPage();
        List<YunUserInfo> list = yunUserInfoService.selectYunUserInfoList(yunUserInfo);
        return getDataTable(list);
    }

    /**
     * 导出家教老师表列表
     */
    @PreAuthorize("@ss.hasPermi('hometeacher:info:export')")
    @Log(title = "家教老师表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunUserInfo yunUserInfo)
    {
        List<YunUserInfo> list = yunUserInfoService.selectYunUserInfoList(yunUserInfo);
        ExcelUtil<YunUserInfo> util = new ExcelUtil<YunUserInfo>(YunUserInfo.class);
        return util.exportExcel(list, "info");
    }

    /**
     * 获取家教老师表详细信息
     */
    @PreAuthorize("@ss.hasPermi('hometeacher:info:query')")
    @GetMapping(value = { "/", "/{id}" })
    public AjaxResult getInfo(@PathVariable(value = "id", required = false) Long id)
    {
        AjaxResult ajax = AjaxResult.success();
        //获取课程列表信息
        SysCourse sysCourse = new SysCourse();
        ajax.put("sysCourses", sysCourseService.selectSysCourseList(sysCourse));
        if (StringUtils.isNotNull(id))
        {
            ajax.put(AjaxResult.DATA_TAG,yunUserInfoService.selectYunUserInfoById(id));
        }
        return ajax;
    }

    /**
     * 新增家教老师表
     */
    @PreAuthorize("@ss.hasPermi('hometeacher:info:add')")
    @Log(title = "家教老师表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunUserInfo yunUserInfo)
    {
        List<YunUserInfo> yunUserInfos=  yunUserInfoService.selectYunUserInfoList(yunUserInfo);
        if(yunUserInfos.size()>0){
            return AjaxResult.error(204,"您的信息已经存在，不能重复添加");
        }else{
            return toAjax(yunUserInfoService.insertYunUserInfo(yunUserInfo));
        }

    }

    /**
     * 修改家教老师表
     */
    @PreAuthorize("@ss.hasPermi('hometeacher:info:edit')")
    @Log(title = "家教老师表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunUserInfo yunUserInfo)
    {
        return toAjax(yunUserInfoService.updateYunUserInfo(yunUserInfo));
    }

    /**
     * 删除家教老师表
     */
    @PreAuthorize("@ss.hasPermi('hometeacher:info:remove')")
    @Log(title = "家教老师表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunUserInfoService.deleteYunUserInfoByIds(ids));
    }
}
