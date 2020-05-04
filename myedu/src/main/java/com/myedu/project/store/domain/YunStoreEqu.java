package com.myedu.project.store.domain;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import com.myedu.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 门店设备对象 yun_store_equ
 * 
 * @author lsp
 * @date 2020-05-04
 */
public class YunStoreEqu extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 所属门店 */
    @Excel(name = "所属门店")
    private Long storeId;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String equName;

    /** 设备型号 */
    @Excel(name = "设备型号")
    private String model;

    /** 设备地址 */
    @Excel(name = "设备地址")
    private String address;

    /** 设备登录名 */
    @Excel(name = "设备登录名")
    private String userName;

    /** 设备登录密码 */
    @Excel(name = "设备登录密码")
    private String passward;

    /** 端口 */
    @Excel(name = "端口")
    private String port;

    /** 状态(1在线2离线) */
    @Excel(name = "状态(1在线2离线)")
    private String status;

    /** 是否可用 */
    @Excel(name = "是否可用")
    private String isuse;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 创建人Id */
    @Excel(name = "创建人Id")
    private Long createById;
    /** 门店名称 */
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
    public void setEquName(String equName) 
    {
        this.equName = equName;
    }

    public String getEquName() 
    {
        return equName;
    }
    public void setModel(String model) 
    {
        this.model = model;
    }

    public String getModel() 
    {
        return model;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setPassward(String passward) 
    {
        this.passward = passward;
    }

    public String getPassward() 
    {
        return passward;
    }
    public void setPort(String port) 
    {
        this.port = port;
    }

    public String getPort() 
    {
        return port;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setIsuse(String isuse) 
    {
        this.isuse = isuse;
    }

    public String getIsuse() 
    {
        return isuse;
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
            .append("equName", getEquName())
            .append("model", getModel())
            .append("address", getAddress())
            .append("userName", getUserName())
            .append("passward", getPassward())
            .append("port", getPort())
            .append("status", getStatus())
            .append("isuse", getIsuse())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .append("createById", getCreateById())
            .append("storeName", getStoreName())
            .toString();
    }
}
