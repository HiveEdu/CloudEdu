package com.myedu.project.store.domain;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import java.util.Date;
import com.myedu.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 作业表关联学生对象 yun_store_work_stu
 * 
 * @author myedu
 * @date 2020-07-05
 */
public class YunStoreWorkStu extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 门店作业id */
    @Excel(name = "门店作业id")
    private Long storeWorkId;

    /** 学生id */
    @Excel(name = "学生id")
    private Long stuId;

    /** 作业完成程度(1:未完成2：作业中3:已完成) */
    @Excel(name = "作业完成程度(1:未完成2：作业中3:已完成)")
    private String status;

    /** 完成时间 */
    @Excel(name = "完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date complTime;

    /** 批改内容 */
    @Excel(name = "批改内容")
    private String content;

    /** 批改人 */
    @Excel(name = "批改人")
    private String correctBy;

    /** 家长评语 */
    @Excel(name = "家长评语")
    private String comments;

    /** $column.columnComment */
    @Excel(name = "家长评语")
    private String commentsBy;

    /** 评语时间 */
    @Excel(name = "评语时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date commentsTime;

    /** 批改时间 */
    @Excel(name = "批改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date correctTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setStoreWorkId(Long storeWorkId) 
    {
        this.storeWorkId = storeWorkId;
    }

    public Long getStoreWorkId() 
    {
        return storeWorkId;
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
    public void setComplTime(Date complTime) 
    {
        this.complTime = complTime;
    }

    public Date getComplTime() 
    {
        return complTime;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setCorrectBy(String correctBy) 
    {
        this.correctBy = correctBy;
    }

    public String getCorrectBy() 
    {
        return correctBy;
    }
    public void setComments(String comments) 
    {
        this.comments = comments;
    }

    public String getComments() 
    {
        return comments;
    }
    public void setCommentsBy(String commentsBy) 
    {
        this.commentsBy = commentsBy;
    }

    public String getCommentsBy() 
    {
        return commentsBy;
    }
    public void setCommentsTime(Date commentsTime) 
    {
        this.commentsTime = commentsTime;
    }

    public Date getCommentsTime() 
    {
        return commentsTime;
    }
    public void setCorrectTime(Date correctTime) 
    {
        this.correctTime = correctTime;
    }

    public Date getCorrectTime() 
    {
        return correctTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("storeWorkId", getStoreWorkId())
            .append("stuId", getStuId())
            .append("status", getStatus())
            .append("complTime", getComplTime())
            .append("content", getContent())
            .append("correctBy", getCorrectBy())
            .append("comments", getComments())
            .append("commentsBy", getCommentsBy())
            .append("commentsTime", getCommentsTime())
            .append("correctTime", getCorrectTime())
            .toString();
    }
}
