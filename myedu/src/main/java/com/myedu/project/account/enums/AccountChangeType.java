package com.myedu.project.account.enums;

public enum AccountChangeType {

    RECHARGE("1", "充值"), PAYMENT("2", "支付"),
    REFUND("3", "退款"), WITHDRAW("4", "提现"),
    INTERNALTRANSFERACCOUNT("5", "内部调帐");

    private final String code;
    private final String info;

    AccountChangeType(String code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public String getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
}
