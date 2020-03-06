package com.myedu.project.store.enums;

public enum StudengStatus {

    ONLINE("2", "在校"), OFFLINE("3", "离校");

    private final String code;
    private final String info;

    StudengStatus(String code, String info)
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
