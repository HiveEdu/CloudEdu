package com.myedu.project.store.enums;

public enum StoreStatus {

    APPROVE("1", "审核通过"),REJECTED("2", "审核驳回"), ONLINE("3", "在售"), OFFLINE("4", "下线");

    private final String code;
    private final String info;

    StoreStatus(String code, String info)
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
