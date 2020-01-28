package com.myedu.project.account.domain.vo;

import com.myedu.project.account.domain.YunAccount;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: ${梁少鹏}
 * Date: 2020/1/26
 * Time: 11:38
 * Description:
 */
public class YunAccountVo extends YunAccount {
    //账户充值金额
    private BigDecimal rachargeAmount;

    public BigDecimal getRachargeAmount() {
        return rachargeAmount;
    }

    public void setRachargeAmount(BigDecimal rachargeAmount) {
        this.rachargeAmount = rachargeAmount;
    }
}