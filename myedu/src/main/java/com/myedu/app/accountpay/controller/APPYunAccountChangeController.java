package com.myedu.app.accountpay.controller;

import com.myedu.common.utils.ServletUtils;
import com.myedu.framework.aspectj.lang.annotation.AutoIdempotent;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
import com.myedu.framework.security.LoginUser;
import com.myedu.framework.security.service.TokenService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.account.domain.YunAccountChange;
import com.myedu.project.account.service.IYunAccountChangeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 账户资金变动流水Controller
 * 
 * @author 梁少鹏
 * @date 2020-01-25
 */
@Api("账户资金变动流水")
@RestController
@RequestMapping("/app/account/account_change")
public class APPYunAccountChangeController extends BaseController
{
    @Autowired
    private IYunAccountChangeService yunAccountChangeService;
    @Autowired
    private TokenService tokenService;
    /**
     * 查询账户资金变动流水列表
     */
    @AutoIdempotent
    @ApiOperation("查询账户资金变动流水列表")
    @ApiImplicitParam(name = "yunAccountChange", value = "查询账户资金变动流水列表",
            dataType = "YunAccountChange")
    @PreAuthorize("@ss.hasPermi('account:account_change:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunAccountChange yunAccountChange)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        startPage();
        yunAccountChange.setCreateById(loginUser.getUser().getUserId());
        List<YunAccountChange> list = yunAccountChangeService.selectYunAccountChangeList(yunAccountChange);
        return getDataTable(list);
    }


    /**
     * 获取账户资金变动流水详细信息
     */
    @AutoIdempotent
    @ApiOperation("获取账户资金变动流水详细信息")
    @ApiImplicitParam(name = "id", value = "获取账户资金变动流水详细信息",
            dataType = "Long")
    @PreAuthorize("@ss.hasPermi('account:account_change:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yunAccountChangeService.selectYunAccountChangeById(id));
    }

    /**
     * 新增账户资金变动流水
     */
    @AutoIdempotent
    @ApiOperation("新增账户资金变动流水")
    @ApiImplicitParam(name = "id", value = "新增账户资金变动流水",
            dataType = "Long")
    @PreAuthorize("@ss.hasPermi('account:account_change:add')")
    @Log(title = "新增账户资金变动流水", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunAccountChange yunAccountChange)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        yunAccountChange.setCreateById(loginUser.getUser().getUserId());
        return toAjax(yunAccountChangeService.insertYunAccountChange(yunAccountChange));
    }

    /**
     * 修改账户资金变动流水
     */
    @AutoIdempotent
    @ApiOperation("修改账户资金变动流水")
    @ApiImplicitParam(name = "yunAccountChange", value = "修改账户资金变动流水",
            dataType = "YunAccountChange")
    @PreAuthorize("@ss.hasPermi('account:account_change:edit')")
    @Log(title = "账户资金变动流水", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunAccountChange yunAccountChange)
    {
        return toAjax(yunAccountChangeService.updateYunAccountChange(yunAccountChange));
    }

    /**
     * 删除账户资金变动流水
     */
    @AutoIdempotent
    @ApiOperation("删除账户资金变动流水")
    @ApiImplicitParam(name = "ids", value = "删除账户资金变动流水",
            dataType = "Long[]")
    @PreAuthorize("@ss.hasPermi('account:account_change:remove')")
    @Log(title = "账户资金变动流水", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunAccountChangeService.deleteYunAccountChangeByIds(ids));
    }
}
