package com.myedu.project.order.enums;

public enum OrderStatus {

    TOBEPAID("1", "交易创建待支付"),
    HAVEAREFUND("2", "退款成功"),
    TIMEEND("3", "交易超时"),
    HAVETOPAY("4", "交易成功"),
    END("5", "交易结束并不可退款");
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
