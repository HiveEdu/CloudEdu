package com.myedu.project.system.domain;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import com.myedu.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
/**
/**
 * 活动管理对象 sys_activity
 * 
 * @author myedu
 * @date 2020-07-22
 */
public class SysActivity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 活动标题 */
    @Excel(name = "活动标题")
    private String title;

    /** 活动照片 */
    @Excel(name = "活动照片")
    private String picture;

    /** 活动链接 */
    @Excel(name = "活动链接")
    private String links;

    /** 开始时间 */
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private String endTime;

    /** 活动状态 */
    @Excel(name = "活动状态")
    private String status;

    /** $column.columnComment */
    @Excel(name = "活动状态")
    private String createById;

    /** 变更时间 */

    private String updateByTime;

    public String getUpdateByTime() {
        return updateByTime;
    }

    public void setUpdateByTime(String updateByTime) {
        this.updateByTime = updateByTime;
    }

    /** 删除标志 */
    private String delFlag;

    /** 活动内容 */
    @Excel(name = "活动内容")
    private String content;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setPicture(String picture) 
    {
        this.picture = picture;
    }

    public String getPicture() 
    {
        return picture;
    }
    public void setLinks(String links) 
    {
        this.links = links;
    }

    public String getLinks() 
    {
        return links;
    }
    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public Date getStartTime()
    {
        return startTime;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setCreateById(String createById) 
    {
        this.createById = createById;
    }

    public String getCreateById() 
    {
        return createById;
    }


    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    @Override
    public String getEndTime() {
        return endTime;
    }

    @Override
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getContent()
    {
        return content;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("picture", getPicture())
            .append("links", getLinks())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createById", getCreateById())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateByTime", getUpdateByTime())
            .append("delFlag", getDelFlag())
            .append("content", getContent())
            .toString();
    }
}
