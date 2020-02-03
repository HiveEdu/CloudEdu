package com.myedu.project.store.domain;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import com.myedu.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 优惠券制作对象 store_coupon_issue
 * 
 * @author 梁龙飞
 * @date 2020-02-02
 */
public class StoreCouponIssue extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 优惠券ID */
    @Excel(name = "优惠券ID")
    private Long cid;

    /** $column.columnComment */
    @Excel(name = "优惠券ID")
    private String cname;

    /** 优惠券领取开启时间 */
    @Excel(name = "优惠券领取开启时间")
    private Long startTime;

    /** 优惠券领取结束时间 */
    @Excel(name = "优惠券领取结束时间")
    private Long endTime;

    /** 优惠券领取数量 */
    @Excel(name = "优惠券领取数量")
    private Long totalCount;

    /** 优惠券剩余领取数量 */
    @Excel(name = "优惠券剩余领取数量")
    private Long remainCount;

    /** $column.columnComment */
    @Excel(name = "优惠券剩余领取数量")
    private Long isPermanent;

    /** 1 正常 0 未开启 -1 已无效 */
    @Excel(name = "1 正常 0 未开启 -1 已无效")
    private Long status;

    /** $column.columnComment */
    @Excel(name = "1 正常 0 未开启 -1 已无效")
    private Long isDel;

    /** 优惠券添加时间 */
    @Excel(name = "优惠券添加时间")
    private Long addTime;

    /** $column.columnComment */
    @Excel(name = "优惠券添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTimeDate;

    /** $column.columnComment */
    @Excel(name = "优惠券添加时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTimeDate;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCid(Long cid) 
    {
        this.cid = cid;
    }

    public Long getCid() 
    {
        return cid;
    }
    public void setCname(String cname) 
    {
        this.cname = cname;
    }

    public String getCname() 
    {
        return cname;
    }
    public void setStartTime(Long startTime) 
    {
        this.startTime = startTime;
    }

    public Long getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(Long endTime)
    {
        this.endTime = endTime;
    }

//    public Long getEndTime()
//    {
//        return endTime;
//    }
    public void setTotalCount(Long totalCount)
    {
        this.totalCount = totalCount;
    }

    public Long getTotalCount() 
    {
        return totalCount;
    }
    public void setRemainCount(Long remainCount) 
    {
        this.remainCount = remainCount;
    }

    public Long getRemainCount() 
    {
        return remainCount;
    }
    public void setIsPermanent(Long isPermanent) 
    {
        this.isPermanent = isPermanent;
    }

    public Long getIsPermanent() 
    {
        return isPermanent;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setIsDel(Long isDel) 
    {
        this.isDel = isDel;
    }

    public Long getIsDel() 
    {
        return isDel;
    }
    public void setAddTime(Long addTime) 
    {
        this.addTime = addTime;
    }

    public Long getAddTime() 
    {
        return addTime;
    }
    public void setEndTimeDate(Date endTimeDate) 
    {
        this.endTimeDate = endTimeDate;
    }

    public Date getEndTimeDate() 
    {
        return endTimeDate;
    }
    public void setStartTimeDate(Date startTimeDate) 
    {
        this.startTimeDate = startTimeDate;
    }

    public Date getStartTimeDate() 
    {
        return startTimeDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("cid", getCid())
            .append("cname", getCname())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("totalCount", getTotalCount())
            .append("remainCount", getRemainCount())
            .append("isPermanent", getIsPermanent())
            .append("status", getStatus())
            .append("isDel", getIsDel())
            .append("addTime", getAddTime())
            .append("endTimeDate", getEndTimeDate())
            .append("startTimeDate", getStartTimeDate())
            .toString();
    }
}
