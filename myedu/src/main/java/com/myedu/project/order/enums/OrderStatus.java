package com.myedu.project.order.enums;

public enum OrderStatus {
    REFSUCC("-2", "退货成功"),
    REFUNDAPP("-1", "申请退款"),
    DEFAULT("0","待发货"),
    TOBEPAID("1", "交易创建未支付"),
    HAVEAREFUND("2", "退款成功"),
    TIMEEND("3", "交易超时"),
    HAVETOPAY("4", "交易成功"),
    END("5", "交易结束并不可退款"),
    COMPLETED("6", "已核销");

    // 订单状态（-1 : 申请退款 -2 : 退货成功 0：待发货；1：待收货；2：已收货；3：待评价；-1：已退款）

//    STATUS_0("0", "默认"),
//    STATUS_1("1", "待收货"),
//    STATUS_2("2", "已收货"),
//    STATUS_3("3", "已完成"),

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
