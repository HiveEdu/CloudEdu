package com.myedu.project.account.domain;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import com.myedu.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 账户资金变动流水对象 yun_account_change
 * 
 * @author 梁少鹏
 * @date 2020-01-25
 */
public class YunAccountChange extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 类型(1.充值 2.支付 3.退款 4.提现 5.内部调账) */
    @Excel(name = "类型(1.充值 2.支付 3.退款 4.提现 5.内部调账)")
    private String changeType;

    /** 变动前总金额 */
    @Excel(name = "变动前总金额")
    private BigDecimal preAmount;

    /** 可提现发生金额 */
    @Excel(name = "可提现发生金额")
    private BigDecimal cashAmount;

    /** 不可发生金额 */
    @Excel(name = "不可发生金额")
    private BigDecimal uncashAmount;

    /** 关联流水ID(change_type不同，对应不同流水表，如充值、支付、提现流水) */
    @Excel(name = "关联流水ID(change_type不同，对应不同流水表，如充值、支付、提现流水)")
    private Long refId;

    /** 删除标志（0代表存在 1代表删除） */
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setChangeType(String changeType)
    {
        this.changeType = changeType;
    }

    public String getChangeType() 
    {
        return changeType;
    }
    public void setPreAmount(BigDecimal preAmount)
    {
        this.preAmount = preAmount;
    }

    public BigDecimal getPreAmount()
    {
        return preAmount;
    }
    public void setCashAmount(BigDecimal cashAmount)
    {
        this.cashAmount = cashAmount;
    }

    public BigDecimal getCashAmount()
    {
        return cashAmount;
    }
    public void setUncashAmount(BigDecimal uncashAmount)
    {
        this.uncashAmount = uncashAmount;
    }

    public BigDecimal getUncashAmount()
    {
        return uncashAmount;
    }
    public void setRefId(Long refId) 
    {
        this.refId = refId;
    }

    public Long getRefId() 
    {
        return refId;
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
            .append("userId", getUserId())
            .append("changeType", getChangeType())
            .append("preAmount", getPreAmount())
            .append("cashAmount", getCashAmount())
            .append("uncashAmount", getUncashAmount())
            .append("refId", getRefId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .append("createById", getCreateById())
            .toString();
    }
}
