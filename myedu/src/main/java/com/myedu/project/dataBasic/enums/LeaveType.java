package com.myedu.project.dataBasic.enums;

public enum LeaveType {


    STOREVIP("1", "门店VIP"), COURSEVIP("2", "普通用户VIP");
    private final String code;
    private final String info;

    LeaveType(String code, String info)
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
