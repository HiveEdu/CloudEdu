package com.myedu.project.store.domain;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import com.myedu.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 点名签到对象 yun_store_signin
 * 
 * @author 梁龙飞
 * @date 2020-02-29
 */
public class YunStoreSignin extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 门店id */
    @Excel(name = "门店id")
    private Long storeId;
    /** 学生id */
    @Excel(name = "学生id")
    private Long stuId;

    /** 名单标题 */
    @Excel(name = "名单标题")
    private String nameTitle;

    /** 签到类型 */
    @Excel(name = "签到类型")
    private String signinType;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 创建人Id */
    @Excel(name = "创建人Id")
    private Long createById;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setStuId(Long stuId) 
    {
        this.stuId = stuId;
    }

    public Long getStuId() 
    {
        return stuId;
    }
    public void setNameTitle(String nameTitle) 
    {
        this.nameTitle = nameTitle;
    }

    public String getNameTitle() 
    {
        return nameTitle;
    }
    public void setSigninType(String signinType) 
    {
        this.signinType = signinType;
    }

    public String getSigninType() 
    {
        return signinType;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setCreateById(Long createById) 
    {
        this.createById = createById;
    }

    public Long getCreateById() 
    {
        return createById;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("storeId", getStoreId())
            .append("stuId", getStuId())
            .append("nameTitle", getNameTitle())
            .append("signinType", getSigninType())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .append("createById", getCreateById())
            .toString();
    }
}
