package com.myedu.project.parents.domain;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import com.myedu.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 学生错题记录对象 yun_stu_mistake
 * 
 * @author 梁少鹏
 * @date 2019-12-29
 */
@ApiModel("学生错题实体")
public class YunStuMistake extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("学生错题主键Id")
    private Long id;

    /** 错题来源 */
    @ApiModelProperty("错题来源")
    @Excel(name = "错题来源")
    private String source;

    /** 错题类型 */
    @ApiModelProperty("错题类型")
    @Excel(name = "错题类型")
    private String type;

    /** 错题原因 */
    @ApiModelProperty("错题原因")
    @Excel(name = "错题原因")
    private String reason;

    /** 错题图片地址 */
    @ApiModelProperty("错题图片地址")
    private String images;

    /** 解决思路 */
    @ApiModelProperty("解决思路")
    @Excel(name = "解决思路")
    private String solutions;

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
    public void setSource(String source) 
    {
        this.source = source;
    }

    public String getSource() 
    {
        return source;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setReason(String reason) 
    {
        this.reason = reason;
    }

    public String getReason() 
    {
        return reason;
    }
    public void setImages(String images) 
    {
        this.images = images;
    }

    public String getImages() 
    {
        return images;
    }
    public void setSolutions(String solutions) 
    {
        this.solutions = solutions;
    }

    public String getSolutions() 
    {
        return solutions;
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
            .append("source", getSource())
            .append("type", getType())
            .append("reason", getReason())
            .append("images", getImages())
            .append("solutions", getSolutions())
            .append("studentId", getStudentId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .append("createById", getCreateById())
            .toString();
    }
}
