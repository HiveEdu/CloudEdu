package com.myedu.project.store.activity.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.myedu.framework.aspectj.lang.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;

import com.myedu.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.Min;

/**
 * 门店砍价活动对象 yun_store_bargain
 * 
 * @author 梁龙飞
 * @date 2020-03-14
 */
public class YunStoreBargain extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id主键 */
    private Long id;

    /** 课程id */
    @Excel(name = "课程id")
    private Long courseId;

    /** 砍价活动名称 */
    @Excel(name = "砍价活动名称")
    private String title;

    /** 砍价活动图片 */
    @Excel(name = "砍价活动图片")
    private String image;

    /** 单位名称 */
    @Excel(name = "单位名称")
    private String unitName;

    /** 库存 */
    @Excel(name = "库存")
    private Long stock;

    /** 销量必填 */
    @Excel(name = "销量必填")
    private Long sales;

    /** 砍价产品轮播图 */
    @Excel(name = "砍价产品轮播图")
    private String images;

    /** 砍价开启时间 */
    @Excel(name = "砍价开启时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 砍价结束时间 */
    @Excel(name = "砍价结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date stopTime;

    /** 开始时间 */
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTimeDate;

    /** 结束时间 */
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTimeDate;

    /** 砍价产品名称 */
    @Excel(name = "砍价产品名称")
    private String storeName;

    /** 砍价金额 */
    @Excel(name = "砍价金额")
    private BigDecimal price;

    /** 砍价商品最低价 */
    @Excel(name = "砍价商品最低价")
    private BigDecimal minPrice;

    /** 每次购买的砍价产品数量 */
    @Excel(name = "每次购买的砍价产品数量")
    @Min(value = 0,message = "砍价商品最低价必须大于等于0")
    private Long num;

    /** 用户每次砍价的最大金额 */
    @Excel(name = "用户每次砍价的最大金额")
    private BigDecimal bargainMaxPrice;

    /** 用户每次砍价的最小金额 */
    @Excel(name = "用户每次砍价的最小金额")
    @Min(value = 0,message = "砍价商品最低价必须大于等于0")
    private BigDecimal bargainMinPrice;

    /** 用户每次砍价的次数 */
    @Excel(name = "用户每次砍价的次数")
    @Min(value = 0,message = "砍价商品最低价必须大于等于0")
    private Long bargainNum;

    /** 砍价状态  */
    @Excel(name = "砍价状态 ")
    private String status;

    /** 砍价状态 */
    @Excel(name = "砍价状态 ")
    private String description;

    /** 返多少积分 */
    @Excel(name = "返多少积分")
    private BigDecimal giveIntegral;

    /** 砍价活动简介 */
    @Excel(name = "砍价活动简介")
    private String info;

    /** 成本价 */
    @Excel(name = "成本价")
    private BigDecimal cost;

    /** 排序 */
    @Excel(name = "排序")
    private Long sort;

    /** 是否推荐0不推荐1推荐 */
    @Excel(name = "是否推荐0不推荐1推荐")
    private Long isHot;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 是否推荐0不推荐1推荐 */
    @Excel(name = "是否推荐0不推荐1推荐")
    private String rule;

    /** 砍价产品浏览量 */
    @Excel(name = "砍价产品浏览量")
    private Long look;

    /** 砍价产品分享量 */
    @Excel(name = "砍价产品分享量")
    private Long share;

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
    public void setCourseId(Long courseId) 
    {
        this.courseId = courseId;
    }

    public Long getCourseId() 
    {
        return courseId;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setImage(String image)
    {
        this.image = image;
    }

    public String getImage()
    {
        return image;
    }
    public void setUnitName(String unitName) 
    {
        this.unitName = unitName;
    }

    public String getUnitName() 
    {
        return unitName;
    }
    public void setStock(Long stock) 
    {
        this.stock = stock;
    }

    public Long getStock() 
    {
        return stock;
    }
    public void setSales(Long sales) 
    {
        this.sales = sales;
    }

    public Long getSales() 
    {
        return sales;
    }
    public void setImages(String images)
    {
        this.images = images;
    }

    public String getImages()
    {
        return images;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setStopTime(Date stopTime) 
    {
        this.stopTime = stopTime;
    }

    public Date getStopTime() 
    {
        return stopTime;
    }
    public void setStartTimeDate(Date startTimeDate) 
    {
        this.startTimeDate = startTimeDate;
    }

    public Date getStartTimeDate() 
    {
        return startTimeDate;
    }
    public void setEndTimeDate(Date endTimeDate) 
    {
        this.endTimeDate = endTimeDate;
    }

    public Date getEndTimeDate() 
    {
        return endTimeDate;
    }
    public void setStoreName(String storeName) 
    {
        this.storeName = storeName;
    }

    public String getStoreName() 
    {
        return storeName;
    }


    public void setNum(Long num) 
    {
        this.num = num;
    }

    public Long getNum() 
    {
        return num;
    }


    public void setBargainNum(Long bargainNum) 
    {
        this.bargainNum = bargainNum;
    }

    public Long getBargainNum() 
    {
        return bargainNum;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }

    public void setInfo(String info) 
    {
        this.info = info;
    }

    public String getInfo() 
    {
        return info;
    }

    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }
    public void setIsHot(Long isHot) 
    {
        this.isHot = isHot;
    }

    public Long getIsHot() 
    {
        return isHot;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setRule(String rule) 
    {
        this.rule = rule;
    }

    public String getRule() 
    {
        return rule;
    }
    public void setLook(Long look) 
    {
        this.look = look;
    }

    public Long getLook() 
    {
        return look;
    }
    public void setShare(Long share) 
    {
        this.share = share;
    }

    public Long getShare() 
    {
        return share;
    }
    public void setCreateById(Long createById) 
    {
        this.createById = createById;
    }

    public Long getCreateById() 
    {
        return createById;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getBargainMaxPrice() {
        return bargainMaxPrice;
    }

    public void setBargainMaxPrice(BigDecimal bargainMaxPrice) {
        this.bargainMaxPrice = bargainMaxPrice;
    }

    public BigDecimal getBargainMinPrice() {
        return bargainMinPrice;
    }

    public void setBargainMinPrice(BigDecimal bargainMinPrice) {
        this.bargainMinPrice = bargainMinPrice;
    }

    public BigDecimal getGiveIntegral() {
        return giveIntegral;
    }

    public void setGiveIntegral(BigDecimal giveIntegral) {
        this.giveIntegral = giveIntegral;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("courseId", getCourseId())
            .append("title", getTitle())
            .append("image", getImage())
            .append("unitName", getUnitName())
            .append("stock", getStock())
            .append("sales", getSales())
            .append("images", getImages())
            .append("startTime", getStartTime())
            .append("stopTime", getStopTime())
            .append("startTimeDate", getStartTimeDate())
            .append("endTimeDate", getEndTimeDate())
            .append("storeName", getStoreName())
            .append("price", getPrice())
            .append("minPrice", getMinPrice())
            .append("num", getNum())
            .append("bargainMaxPrice", getBargainMaxPrice())
            .append("bargainMinPrice", getBargainMinPrice())
            .append("bargainNum", getBargainNum())
            .append("status", getStatus())
            .append("description", getDescription())
            .append("giveIntegral", getGiveIntegral())
            .append("info", getInfo())
            .append("cost", getCost())
            .append("sort", getSort())
            .append("isHot", getIsHot())
            .append("delFlag", getDelFlag())
            .append("createTime", getCreateTime())
            .append("rule", getRule())
            .append("look", getLook())
            .append("share", getShare())
            .append("createById", getCreateById())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
    public void copy(YunStoreBargain source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
