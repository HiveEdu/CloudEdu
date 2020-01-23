package com.myedu.project.parents.domain;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import com.myedu.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 学生身高体重记录对象 yun_stu_hw
 * 
 * @author  llf
 * @date 2019-12-29
 */
@ApiModel("学生身高体重实体")
public class YunStuHw extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学生身高体重表主键Id */
    @ApiModelProperty("学生身高体重表主键Id")
    private Long id;

    /** 身高（cm） */
    @ApiModelProperty("身高（cm）")
    @Excel(name = "身高", readConverterExp = "c=m")
    private Double height;

    /** 体重（kg） */
    @ApiModelProperty("体重（kg）")
    @Excel(name = "体重", readConverterExp = "k=g")
    private Double weight;

    /** 关联学生id */
    @ApiModelProperty("关联学生id")
    @Excel(name = "关联学生id")
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
    public void setHeight(Double height) 
    {
        this.height = height;
    }

    public Double getHeight() 
    {
        return height;
    }
    public void setWeight(Double weight) 
    {
        this.weight = weight;
    }

    public Double getWeight() 
    {
        return weight;
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
            .append("height", getHeight())
            .append("weight", getWeight())
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
