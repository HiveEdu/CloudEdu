package com.myedu.project.order.enums;

public enum StoreStuStatus {

    SIGNUP("1", "已报名"), ATSCHOOL("2", "在校"),
    LEAVESCHOOL("3", "离校");

    private final String code;
    private final String info;

    StoreStuStatus(String code, String info)
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
