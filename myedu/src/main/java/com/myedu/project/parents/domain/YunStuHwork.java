package com.myedu.project.parents.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.myedu.framework.aspectj.lang.annotation.Excel;
import java.util.Date;
import com.myedu.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 学生作业对象 yun_stu_hwork
 * 
 * @author 梁少鹏
 * @date 2019-12-29
 */
public class YunStuHwork extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 关联学生Id */
    private Long studentId;

    /** 作业类型 */
    @Excel(name = "作业类型")
    private String hworkType;

    /** 作业名称 */
    @Excel(name = "作业名称")
    private String hworkName;

    /** 完成状态 */
    @Excel(name = "完成状态")
    private String hworkStatus;

    /** 完成时间 */
    @Excel(name = "完成时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date hworkTime;

    /** 作业内容 */
    @Excel(name = "作业内容")
    private String hworkContent;

    /** 作业照片地址 */
    private String hworkImage;

    /** 删除标志（0代表存在 1代表删除） */
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
    public void setStudentId(Long studentId) 
    {
        this.studentId = studentId;
    }

    public Long getStudentId() 
    {
        return studentId;
    }
    public void setHworkType(String hworkType) 
    {
        this.hworkType = hworkType;
    }

    public String getHworkType() 
    {
        return hworkType;
    }
    public void setHworkName(String hworkName) 
    {
        this.hworkName = hworkName;
    }

    public String getHworkName() 
    {
        return hworkName;
    }
    public void setHworkStatus(String hworkStatus) 
    {
        this.hworkStatus = hworkStatus;
    }

    public String getHworkStatus() 
    {
        return hworkStatus;
    }
    public void setHworkTime(Date hworkTime) 
    {
        this.hworkTime = hworkTime;
    }

    public Date getHworkTime() 
    {
        return hworkTime;
    }
    public void setHworkContent(String hworkContent) 
    {
        this.hworkContent = hworkContent;
    }

    public String getHworkContent() 
    {
        return hworkContent;
    }
    public void setHworkImage(String hworkImage) 
    {
        this.hworkImage = hworkImage;
    }

    public String getHworkImage() 
    {
        return hworkImage;
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
            .append("studentId", getStudentId())
            .append("hworkType", getHworkType())
            .append("hworkName", getHworkName())
            .append("hworkStatus", getHworkStatus())
            .append("hworkTime", getHworkTime())
            .append("hworkContent", getHworkContent())
            .append("hworkImage", getHworkImage())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .append("createById", getCreateById())
            .toString();
    }
}
