package com.myedu.project.order.domain;

import java.math.BigDecimal;
import java.util.Map;
public class WxMssVo {
    //private String touser;//用户openid
    private String template_id;//订阅消息模版id
    private String page = "pages/index/index";//默认跳到小程序首页
    private Map<String, TemplateData> data;//推送文字

    // 微信用户id
    private Long       uid;

    // 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段
    private String     unionid;

    // 用户的标识，对当前公众号唯一
    private String     openid;

    // 小程序唯一身份ID
    private String     routineOpenid;

    // 用户的昵称
    private String     nickname;

    // 用户头像
    private String     headimgurl;

    // 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
    private Integer    sex;

    // 用户所在城市
    private String     city;

    // 用户的语言，简体中文为zh_CN
    private String     language;

    // 用户所在省份
    private String     province;

    // 用户所在国家
    private String     country;

    // 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
    private String     remark;

    // 用户所在的分组ID（兼容旧的用户分组接口）
    private Integer    groupid;

    // 用户被打上的标签ID列表
    private String     tagidList;

    // 用户是否订阅该公众号标识
    private Integer    subscribe;

    // 关注公众号时间
    private Integer    subscribeTime;

    // 添加时间
    private Integer    addTime;

    // 一级推荐人
    private Integer    stair;

    // 二级推荐人
    private Integer    second;

    // 一级推荐人订单
    private Integer    orderStair;

    // 二级推荐人订单
    private Integer    orderSecond;

    // 佣金
    private BigDecimal nowMoney;

    // 小程序用户会话密匙
    private String     sessionKey;

    // 用户类型
    private String     userType;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getRoutineOpenid() {
        return routineOpenid;
    }

    public void setRoutineOpenid(String routineOpenid) {
        this.routineOpenid = routineOpenid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public String getTagidList() {
        return tagidList;
    }

    public void setTagidList(String tagidList) {
        this.tagidList = tagidList;
    }

    public Integer getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Integer subscribe) {
        this.subscribe = subscribe;
    }

    public Integer getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(Integer subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public Integer getAddTime() {
        return addTime;
    }

    public void setAddTime(Integer addTime) {
        this.addTime = addTime;
    }

    public Integer getStair() {
        return stair;
    }

    public void setStair(Integer stair) {
        this.stair = stair;
    }

    public Integer getSecond() {
        return second;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }

    public Integer getOrderStair() {
        return orderStair;
    }

    public void setOrderStair(Integer orderStair) {
        this.orderStair = orderStair;
    }

    public Integer getOrderSecond() {
        return orderSecond;
    }

    public void setOrderSecond(Integer orderSecond) {
        this.orderSecond = orderSecond;
    }

    public BigDecimal getNowMoney() {
        return nowMoney;
    }

    public void setNowMoney(BigDecimal nowMoney) {
        this.nowMoney = nowMoney;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }



    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Map<String, TemplateData> getData() {
        return data;
    }

    public void setData(Map<String, TemplateData> data) {
        this.data = data;
    }
}
