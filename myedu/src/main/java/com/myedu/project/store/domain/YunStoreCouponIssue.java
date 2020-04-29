package com.myedu.project.store.domain;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import com.myedu.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
/**
 * 店铺优惠券发布对象 yun_store_coupon_issue
 * 
 * @author 梁少鹏
 * @date 2020-02-03
 */
@ApiModel("店铺优惠券发布对象实体")
public class YunStoreCouponIssue extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    /** 优惠券ID */
    @ApiModelProperty("优惠券ID")
    @Excel(name = "优惠券ID")
    private Long cid;

    /** 优惠券名称 */
    @ApiModelProperty("优惠券名称")
    @Excel(name = "优惠券名称")
    private String cname;

    /** 优惠券领取开启时间 */
    @ApiModelProperty("优惠券领取开启时间")
    @Excel(name = "优惠券领取开启时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date leadStartTime;

    /** 优惠券领取结束时间 */
    @ApiModelProperty("优惠券领取结束时间")
    @Excel(name = "优惠券领取结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date leadEndTime;

    /** 优惠券领取数量 */
    @ApiModelProperty("优惠券领取数量")
    @Excel(name = "优惠券领取数量")
    private Long totalCount;

    /** 优惠券剩余领取数量 */
    @ApiModelProperty("优惠券剩余领取数量")
    @Excel(name = "优惠券剩余领取数量")
    private Long remainCount;

    /** 优惠券剩余已领取数量 */
    @ApiModelProperty("优惠券剩余已领取数量")
    @Excel(name = "优惠券剩已领取数量")
    private Long leadCount;

    @ApiModelProperty("是否限量")
    @Excel(name = "是否限量")
    private String isPermanent;

    /** 1 开启 2 关闭 3 已无效*/
    @ApiModelProperty("状态(1 开启 2 关闭 3 已无效)")
    @Excel(name = "1 开启 2 关闭 3 已无效")
    private String status;

    @ApiModelProperty("开始时间")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTimeDate;

    @ApiModelProperty("结束时间")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTimeDate;

    /** 删除标志（0代表存在 1代表删除） */
    @ApiModelProperty("删除标志（0代表存在 1代表删除）")
    @Excel(name = "删除标志", readConverterExp = "0=代表存在,1=代表删除")
    private String delFlag;

    /** 创建人Id */
    @ApiModelProperty("创建人Id")
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
    public void setLeadStartTime(Date leadStartTime) 
    {
        this.leadStartTime = leadStartTime;
    }

    public Date getLeadStartTime() 
    {
        return leadStartTime;
    }
    public void setLeadEndTime(Date leadEndTime) 
    {
        this.leadEndTime = leadEndTime;
    }

    public Date getLeadEndTime() 
    {
        return leadEndTime;
    }
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
    public void setIsPermanent(String isPermanent)
    {
        this.isPermanent = isPermanent;
    }

    public String getIsPermanent()
    {
        return isPermanent;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
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

    public Long getLeadCount() {
        return leadCount;
    }

    public void setLeadCount(Long leadCount) {
        this.leadCount = leadCount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("cid", getCid())
            .append("cname", getCname())
            .append("leadStartTime", getLeadStartTime())
            .append("leadEndTime", getLeadEndTime())
            .append("totalCount", getTotalCount())
            .append("remainCount", getRemainCount())
            .append("isPermanent", getIsPermanent())
            .append("status", getStatus())
            .append("endTimeDate", getEndTimeDate())
            .append("startTimeDate", getStartTimeDate())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .append("createById", getCreateById())
            .append("leadCount", getLeadCount())
            .toString();
    }
}
