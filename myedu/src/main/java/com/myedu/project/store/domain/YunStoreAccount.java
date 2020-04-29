package com.myedu.project.store.domain;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import com.myedu.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 门店账目记录对象 yun_store_account
 * 
 * @author lsp
 * @date 2020-04-25
 */
@ApiModel("门店账目记录对象实体")
public class YunStoreAccount extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("主键id")
    private Long id;

    /** 门店id */
    @ApiModelProperty("门店id")
    @Excel(name = "门店id")
    private Long storeId;

    /** 账目类型（1出账2进账） */
    @ApiModelProperty("账目类型（1出账2进账）")
    @Excel(name = "账目类型", readConverterExp = "1=出账2进账")
    private String accountsType;

    /** 金额 */
    @ApiModelProperty("金额")
    @Excel(name = "金额")
    private Double money;

    /** 说明 */
    @ApiModelProperty("说明")
    @Excel(name = "说明")
    private String note;

    /** 删除标志（0代表存在 1代表删除） */
    @ApiModelProperty(" 删除标志（0代表存在 1代表删除）")
    private String delFlag;

    /** 创建人Id */
    @ApiModelProperty("创建人Id")
    @Excel(name = "创建人Id")
    private Long createById;

    @ApiModelProperty("storeName")
    private String storeName;
    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setStoreId(Long storeId) 
    {
        this.storeId = storeId;
    }

    public Long getStoreId() 
    {
        return storeId;
    }
    public void setAccountsType(String accountsType) 
    {
        this.accountsType = accountsType;
    }

    public String getAccountsType() 
    {
        return accountsType;
    }
    public void setMoney(Double money) 
    {
        this.money = money;
    }

    public Double getMoney() 
    {
        return money;
    }
    public void setNote(String note) 
    {
        this.note = note;
    }

    public String getNote() 
    {
        return note;
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

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("storeId", getStoreId())
            .append("accountsType", getAccountsType())
            .append("money", getMoney())
            .append("note", getNote())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .append("createById", getCreateById())
            .toString();
    }
}
