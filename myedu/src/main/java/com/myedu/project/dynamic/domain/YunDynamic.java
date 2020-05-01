package com.myedu.project.dynamic.domain;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import com.myedu.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 云托管动态管理对象 yun_dynamic
 * 
 * @author 梁少鹏
 * @date 2020-01-01
 */
@ApiModel("云托管动态管理对象实体")
public class YunDynamic extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("主键ID")
    private Long id;

    /** 动态内容 */
    @ApiModelProperty("动态内容")
    private String content;

    /** 动态图片 */
    @ApiModelProperty("动态图片")
    private String picture;

    /** 动态类型（0：家长发表1：老师发表） */
    @ApiModelProperty("（0：家长发表1：老师发表）")
    @Excel(name = "动态类型", readConverterExp = "0=：家长发表1：老师发表")
    private String type;

    /** 点赞数量 */
    @ApiModelProperty("点赞数量")
    private Long likes;

    /** 评论数量 */
    @ApiModelProperty("评论数量")
    private Long comments;

    /** 创建人Id */
    @ApiModelProperty("创建人Id")
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
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setPicture(String picture) 
    {
        this.picture = picture;
    }

    public String getPicture() 
    {
        return picture;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setLikes(Long likes) 
    {
        this.likes = likes;
    }

    public Long getLikes() 
    {
        return likes;
    }
    public void setComments(Long comments) 
    {
        this.comments = comments;
    }

    public Long getComments() 
    {
        return comments;
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
            .append("content", getContent())
            .append("picture", getPicture())
            .append("type", getType())
            .append("likes", getLikes())
            .append("comments", getComments())
            .append("createById", getCreateById())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
