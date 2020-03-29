package com.myedu.project.order.enums;

public enum PaymentType {

    PAYMENTOFANORDER("1", "订单制度"), storetopup("2", "门店充值");

    private final String code;
    private final String info;

    PaymentType(String code, String info)
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
