package com.myedu.project.parents.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.myedu.framework.aspectj.lang.annotation.Excel;
import java.util.Date;
import com.myedu.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 学生请假对象 yun_stu_leave
 *
 * @author 梁少鹏
 * @date 2019-12-28
 */
@ApiModel("学生请假实体")
public class YunStuLeave extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学生作业主键Id */
    @ApiModelProperty("学生作业主键Id")
    private Long id;

    /** 请假原因 */
    @ApiModelProperty("请假原因")
    @Excel(name = "请假原因")
    private String reason;

    /** 开始时间 */
    @ApiModelProperty("开始时间")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date leaveStartTime;
    /** 结束时间 */
    @ApiModelProperty("结束时间")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date leaveEndTime;

    /** 关联学生ID */
    @ApiModelProperty("关联学生ID")
    @Excel(name = "关联学生ID")
    private Long studentId;

    /** 删除标志（0代表存在 1代表删除） */
    @ApiModelProperty("删除标志（0代表存在 1代表删除）")
    private String delFlag;

    /** 创建人Id */
    @ApiModelProperty("创建人Id")
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
    public void setReason(String reason)
    {
        this.reason = reason;
    }

    public String getReason()
    {
        return reason;
    }
    public void setLeaveStartTime(Date leaveStartTime)
    {
        this.leaveStartTime = leaveStartTime;
    }

    public Date getLeaveStartTime()
    {
        return leaveStartTime;
    }
    public void setLeaveEndTime(Date leaveEndTime)
    {
        this.leaveEndTime = leaveEndTime;
    }
    public Date getLeaveEndTime(){
        return  leaveEndTime;
    }
    public void setStudentId(Long studentId)
    {
        this.studentId = studentId;
    }

    public Long getStudentId()
    {
        return studentId;
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
                .append("reason", getReason())
                .append("leaveStartTime", getLeaveStartTime())
                .append("leaveEndTime", getLeaveEndTime())
                .append("studentId", getStudentId())
                .append("remark", getRemark())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("delFlag", getDelFlag())
                .append("createById", getCreateById())
                .toString();
    }
}
