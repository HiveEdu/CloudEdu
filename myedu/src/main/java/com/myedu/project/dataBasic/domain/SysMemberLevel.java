package com.myedu.project.dataBasic.domain;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import com.myedu.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 会员等级对象 sys_member_level
 * 
 * @author 梁少鹏
 * @date 2020-03-14
 */
public class SysMemberLevel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 会员名称 */
    @Excel(name = "会员名称")
    private String name;

    /** 有效时间(天) */
    @Excel(name = "有效时间(天)")
    private Long validDate;

    /** 1门店会员2普通用户会员 */
    @Excel(name = "1门店会员2普通用户会员")
    private String type;

    /** 是否为永久会员 */
    @Excel(name = "是否为永久会员")
    private String isForever;

    /** 会员等级 */
    @Excel(name = "会员等级")
    private Long grade;

    /** 享受折扣 */
    @Excel(name = "享受折扣")
    private Double discount;

    /** 会员卡背景 */
    @Excel(name = "会员卡背景")
    private String image;

    /** 会员图标 */
    @Excel(name = "会员图标")
    private String icon;

    /** 说明 */
    @Excel(name = "说明")
    private String note;

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
    public void setValidDate(Long validDate) 
    {
        this.validDate = validDate;
    }

    public Long getValidDate() 
    {
        return validDate;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setIsForever(String isForever) 
    {
        this.isForever = isForever;
    }

    public String getIsForever() 
    {
        return isForever;
    }
    public void setGrade(Long grade) 
    {
        this.grade = grade;
    }

    public Long getGrade() 
    {
        return grade;
    }
    public void setDiscount(Double discount) 
    {
        this.discount = discount;
    }

    public Double getDiscount() 
    {
        return discount;
    }
    public void setImage(String image) 
    {
        this.image = image;
    }

    public String getImage() 
    {
        return image;
    }
    public void setIcon(String icon) 
    {
        this.icon = icon;
    }

    public String getIcon() 
    {
        return icon;
    }
    public void setNote(String note) 
    {
        this.note = note;
    }

    public String getNote() 
    {
        return note;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("validDate", getValidDate())
            .append("type", getType())
            .append("isForever", getIsForever())
            .append("grade", getGrade())
            .append("discount", getDiscount())
            .append("image", getImage())
            .append("icon", getIcon())
            .append("note", getNote())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
