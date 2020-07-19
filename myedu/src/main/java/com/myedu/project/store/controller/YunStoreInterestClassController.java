package com.myedu.project.store.controller;

import com.myedu.common.utils.SecurityUtils;
import com.myedu.common.utils.StringUtils;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.dataBasic.domain.SysCourse;
import com.myedu.project.dataBasic.service.ISysCourseService;
import com.myedu.project.store.domain.YunStoreInterestClass;
import com.myedu.project.store.domain.vo.YunStoreInterestClassVo;
import com.myedu.project.store.domain.vo.YunStoreVo;
import com.myedu.project.store.service.IYunStoreInterestClassService;
import com.myedu.project.store.service.IYunStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 门店兴趣班Controller
 * 
 * @author myedu
 * @date 2020-07-19
 */
@RestController
@RequestMapping("/store/interclass")
public class YunStoreInterestClassController extends BaseController
{
    @Autowired
    private IYunStoreInterestClassService yunStoreInterestClassService;

    @Autowired
    private IYunStoreService yunStoreService;
    @Autowired
    private ISysCourseService sysCourseService;
    /**
     * 查询门店兴趣班列表
     */
    @PreAuthorize("@ss.hasPermi('store:interclass:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunStoreInterestClass yunStoreInterestClass)
    {
        startPage();
        List<YunStoreInterestClassVo> list = yunStoreInterestClassService.selectYunStoreInterestClassList(yunStoreInterestClass);
        return getDataTable(list);
    }

    /**
     * 导出门店兴趣班列表
     */
    @PreAuthorize("@ss.hasPermi('store:interclass:export')")
    @Log(title = "门店兴趣班", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunStoreInterestClass yunStoreInterestClass)
    {
        List<YunStoreInterestClassVo> list = yunStoreInterestClassService.selectYunStoreInterestClassList(yunStoreInterestClass);
        ExcelUtil<YunStoreInterestClassVo> util = new ExcelUtil(YunStoreInterestClassVo.class);
        return util.exportExcel(list, "interclass");
    }

    /**
     * 获取门店兴趣班详细信息
     */
    @PreAuthorize("@ss.hasPermi('store:interclass:query')")
    @GetMapping(value = { "/", "/{id}" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxResult getInfo(@PathVariable(value = "id", required = false) Long id)
    {
        AjaxResult ajax = AjaxResult.success();
        YunStoreVo yunStore=new YunStoreVo();
        yunStore.setCreateById(SecurityUtils.getUserId());
        List<YunStoreVo> stores=yunStoreService.selectYunStoreList(yunStore);
        ajax.put("stores", stores);
        SysCourse sysCourses=new SysCourse();
        ajax.put("sysCourses", sysCourseService.selectSysCourseList(sysCourses));
        if (StringUtils.isNotNull(id)) {
            ajax.put(AjaxResult.DATA_TAG, yunStoreInterestClassService.selectYunStoreInterestClassById(id));
        }
        return ajax;
    }

    /**
     * 新增门店兴趣班
     */
    @PreAuthorize("@ss.hasPermi('store:interclass:add')")
    @Log(title = "门店兴趣班", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunStoreInterestClass yunStoreInterestClass)
    {
        yunStoreInterestClass.setCreateById(SecurityUtils.getUserId());
        yunStoreInterestClass.setCreateBy(SecurityUtils.getUsername());
        return toAjax(yunStoreInterestClassService.insertYunStoreInterestClass(yunStoreInterestClass));
    }

    /**
     * 修改门店兴趣班
     */
    @PreAuthorize("@ss.hasPermi('store:interclass:edit')")
    @Log(title = "门店兴趣班", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunStoreInterestClass yunStoreInterestClass)
    {
        yunStoreInterestClass.setUpdateBy(SecurityUtils.getUsername());
        yunStoreInterestClass.setUpdateById(SecurityUtils.getUserId());
        return toAjax(yunStoreInterestClassService.updateYunStoreInterestClass(yunStoreInterestClass));
    }

    /**
     * 删除门店兴趣班
     */
    @PreAuthorize("@ss.hasPermi('store:interclass:remove')")
    @Log(title = "门店兴趣班", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunStoreInterestClassService.deleteYunStoreInterestClassByIds(ids));
    }
}
