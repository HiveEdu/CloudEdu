package com.myedu.project.store.domain;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import com.myedu.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 课程对象 yun_course
 * 
 * @author 梁少鹏
 * @date 2020-01-21
 */
@ApiModel("门店课程对象实体")
public class YunCourse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("课程ID")
    private Long id;

    /** 课程名称 */
    @ApiModelProperty("课程名称")
    @Excel(name = "课程名称")
    private String name;

    /** 课程类型（0托管班1辅导班2兴趣班） */
    @ApiModelProperty("课程类型（0托管班1辅导班2兴趣班）")
    @Excel(name = "课程类型", readConverterExp = "0=托管班1辅导班2兴趣班")
    private String classify;

    /** 托管类型（0午托1晚托2全托3早教4临托） */
    @ApiModelProperty("托管类型（0午托1晚托2全托3早教4临托）")
    @Excel(name = "托管类型", readConverterExp = "0=午托1晚托2全托3早教4临托")
    private String reclassifyColl;

    /** 是否一对一（0是1否） */
    @ApiModelProperty(" 是否一对一（0是1否）")
    @Excel(name = "是否一对一", readConverterExp = "0=是1否")
    private String isOneToOne;

    /** 招生人数 */
    @ApiModelProperty("招生人数")
    @Excel(name = "招生人数")
    private Long stuCount;

    /** 招生对象 */
    @ApiModelProperty("招生对象")
    @Excel(name = "招生对象")
    private String gradeId;

    /** 开始时间 */
    @ApiModelProperty("开始时间")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cStarttime;

    /** 结束时间 */
    @ApiModelProperty("结束时间")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cEndtime;

    /** 课程费用 */
    @ApiModelProperty("课程费用")
    @Excel(name = "课程费用")
    private Double courseCost;

    /** 餐费 */
    @ApiModelProperty("餐费")
    @Excel(name = "餐费")
    private BigDecimal meals;

    /** 课程介绍 */
    @ApiModelProperty("课程介绍")
    @Excel(name = "课程介绍")
    private String introduce;

    /** 课程封面 */
    @ApiModelProperty("课程封面")
    @Excel(name = "课程封面")
    private String picture;

    /** 门店Id */
    @ApiModelProperty("门店Id")
    @Excel(name = "门店Id")
    private Long storeId;

    /** 上课时间 */
    @ApiModelProperty("上课时间")
    @Excel(name = "上课时间", width = 30)
    private String classTime;

    /** 上课时长 */
    @ApiModelProperty("上课时长")
    @Excel(name = "上课时长")
    private Double classHour;

    /** 总课时 */
    @ApiModelProperty("总课时")
    @Excel(name = "总课时")
    private Long classAll;

    /** 总费用 */
    @ApiModelProperty("总费用")
    @Excel(name = "总费用")
    private BigDecimal totalCost;

    /** 上课周期 */
    @ApiModelProperty("上课周期")
    @Excel(name = "上课周期")
    private String week;

    /** 课程内容 */
    @ApiModelProperty("课程内容")
    @Excel(name = "课程内容")
    private String content;

    /** 课程状态 */
    @ApiModelProperty("课程状态")
    @Excel(name = "课程状态")
    private String status;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 创建人Id */
    @ApiModelProperty("创建人Id")
    @Excel(name = "创建人Id")
    private Long createById;


    /** 课程类型 */
    @ApiModelProperty("课程类型")
    @Excel(name = "课程类型")
    private String courseType;

    /** 家教老师id */
    @ApiModelProperty("家教老师id")
    @Excel(name = "家教老师id")
    private Long teacherId;

    /** 课程照片墙 */
    @ApiModelProperty("课程照片墙")
    @Excel(name = "课程照片墙")
    private String photos;

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
    public void setClassify(String classify) 
    {
        this.classify = classify;
    }

    public String getClassify() 
    {
        return classify;
    }
    public void setReclassifyColl(String reclassifyColl) 
    {
        this.reclassifyColl = reclassifyColl;
    }

    public String getReclassifyColl() 
    {
        return reclassifyColl;
    }
    public void setIsOneToOne(String isOneToOne) 
    {
        this.isOneToOne = isOneToOne;
    }

    public String getIsOneToOne() 
    {
        return isOneToOne;
    }
    public void setStuCount(Long stuCount) 
    {
        this.stuCount = stuCount;
    }

    public Long getStuCount() 
    {
        return stuCount;
    }
    public void setGradeId(String gradeId)
    {
        this.gradeId = gradeId;
    }

    public String getGradeId()
    {
        return gradeId;
    }
    public void setcStarttime(Date cStarttime) 
    {
        this.cStarttime = cStarttime;
    }

    public Date getcStarttime() 
    {
        return cStarttime;
    }
    public void setcEndtime(Date cEndtime) 
    {
        this.cEndtime = cEndtime;
    }

    public Date getcEndtime() 
    {
        return cEndtime;
    }
    public void setCourseCost(Double courseCost) 
    {
        this.courseCost = courseCost;
    }

    public Double getCourseCost() 
    {
        return courseCost;
    }
    public void setMeals(BigDecimal meals)
    {
        this.meals = meals;
    }

    public BigDecimal getMeals()
    {
        return meals;
    }
    public void setIntroduce(String introduce) 
    {
        this.introduce = introduce;
    }

    public String getIntroduce() 
    {
        return introduce;
    }
    public void setPicture(String picture) 
    {
        this.picture = picture;
    }

    public String getPicture() 
    {
        return picture;
    }
    public void setStoreId(Long storeId) 
    {
        this.storeId = storeId;
    }

    public Long getStoreId() 
    {
        return storeId;
    }
    public void setClassTime(String classTime)
    {
        this.classTime = classTime;
    }

    public String getClassTime()
    {
        return classTime;
    }
    public void setClassHour(Double classHour) 
    {
        this.classHour = classHour;
    }

    public Double getClassHour() 
    {
        return classHour;
    }
    public void setClassAll(Long classAll) 
    {
        this.classAll = classAll;
    }

    public Long getClassAll() 
    {
        return classAll;
    }
    public void setTotalCost(BigDecimal totalCost)
    {
        this.totalCost = totalCost;
    }

    public BigDecimal getTotalCost()
    {
        return totalCost;
    }
    public void setWeek(String week)
    {
        this.week = week;
    }

    public String getWeek()
    {
        return week;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
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

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("classify", getClassify())
            .append("reclassifyColl", getReclassifyColl())
            .append("isOneToOne", getIsOneToOne())
            .append("stuCount", getStuCount())
            .append("gradeId", getGradeId())
            .append("cStarttime", getcStarttime())
            .append("cEndtime", getcEndtime())
            .append("courseCost", getCourseCost())
            .append("meals", getMeals())
            .append("introduce", getIntroduce())
            .append("picture", getPicture())
            .append("storeId", getStoreId())
            .append("classTime", getClassTime())
            .append("classHour", getClassHour())
            .append("classAll", getClassAll())
            .append("totalCost", getTotalCost())
            .append("week", getWeek())
            .append("content", getContent())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .append("createById", getCreateById())
            .append("courseType", getCourseType())
            .append("photos", getPhotos())
            .append("teacherId", getTeacherId())
            .toString();
    }
}
