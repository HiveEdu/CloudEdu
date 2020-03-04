package com.myedu.project.store.domain;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import com.myedu.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 门店学生管理对象 yun_store_stu
 * 
 * @author 梁少鹏
 * @date 2020-03-03
 */
public class YunStoreStu extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 门店id */
    @Excel(name = "门店id")
    private Long storeId;

    /** 学生id */
    @Excel(name = "学生id")
    private Long stuId;

    /** 学生状态 */
    @Excel(name = "学生状态")
    private String status;

    /** 签到类型 */
    @Excel(name = "签到类型")
    private String signinType;

    /** 分配班级 */
    @Excel(name = "分配班级")
    private Long  clbum;

    /** 删除标志（0代表存在 1代表删除） */
    @Excel(name = "删除标志", readConverterExp = "0=代表存在,1=代表删除")
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
    public void setStoreId(Long storeId) 
    {
        this.storeId = storeId;
    }

    public Long getStoreId() 
    {
        return storeId;
    }
    public void setStuId(Long stuId) 
    {
        this.stuId = stuId;
    }

    public Long getStuId() 
    {
        return stuId;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setSigninType(String signinType) 
    {
        this.signinType = signinType;
    }

    public String getSigninType() 
    {
        return signinType;
    }
    public void setClbum(Long  clbum)
    {
        this. clbum =  clbum;
    }

    public Long getClbum()
    {
        return  clbum;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("storeId", getStoreId())
            .append("stuId", getStuId())
            .append("status", getStatus())
            .append("signinType", getSigninType())
            .append("clbum", getClbum())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .append("createById", getCreateById())
            .toString();
    }
}
