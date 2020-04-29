package com.myedu.project.store.domain;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import com.myedu.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 店铺优惠券对象 yun_store_coupon
 * 
 * @author 梁少鹏
 * @date 2020-02-03
 */
@ApiModel("店铺优惠券对象实体")
public class YunStoreCoupon extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    @ApiModelProperty("主键id")
    private Long id;

    /** 优惠券名称 */
    @ApiModelProperty("优惠券名称")
    @Excel(name = "优惠券名称")
    private String title;

    /** 兑换消耗积分值 */
    @ApiModelProperty("兑换消耗积分值")
    @Excel(name = "兑换消耗积分值")
    private Long integral;

    /** 兑换的优惠券面值 */
    @ApiModelProperty("兑换的优惠券面值")
    @Excel(name = "兑换的优惠券面值")
    private Double couponPrice;

    /** 最低消费多少金额可用优惠券 */
    @ApiModelProperty("最低消费多少金额可用优惠券")
    @Excel(name = "最低消费多少金额可用优惠券")
    private Double useMinPrice;

    /** 优惠券有效期限（单位：天） */
    @ApiModelProperty("优惠券有效期限(单位：天）")
    @Excel(name = "优惠券有效期限", readConverterExp = "单=位：天")
    private Long couponTime;

    /** 排序 */
    @ApiModelProperty("排序")
    @Excel(name = "排序")
    private Long sort;

    /** 状态（0：关闭，1：开启） */
    @ApiModelProperty("状态（0：关闭，1：开启）")
    @Excel(name = "状态", readConverterExp = "0=：关闭，1：开启")
    private String  status;

    /** 删除标志（0代表存在 1代表删除） */
    @ApiModelProperty("删除标志（0代表存在 1代表删除")
    @Excel(name = "删除标志", readConverterExp = "0=代表存在,1=代表删除")
    private String delFlag;

    /** 创建人Id */
    @ApiModelProperty("创建人Id")
    @Excel(name = "创建人Id")
    private Long createById;

    /** 门店Id */
    @ApiModelProperty("门店Id")
    @Excel(name = "门店Id")
    private Long storeId;

    /** 发布次数*/
    @ApiModelProperty("发布次数")
    @Excel(name = "发布次数")
    private Long publishNum;


    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setIntegral(Long integral) 
    {
        this.integral = integral;
    }

    public Long getIntegral() 
    {
        return integral;
    }
    public void setCouponPrice(Double couponPrice) 
    {
        this.couponPrice = couponPrice;
    }

    public Double getCouponPrice() 
    {
        return couponPrice;
    }
    public void setUseMinPrice(Double useMinPrice) 
    {
        this.useMinPrice = useMinPrice;
    }

    public Double getUseMinPrice() 
    {
        return useMinPrice;
    }
    public void setCouponTime(Long couponTime) 
    {
        this.couponTime = couponTime;
    }

    public Long getCouponTime() 
    {
        return couponTime;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }
    public void setStatus(String  status)
    {
        this.status = status;
    }

    public String  getStatus()
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
    public void setStoreId(Long storeId) 
    {
        this.storeId = storeId;
    }

    public Long getStoreId() 
    {
        return storeId;
    }

    public Long getPublishNum() {
        return publishNum;
    }

    public void setPublishNum(Long publishNum) {
        this.publishNum = publishNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("integral", getIntegral())
            .append("couponPrice", getCouponPrice())
            .append("useMinPrice", getUseMinPrice())
            .append("couponTime", getCouponTime())
            .append("sort", getSort())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .append("createById", getCreateById())
            .append("storeId", getStoreId())
            .append("publishNum", getPublishNum())
            .toString();
    }
}
