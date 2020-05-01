package com.myedu.project.dynamic.domain;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import com.myedu.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 动态评论对象 yun_dy_comment
 * 
 * @author 梁少鹏
 * @date 2020-01-01
 */
@ApiModel("动态评论对象实体")
public class YunDyComment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("主键ID")
    private Long id;

    /** 动态关联Id */
    @ApiModelProperty("动态关联Id")
    @Excel(name = "动态关联Id")
    private Long dyId;

    /** 评论内容 */
    @ApiModelProperty("评论内容")
    @Excel(name = "评论内容")
    private String content;

    /** 创建人Id */
    @Excel(name = "创建人Id")
    private Long createById;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setDyId(Long dyId) 
    {
        this.dyId = dyId;
    }

    public Long getDyId() 
    {
        return dyId;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("dyId", getDyId())
            .append("content", getContent())
            .append("createById", getCreateById())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
