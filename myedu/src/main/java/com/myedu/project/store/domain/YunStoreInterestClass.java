package com.myedu.project.store.domain;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import com.myedu.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
import java.util.Date;

/**
 * 门店兴趣班对象 yun_store_interest_class
 * 
 * @author myedu
 * @date 2020-07-19
 */
@ApiModel("门店兴趣班对象实体")
public class YunStoreInterestClass extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    @ApiModelProperty("主键id")
    private Long id;

    /** 班级图标 */
    @Excel(name = "班级图标")
    @ApiModelProperty("班级图标")
    private String logo;

    /** 辅导内容 */
    @Excel(name = "辅导内容")
    @ApiModelProperty("辅导内容")
    private String content;

    /** 班级名称 */
    @ApiModelProperty("班级名称")
    @Excel(name = "班级名称")
    private String name;

    /** 开课日期 */
    @ApiModelProperty("开课日期")
    @Excel(name = "开课日期", width = 30, dateFormat = "yyyy-MM-dd")
    private String openDate;

    /** 主教老师 */
    @ApiModelProperty("主教老师")
    @Excel(name = "主教老师")
    private String masterTeacher;

    /** 助教老师 */
    @ApiModelProperty("助教老师")
    @Excel(name = "助教老师")
    private String assistantTeacher;

    /** 上课时间 */
    @ApiModelProperty("上课时间")
    @Excel(name = "上课时间", width = 30, dateFormat = "yyyy-MM-dd")
    private String classTime;

    /** 时长 */
    @ApiModelProperty("时长")
    @Excel(name = "时长")
    private Long duration;

    /** 总课时 */
    @ApiModelProperty("总课时")
    @Excel(name = "总课时")
    private Long totalCalssHour;

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    /** 课程说明 */
    @ApiModelProperty("课程说明")
    @Excel(name = "课程说明")
    private String courseDesc;

    /** 课程类型 */
    @ApiModelProperty("课程类型")
    @Excel(name = "课程类型")
    private String classType;

    /** 删除标志 */
    @ApiModelProperty("删除标志")
    private String delFlag;

    /** 创建人id */
    @ApiModelProperty("创建人id")
    @Excel(name = "创建人id")
    private Long createById;

    /** 修改人id */
    @Excel(name = "修改人id")
    private Long updateById;

    /** 上课周期 */
    @ApiModelProperty("上课周期")
    @Excel(name = "上课周期")
    private String week;

    /** 门店id */
    @ApiModelProperty("门店id")
    @Excel(name = "门店id")
    private Long storeId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setLogo(String logo) 
    {
        this.logo = logo;
    }

    public String getLogo() 
    {
        return logo;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }



    public void setMasterTeacher(String masterTeacher) 
    {
        this.masterTeacher = masterTeacher;
    }

    public String getMasterTeacher() 
    {
        return masterTeacher;
    }
    public void setAssistantTeacher(String assistantTeacher) 
    {
        this.assistantTeacher = assistantTeacher;
    }

    public String getAssistantTeacher() 
    {
        return assistantTeacher;
    }



    public void setDuration(Long duration) 
    {
        this.duration = duration;
    }

    public Long getDuration() 
    {
        return duration;
    }
    public void setTotalCalssHour(Long totalCalssHour) 
    {
        this.totalCalssHour = totalCalssHour;
    }

    public Long getTotalCalssHour() 
    {
        return totalCalssHour;
    }
    public void setCourseDesc(String courseDesc) 
    {
        this.courseDesc = courseDesc;
    }

    public String getCourseDesc() 
    {
        return courseDesc;
    }
    public void setClassType(String classType) 
    {
        this.classType = classType;
    }

    public String getClassType() 
    {
        return classType;
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
    public void setUpdateById(Long updateById) 
    {
        this.updateById = updateById;
    }

    public Long getUpdateById() 
    {
        return updateById;
    }
    public void setWeek(String week) 
    {
        this.week = week;
    }

    public String getWeek() 
    {
        return week;
    }
    public void setStoreId(Long storeId) 
    {
        this.storeId = storeId;
    }

    public Long getStoreId() 
    {
        return storeId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("logo", getLogo())
            .append("content", getContent())
            .append("name", getName())
            .append("openDate", getOpenDate())
            .append("masterTeacher", getMasterTeacher())
            .append("assistantTeacher", getAssistantTeacher())
            .append("classTime", getClassTime())
            .append("duration", getDuration())
            .append("totalCalssHour", getTotalCalssHour())
            .append("courseDesc", getCourseDesc())
            .append("classType", getClassType())
            .append("delFlag", getDelFlag())
            .append("createById", getCreateById())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateById", getUpdateById())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("week", getWeek())
            .append("storeId", getStoreId())
            .toString();
    }
}
