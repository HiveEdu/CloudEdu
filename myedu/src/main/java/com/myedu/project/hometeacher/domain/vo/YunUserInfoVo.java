package com.myedu.project.hometeacher.domain.vo;

import com.myedu.project.hometeacher.domain.YunUserInfo;

public class YunUserInfoVo extends YunUserInfo {
    //昵称
    private String nickName;
    //头像
    private String avatar;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    //地址
    private String address;
    //性别
    private String sex;
}
