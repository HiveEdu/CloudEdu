package com.myedu.project.account.enums;

public enum AccountChangeType {

    RECHARGE("1", "钱包充值"), PAYMENT("2", "订单支付"),
    REFUND("3", "退款"), WITHDRAW("4", "提现"),
    INTERNALTRANSFERACCOUNT("5", "内部调帐"),STORERECHARGE("6", "门店充值");

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
