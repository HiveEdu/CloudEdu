package com.myedu.project.store.domain;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import com.myedu.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 班级学生管理对象 yun_store_class_stu
 * 
 * @author LSP
 * @date 2020-04-21
 */
@ApiModel("班级学生管理对象实体")
public class YunStoreClassStu extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 门店Id */
    @ApiModelProperty("门店Id")
    @Excel(name = "门店Id")
    private Long storeId;

    /** 班级id */
    @ApiModelProperty("班级id")
    @Excel(name = "班级id")
    private Long classId;

    /** 学生id */
    @ApiModelProperty("学生id")
    @Excel(name = "学生id")
    private Long stuId;

    public void setClassId(Long classId) 
    {
        this.classId = classId;
    }

    public Long getClassId() 
    {
        return classId;
    }
    public void setStuId(Long stuId) 
    {
        this.stuId = stuId;
    }

    public Long getStuId() 
    {
        return stuId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("classId", getClassId())
            .append("stuId", getStuId())
            .append("storeId", getStoreId())
            .toString();
    }
}
