package com.myedu.project.account.domain;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import com.myedu.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 账户管理对象 yun_account
 * 
 * @author 梁少鹏
 * @date 2020-01-25
 */
public class YunAccount extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 总金额 */
    @Excel(name = "总金额")
    private BigDecimal totalAmount;

    /** 可提现金额 */
    @Excel(name = "可提现金额")
    private BigDecimal caseAmount;

    /** 不可提现金额 */
    @Excel(name = "不可提现金额")
    private BigDecimal uncaseAmount;

    /** 冻结金额 */
    @Excel(name = "冻结金额")
    private BigDecimal freezeCaseAmount;

    /** 不可提现冻结金额 */
    @Excel(name = "不可提现冻结金额")
    private BigDecimal freezeUncaseAmount;

    /** 状态（0可用1不可用） */
    @Excel(name = "状态", readConverterExp = "0=可用1不可用")
    private String status;

    /** 删除标志（0代表存在 1代表删除） */
    @Excel(name = "删除标志", readConverterExp = "0=代表存在,1=代表删除")
    private String delFlag;

    /** 创建人Id */
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
    public void setTotalAmount(BigDecimal totalAmount)
    {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalAmount()
    {
        return totalAmount;
    }
    public void setCaseAmount(BigDecimal caseAmount)
    {
        this.caseAmount = caseAmount;
    }

    public BigDecimal getCaseAmount()
    {
        return caseAmount;
    }
    public void setUncaseAmount(BigDecimal uncaseAmount)
    {
        this.uncaseAmount = uncaseAmount;
    }

    public BigDecimal getUncaseAmount()
    {
        return uncaseAmount;
    }
    public void setFreezeCaseAmount(BigDecimal freezeCaseAmount)
    {
        this.freezeCaseAmount = freezeCaseAmount;
    }

    public BigDecimal getFreezeCaseAmount()
    {
        return freezeCaseAmount;
    }
    public void setFreezeUncaseAmount(BigDecimal freezeUncaseAmount)
    {
        this.freezeUncaseAmount = freezeUncaseAmount;
    }

    public BigDecimal getFreezeUncaseAmount()
    {
        return freezeUncaseAmount;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
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
            .append("totalAmount", getTotalAmount())
            .append("caseAmount", getCaseAmount())
            .append("uncaseAmount", getUncaseAmount())
            .append("freezeCaseAmount", getFreezeCaseAmount())
            .append("freezeUncaseAmount", getFreezeUncaseAmount())
            .append("status", getStatus())
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
