package com.myedu.project.store.enums;

public enum CouponIssueStatus {

    STARE("1", "开启"), CLOSE("2", "关闭"), INVA("3", "无效");

    private final String code;
    private final String info;

    CouponIssueStatus(String code, String info)
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
