package com.myedu.project.dataBasic.domain;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import com.myedu.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 标签管理对象 sys_label
 * 
 * @author 梁龙飞
 * @date 2020-01-04
 */
public class SysLabel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 标贴名称 */
    @Excel(name = "标贴名称")
    private String name;

    /** 类型（0：门店标贴1：课程标贴） */
    @Excel(name = "类型", readConverterExp = "0=：门店标贴1：课程标贴")
    private String type;

    /** 标贴颜色 */
    @Excel(name = "标贴颜色")
    private String color;

    /** 可用状态（0可用1不可用） */
    @Excel(name = "可用状态", readConverterExp = "0=可用1不可用")
    private String isused;

    /** 创建人Id */
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
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setColor(String color) 
    {
        this.color = color;
    }

    public String getColor() 
    {
        return color;
    }
    public void setIsused(String isused) 
    {
        this.isused = isused;
    }

    public String getIsused() 
    {
        return isused;
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
            .append("name", getName())
            .append("type", getType())
            .append("color", getColor())
            .append("isused", getIsused())
            .append("createById", getCreateById())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
