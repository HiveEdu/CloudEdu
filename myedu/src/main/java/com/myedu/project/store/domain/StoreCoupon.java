package com.myedu.project.store.domain;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import com.myedu.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 优惠券制作对象 store_coupon
 * 
 * @author 梁龙飞
 * @date 2020-02-03
 */
public class StoreCoupon extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 优惠券名称 */
    @Excel(name = "优惠券名称")
    private String title;

    /** 兑换消耗积分值 */
    @Excel(name = "兑换消耗积分值")
    private Long integral;

    /** 兑换的优惠券面值 */
    @Excel(name = "兑换的优惠券面值")
    private Double couponPrice;

    /** 最低消费多少金额可用优惠券 */
    @Excel(name = "最低消费多少金额可用优惠券")
    private Double useMinPrice;

    /** 优惠券有效期限（单位：天） */
    @Excel(name = "优惠券有效期限", readConverterExp = "单=位：天")
    private Long couponTime;

    /** 排序 */
    @Excel(name = "排序")
    private Long sort;

    /** 状态（0：关闭，1：开启） */
    @Excel(name = "状态", readConverterExp = "0=：关闭，1：开启")
    private Long status;

    /** 兑换项目添加时间 */
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Integer isDel;

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
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public void setIsDel(Integer isDel)
    {
        this.isDel = isDel;
    }

    public Integer getIsDel() 
    {
        return isDel;
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
            .append("addTime", getAddTime())
            .append("isDel", getIsDel())
            .toString();
    }
}
