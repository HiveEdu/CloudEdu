package com.myedu.project.store.enums;

public enum labelType {

    STORE("0", "门店标签"), COURSE("1", "托管标签");

    private final String code;
    private final String info;

    labelType(String code, String info)
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
