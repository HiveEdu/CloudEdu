package com.myedu.project.order.enums;

public enum OrderStatus {

    TOBEPAID("1", "待支付"), HAVETOPAY("2", "已支付"),
    HAVEAREFUND("3", "已退款");

    private final String code;
    private final String info;

    OrderStatus(String code, String info)
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
