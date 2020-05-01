package com.myedu.project.dynamic.domain;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import com.myedu.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 动态点赞对象 yun_dy_likes
 * 
 * @author 梁少鹏
 * @date 2020-01-01
 */
@ApiModel("动态点赞对象实体")
public class YunDyLikes extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("主键ID")
    private Long id;

    /** 动态关联ID */
    @ApiModelProperty("动态关联ID ")
    private Long dyId;

    /** 创建人Id */
    @ApiModelProperty("创建人Id ")
    @Excel(name = "创建人Id")
    private Long createById;

    /** 删除标志（0代表存在 1代表删除） */
    @ApiModelProperty("删除标志（0代表存在 1代表删除）")
    private String delFlag;

    /** 点赞状态（0取消点赞 1点赞） */
    @ApiModelProperty("点赞状态（0取消点赞 1点赞）")
    private String status;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("dyId", getDyId())
            .append("createById", getCreateById())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }

    public YunDyLikes(Long likedDynamicId , Long  likedUserId, String  status) {
        this.dyId = likedDynamicId;
        this.createById = likedUserId;
        this.status = status;
    }
    public YunDyLikes() {
    }
}
