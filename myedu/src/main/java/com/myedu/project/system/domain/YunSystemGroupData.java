package com.myedu.project.system.domain;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import com.myedu.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
/**
 * 组合数据详情对象 yun_system_group_data
 * 
 * @author myedu
 * @date 2020-06-20
 */
public class YunSystemGroupData extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 组合数据详情ID */
    private Long id;

    /** 对应的数据名称 */
    @Excel(name = "对应的数据名称")
    private String groupName;

    /** 数据组对应的数据值（json数据） */
    @Excel(name = "数据组对应的数据值", readConverterExp = "j=son数据")
    private String value;

    /** 添加数据时间 */
    @Excel(name = "添加数据时间")
    private Integer addTime;

    /** 数据排序 */
    @Excel(name = "数据排序")
    private Integer sort;

    /** 状态（1：开启；2：关闭；） */
    @Excel(name = "状态", readConverterExp = "1=：开启；2：关闭；")
    private Integer status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setGroupName(String groupName) 
    {
        this.groupName = groupName;
    }

    public String getGroupName() 
    {
        return groupName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public String getValue() 
    {
        return value;
    }
    public void setAddTime(Integer addTime) 
    {
        this.addTime = addTime;
    }

    public Integer getAddTime() 
    {
        return addTime;
    }



    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("groupName", getGroupName())
            .append("value", getValue())
            .append("addTime", getAddTime())
            .append("sort", getSort())
            .append("status", getStatus())
            .toString();
    }
}
