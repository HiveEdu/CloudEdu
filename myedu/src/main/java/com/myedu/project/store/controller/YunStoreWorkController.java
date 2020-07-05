package com.myedu.project.store.controller;

import com.myedu.common.utils.SecurityUtils;
import com.myedu.common.utils.StringUtils;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.store.domain.YunStoreWork;
import com.myedu.project.store.domain.vo.YunStoreVo;
import com.myedu.project.store.domain.vo.YunStoreWorkVo;
import com.myedu.project.store.service.IYunStoreService;
import com.myedu.project.store.service.IYunStoreWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 门店作业管理Controller
 * 
 * @author lianglongfei
 * @date 2020-07-05
 */
@RestController
@RequestMapping("/store/storework")
public class YunStoreWorkController extends BaseController
{
    @Autowired
    private IYunStoreWorkService yunStoreWorkService;
    @Autowired
    private IYunStoreService yunStoreService;

    /**
     * 查询门店作业管理列表
     */
    @PreAuthorize("@ss.hasPermi('store:storework:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunStoreWork yunStoreWork)
    {
        startPage();
        List<YunStoreWorkVo> list = yunStoreWorkService.selectYunStoreWorkList(yunStoreWork);
        return getDataTable(list);
    }

    /**
     * 导出门店作业管理列表
     */
    @PreAuthorize("@ss.hasPermi('store:storework:export')")
    @Log(title = "门店作业管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunStoreWork yunStoreWork)
    {
        List<YunStoreWorkVo> list = yunStoreWorkService.selectYunStoreWorkList(yunStoreWork);
        ExcelUtil<YunStoreWorkVo> util = new ExcelUtil<YunStoreWorkVo>(YunStoreWorkVo.class);
        return util.exportExcel(list, "storework");
    }

    /**
     * 获取门店作业管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('store:storework:query')")
    @GetMapping(value = { "/", "/{id}" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxResult getInfo(@PathVariable(value = "id", required = false) Long id)
    {
        AjaxResult ajax = AjaxResult.success();
        YunStoreVo yunStore=new YunStoreVo();
        yunStore.setCreateById(SecurityUtils.getUserId());
        List<YunStoreVo> stores=yunStoreService.selectYunStoreList(yunStore);
        ajax.put("stores", stores);
        if (StringUtils.isNotNull(id))
        {
            ajax.put(AjaxResult.DATA_TAG,yunStoreWorkService.selectYunStoreWorkById(id));
        }
        return ajax;

    }

    /**
     * 新增门店作业管理
     */
    @PreAuthorize("@ss.hasPermi('store:storework:add')")
    @Log(title = "门店作业管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunStoreWork yunStoreWork)
    {
        yunStoreWork.setCreateById(SecurityUtils.getUserId());
        yunStoreWork.setCreateBy(SecurityUtils.getUsername());
        return toAjax(yunStoreWorkService.insertYunStoreWork(yunStoreWork));
    }

    /**
     * 修改门店作业管理
     */
    @PreAuthorize("@ss.hasPermi('store:storework:edit')")
    @Log(title = "门店作业管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunStoreWork yunStoreWork)
    {
        yunStoreWork.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(yunStoreWorkService.updateYunStoreWork(yunStoreWork));
    }

    /**
     * 删除门店作业管理
     */
    @PreAuthorize("@ss.hasPermi('store:storework:remove')")
    @Log(title = "门店作业管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunStoreWorkService.deleteYunStoreWorkByIds(ids));
    }
}
