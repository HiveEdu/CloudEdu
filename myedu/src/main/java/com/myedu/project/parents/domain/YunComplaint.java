package com.myedu.project.parents.domain;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import com.myedu.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 投诉对象 yun_complaint
 * 
 * @author myedu
 * @date 2020-01-18
 */
public class YunComplaint extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 投诉谁 */
    @Excel(name = "投诉谁")
    private String complaint;

    /** 投诉理由 */
    @Excel(name = "投诉理由")
    private String reason;

    /** 投诉证据 */
    @Excel(name = "投诉理证据")
    private String evidentImg;

    /** 删除标志（0代表存在 1代表删除） */
    @Excel(name = "删除标志")
    private String delFlag;

    /** 创建人Id */
    private Long createById;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setComplaint(String complaint) 
    {
        this.complaint = complaint;
    }

    public String getComplaint() 
    {
        return complaint;
    }
    public void setReason(String reason) 
    {
        this.reason = reason;
    }

    public String getReason() 
    {
        return reason;
    }
    public void setEvidentImg(String evidentImg) 
    {
        this.evidentImg = evidentImg;
    }

    public String getEvidentImg() 
    {
        return evidentImg;
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
            .append("complaint", getComplaint())
            .append("reason", getReason())
            .append("evidentImg", getEvidentImg())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .append("createById", getCreateById())
            .toString();
    }
}
