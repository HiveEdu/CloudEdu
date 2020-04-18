package com.myedu.project.store.domain;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import com.myedu.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 课程评论对象 yun_course_comment
 * 
 * @author 梁龙飞
 * @date 2020-04-13
 */
public class YunCourseComment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 课程关联Id */
    @Excel(name = "课程关联Id")
    private Long courseId;

    /** 评论内容 */
    @Excel(name = "评论内容")
    private String content;

    /** 创建人Id */
    @Excel(name = "创建人Id")
    private Long createById;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 评价类型 */
    @Excel(name = "评价类型")
    private String type;

    /** 课程门店Id */
    @Excel(name = "课程门店Id")
    private Long storeId;

    /** 评分内容 */
    @Excel(name = "评分内容")
    private String scoreContent;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /** 平均评分 */
    @Excel(name = "平均评分")
    private BigDecimal score;

    private String courseName;


    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCourseId(Long courseId) 
    {
        this.courseId = courseId;
    }

    public Long getCourseId() 
    {
        return courseId;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setCreateById(Long createById) 
    {
        this.createById = createById;
    }

    public Long getCreateById() 
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
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getScoreContent() {
        return scoreContent;
    }

    public void setScoreContent(String scoreContent) {
        this.scoreContent = scoreContent;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("courseId", getCourseId())
            .append("content", getContent())
            .append("createById", getCreateById())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .append("type", getType())
            .append("storeId", getStoreId())
            .append("scoreContent", getScoreContent())
            .append("score", getScore())
            .append("courseName", getCourseName())
            .toString();

    }
}
