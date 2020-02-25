package com.myedu.project.store.enums;

public enum receiveStatus {

    STORE("0", "未使用"), COURSE("1", "已使用");

    private final String code;
    private final String info;

    receiveStatus(String code, String info)
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
