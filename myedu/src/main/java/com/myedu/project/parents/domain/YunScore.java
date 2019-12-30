package com.myedu.project.parents.domain;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import com.myedu.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * score对象 yun_score
 * 
 * @author 梁龙飞
 * @date 2019-12-30
 */
public class YunScore extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 成绩id */
    private Long scoreId;

    /** 考试分数 */
    @Excel(name = "考试分数")
    private Long mark;

    /** 考试科目 */
    @Excel(name = "考试科目")
    private String examtname;

    /** 考试时间 */
    @Excel(name = "考试时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date examtime;

    /** 照片地址 */
    @Excel(name = "照片地址")
    private String examimage;

    /**  创建id */
    @Excel(name = " 创建id")
    private Long createById;

    /** 更新标志 */
    private String delFlag;

    public void setScoreId(Long scoreId) 
    {
        this.scoreId = scoreId;
    }

    public Long getScoreId() 
    {
        return scoreId;
    }
    public void setMark(Long mark) 
    {
        this.mark = mark;
    }

    public Long getMark() 
    {
        return mark;
    }
    public void setExamtname(String examtname) 
    {
        this.examtname = examtname;
    }

    public String getExamtname() 
    {
        return examtname;
    }
    public void setExamtime(Date examtime) 
    {
        this.examtime = examtime;
    }

    public Date getExamtime() 
    {
        return examtime;
    }
    public void setExamimage(String examimage) 
    {
        this.examimage = examimage;
    }

    public String getExamimage() 
    {
        return examimage;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("scoreId", getScoreId())
            .append("mark", getMark())
            .append("examtname", getExamtname())
            .append("examtime", getExamtime())
            .append("remark", getRemark())
            .append("examimage", getExamimage())
            .append("createById", getCreateById())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
