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
import com.myedu.project.store.domain.YunStoreAccount;
import com.myedu.project.store.service.IYunStoreAccountService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.web.page.TableDataInfo;

/**
 * 门店账目记录Controller
 * 
 * @author lsp
 * @date 2020-04-25
 */
@RestController
@RequestMapping("/store/account")
public class YunStoreAccountController extends BaseController
{
    @Autowired
    private IYunStoreAccountService yunStoreAccountService;
    @Autowired
    private IYunStoreService yunStoreService;
    /**
     * 查询门店账目记录列表
     */
    @PreAuthorize("@ss.hasPermi('store:account:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunStoreAccount yunStoreAccount)
    {
        startPage();
        List<YunStoreAccount> list = yunStoreAccountService.selectYunStoreAccountList(yunStoreAccount);
        return getDataTable(list);
    }

    /**
     * 导出门店账目记录列表
     */
    @PreAuthorize("@ss.hasPermi('store:account:export')")
    @Log(title = "门店账目记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunStoreAccount yunStoreAccount)
    {
        List<YunStoreAccount> list = yunStoreAccountService.selectYunStoreAccountList(yunStoreAccount);
        ExcelUtil<YunStoreAccount> util = new ExcelUtil<YunStoreAccount>(YunStoreAccount.class);
        return util.exportExcel(list, "account");
    }

    /**
     * 获取门店账目记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('store:account:query')")
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
            ajax.put(AjaxResult.DATA_TAG,yunStoreAccountService.selectYunStoreAccountById(id));
        }
        return ajax;
    }

    /**
     * 新增门店账目记录
     */
    @PreAuthorize("@ss.hasPermi('store:account:add')")
    @Log(title = "门店账目记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunStoreAccount yunStoreAccount)
    {
        yunStoreAccount.setCreateById(SecurityUtils.getUserId());
        yunStoreAccount.setCreateBy(SecurityUtils.getUsername());
        yunStoreAccount.setCreateTime(DateUtils.getNowDate());
        return toAjax(yunStoreAccountService.insertYunStoreAccount(yunStoreAccount));
    }

    /**
     * 修改门店账目记录
     */
    @PreAuthorize("@ss.hasPermi('store:account:edit')")
    @Log(title = "门店账目记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunStoreAccount yunStoreAccount)
    {
        yunStoreAccount.setUpdateTime(DateUtils.getNowDate());
        return toAjax(yunStoreAccountService.updateYunStoreAccount(yunStoreAccount));
    }

    /**
     * 删除门店账目记录
     */
    @PreAuthorize("@ss.hasPermi('store:account:remove')")
    @Log(title = "门店账目记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunStoreAccountService.deleteYunStoreAccountByIds(ids));
    }
}
