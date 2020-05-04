package com.myedu.project.account.controller;

import java.math.BigDecimal;
import java.util.List;

import com.myedu.common.utils.DateUtils;
import com.myedu.common.utils.SecurityUtils;
import com.myedu.project.account.domain.YunAccountChange;
import com.myedu.project.account.domain.vo.YunAccountVo;
import com.myedu.project.account.enums.AccountChangeType;
import com.myedu.project.account.service.IYunAccountChangeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
import com.myedu.project.account.domain.YunAccount;
import com.myedu.project.account.service.IYunAccountService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.web.page.TableDataInfo;

/**
 * 账户管理Controller
 * 
 * @author 梁少鹏
 * @date 2020-01-25
 */
@RestController
@RequestMapping("/account/account")
public class YunAccountController extends BaseController
{
    @Autowired
    private IYunAccountService yunAccountService;
    @Autowired
    private IYunAccountChangeService yunAccountChangeService;
    /**
     * 查询账户管理列表
     */
    @PreAuthorize("@ss.hasPermi('account:account:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunAccount yunAccount)
    {
        startPage();
        List<YunAccount> list = yunAccountService.selectYunAccountList(yunAccount);
        return getDataTable(list);
    }

    /**
     * 导出账户管理列表
     */
    @PreAuthorize("@ss.hasPermi('account:account:export')")
    @Log(title = "账户管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunAccount yunAccount)
    {
        List<YunAccount> list = yunAccountService.selectYunAccountList(yunAccount);
        ExcelUtil<YunAccount> util = new ExcelUtil<YunAccount>(YunAccount.class);
        return util.exportExcel(list, "account");
    }

    /**
     * 获取账户管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('account:account:query')")
    @GetMapping(value = { "/", "/{id}" })
    public AjaxResult getInfo(@PathVariable(value = "id", required = false) Long id)
    {
        AjaxResult ajax = AjaxResult.success();
        if(id!=null){
            List<YunAccountChange> yunAccountChanges=yunAccountChangeService.selectYunAccountChangeByUserId(yunAccountService.selectYunAccountById(id).getCreateById());
            ajax.put(AjaxResult.DATA_TAG,yunAccountService.selectYunAccountById(id));
            ajax.put("WITHDRAWLIST",yunAccountChanges);
            return ajax;
        }else{
            YunAccount yunAccount=new YunAccount();
            yunAccount.setCreateById(SecurityUtils.getUserId());
            List<YunAccount> yunAccounts=  yunAccountService.selectYunAccountList(yunAccount);
            if(yunAccounts.size()>0){
                return AjaxResult.error(204,"账户已经存在");
            }else{
                return AjaxResult.success("创建成功");
            }
        }
//        return AjaxResult.success(yunAccountService.selectYunAccountById(id));
    }

    /**
     * 新增账户管理
     */
    @PreAuthorize("@ss.hasPermi('account:account:add')")
    @Log(title = "账户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunAccount yunAccount)
    {
        return toAjax(yunAccountService.insertYunAccount(yunAccount));
    }

    /**
     * 修改账户管理
     */
    @PreAuthorize("@ss.hasPermi('account:account:edit')")
    @Log(title = "账户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunAccount yunAccount)
    {
        return toAjax(yunAccountService.updateYunAccount(yunAccount));
    }

    /**
     * 删除账户管理
     */
    @PreAuthorize("@ss.hasPermi('account:account:remove')")
    @Log(title = "账户管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunAccountService.deleteYunAccountByIds(ids));
    }



    /*
     * @Description :账户充值
     * @Author : 梁少鹏
     * @Date : 2020/1/26 11:14
     */
    @PreAuthorize("@ss.hasPermi('account:account:racharge')")
    @Log(title = "账户充值", businessType = BusinessType.UPDATE)
    @PutMapping("/racharge")
    public AjaxResult racharge(@RequestBody YunAccountVo yunAccount)
    {
        yunAccount.setCaseAmount(yunAccount.getRachargeAmount().add(yunAccount.getTotalAmount()));
        yunAccount.setTotalAmount(yunAccount.getRachargeAmount().add(yunAccount.getTotalAmount()));
        YunAccount yunAccount1=yunAccountService.selectYunAccountById(yunAccount.getId());
        int result=yunAccountService.updateYunAccount(yunAccount);
        if(result==1){//主表修改成功增加记录表
            YunAccountChange yunAccountChange=new YunAccountChange();
            yunAccountChange.setUserId(SecurityUtils.getUserId());
            yunAccountChange.setPreAmount(yunAccount1.getTotalAmount());
            yunAccountChange.setCashAmount(yunAccount.getTotalAmount());
            yunAccountChange.setUncashAmount(yunAccount.getRachargeAmount());
            yunAccountChange.setChangeType(AccountChangeType.RECHARGE.getCode());
            yunAccountChange.setCreateById(SecurityUtils.getUserId());
            yunAccountChange.setCreateBy(SecurityUtils.getUsername());
            yunAccountChange.setCreateTime(DateUtils.getNowDate());
            yunAccountChangeService.insertYunAccountChange(yunAccountChange);
        }
        return toAjax(result);
    }


    @PreAuthorize("@ss.hasPermi('account:account:withdraw')")
    @Log(title = "账户提现", businessType = BusinessType.UPDATE)
    @PutMapping("/withdraw")
    public AjaxResult withdraw(@RequestBody YunAccountVo yunAccount)
    {
        yunAccount.setCaseAmount(yunAccount.getTotalAmount().subtract(yunAccount.getRachargeAmount()));
        yunAccount.setTotalAmount(yunAccount.getTotalAmount().subtract(yunAccount.getRachargeAmount()));
        YunAccount yunAccount1=yunAccountService.selectYunAccountById(yunAccount.getId());
        int result=yunAccountService.updateYunAccount(yunAccount);
        if(result==1){//主表修改成功增加记录表
            YunAccountChange yunAccountChange=new YunAccountChange();
            yunAccountChange.setUserId(SecurityUtils.getUserId());
            yunAccountChange.setPreAmount(yunAccount1.getTotalAmount());
            yunAccountChange.setCashAmount(yunAccount.getTotalAmount());
            yunAccountChange.setUncashAmount(yunAccount.getRachargeAmount());
            yunAccountChange.setChangeType(AccountChangeType.WITHDRAW.getCode());
            yunAccountChange.setCreateById(SecurityUtils.getUserId());
            yunAccountChange.setCreateBy(SecurityUtils.getUsername());
            yunAccountChange.setCreateTime(DateUtils.getNowDate());
            yunAccountChangeService.insertYunAccountChange(yunAccountChange);
        }
        return toAjax(result);
    }

//    @PreAuthorize("@ss.hasPermi('account:account:toPayAsPc')")
//    @Log(title = "支付宝PC网页支付")
//    @PostMapping(value = "/toPayAsPC")
//    public ResponseEntity<String> toPayAsPc(@Validated @RequestBody YunAccountVo yunAccount) throws Exception{
//        AlipayConfig aliPay = alipayService.find();
//        trade.setOutTradeNo(alipayUtils.getOrderCode());
//        String payUrl = alipayService.toPayAsPc(aliPay,trade);
//        return ResponseEntity.ok(payUrl);
//    }
}
