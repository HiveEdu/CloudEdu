package com.myedu.project.store.enums;

/**
 * 用户状态
 * 
 * @author ruoyi
 */
public enum StoryType
{
    STORE("0", "门店"), TRUSTEESHIP("1", "托管类型");

    private final String code;
    private final String info;

    StoryType(String code, String info)
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
