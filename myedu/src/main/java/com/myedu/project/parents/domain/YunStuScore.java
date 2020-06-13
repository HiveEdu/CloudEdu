package com.myedu.project.parents.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.myedu.framework.aspectj.lang.annotation.Excel;
import com.myedu.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;


/**
 * 学生成绩对象 yun_stu_score
 * 
 * @author 梁龙飞
 * @date 2019-12-30
 */
@ApiModel("学生成绩实体")
public class YunStuScore extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 成绩id */
    @ApiModelProperty("学生成绩主键Id")
    private Long scoreId;

    /** 考试分数 */
    @NotNull
   // @Digits(integer=20,fraction=2)
    @ApiModelProperty("考试分数")
    @Excel(name = "考试分数")
    private Double mark;

    /**学生id */
    @ApiModelProperty("学生id")
    @Excel(name = "学生id")
    private Long studentId;

    /**课程id */
    @ApiModelProperty("课程id")
    @Excel(name = "课程id")
    private Long courseId;

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
    public Long getStudentId() {
        return studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    /** 考试名称 */
    @NotBlank(message = "考试名称不能为空")
    @ApiModelProperty("考试名称")
    @Excel(name = "考试名称")
    private String examtname;

    /** 照片地址 */
    @ApiModelProperty("照片地址")
    @Excel(name = "照片地址")
    private String examimage;

    /**  创建id */
    @ApiModelProperty("创建id")
    @Excel(name = " 创建id")
    private Long createById;

    /** 更新标志 */
    @ApiModelProperty("更新标志")
    private String delFlag;

    /** 考试时间 */
    @ApiModelProperty("考试时间")
    @Excel(name = "考试时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date examtime;

    public void setScoreId(Long scoreId) 
    {
        this.scoreId = scoreId;
    }

    public Long getScoreId() 
    {
        return scoreId;
    }
    public void setMark(Double mark) 
    {
        this.mark = mark;
    }

    public Double getMark() 
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
        return "YunStuScore{" +
                "scoreId=" + scoreId +
                ", mark=" + mark +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", examtname='" + examtname + '\'' +
                ", examimage='" + examimage + '\'' +
                ", createById=" + createById +
                ", delFlag='" + delFlag + '\'' +
                ", examtime=" + examtime +
                '}';
    }

    public void setExamtime(Date examtime)
    {
        this.examtime = examtime;
    }

    public Date getExamtime() 
    {
        return examtime;
    }

}
