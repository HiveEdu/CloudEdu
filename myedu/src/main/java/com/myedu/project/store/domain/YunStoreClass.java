package com.myedu.project.store.domain;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import com.myedu.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 分班管理对象 yun_store_class
 * 
 * @author 梁龙飞
 * @date 2020-04-18
 */
public class YunStoreClass extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 班级 */
    private Long id;

    /** 班级名称 */
    @Excel(name = "班级名称")
    private String name;

    /** 班级图标 */
    @Excel(name = "班级图标")
    private String classLogo;

    /** 主教老师 */
    @Excel(name = "主教老师")
    private String bishopTeacher;

    /** 助教老师 */
    @Excel(name = "助教老师")
    private String assistantTeacher;

    /** 删除标志 */
    private String delFlag;

    /** 门店id */
    @Excel(name = "门店id")
    private Long storeId;

    /** 创建人id */
    @Excel(name = "创建人id")
    private Long createById;

    /** 修改人id */
    @Excel(name = "修改人id")
    private Long updateById;

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
    public void setClassLogo(String classLogo) 
    {
        this.classLogo = classLogo;
    }

    public String getClassLogo() 
    {
        return classLogo;
    }
    public void setBishopTeacher(String bishopTeacher) 
    {
        this.bishopTeacher = bishopTeacher;
    }

    public String getBishopTeacher() 
    {
        return bishopTeacher;
    }
    public void setAssistantTeacher(String assistantTeacher) 
    {
        this.assistantTeacher = assistantTeacher;
    }

    public String getAssistantTeacher() 
    {
        return assistantTeacher;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setStoreId(Long storeId) 
    {
        this.storeId = storeId;
    }

    public Long getStoreId() 
    {
        return storeId;
    }
    public void setCreateById(Long createById) 
    {
        this.createById = createById;
    }

    public Long getCreateById() 
    {
        return createById;
    }
    public void setUpdateById(Long updateById) 
    {
        this.updateById = updateById;
    }

    public Long getUpdateById() 
    {
        return updateById;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("classLogo", getClassLogo())
            .append("bishopTeacher", getBishopTeacher())
            .append("assistantTeacher", getAssistantTeacher())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .append("storeId", getStoreId())
            .append("createById", getCreateById())
            .append("updateById", getUpdateById())
            .toString();
    }
}
