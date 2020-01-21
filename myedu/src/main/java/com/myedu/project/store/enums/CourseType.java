package com.myedu.project.store.enums;

public enum CourseType {

    ONLINE("0", "在售"), OFFLINE("1", "下线");

    private final String code;
    private final String info;

    CourseType(String code, String info)
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
