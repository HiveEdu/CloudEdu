package com.myedu.project.parents.domain;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import com.myedu.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 学生数据对象 yun_student
 * 
 * @author 梁少鹏
 * @date 2019-12-28
 */
@ApiModel("学生实体")
public class YunStudent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("学生Id")
    private Long id;

    /** 学生名称 */
    @Excel(name = "学生名称")
    @ApiModelProperty("学生名称")
    private String name;

    /** 学生性别（1：男2：女） */
    @Excel(name = "学生性别", readConverterExp = "1=：男2：女")
    @ApiModelProperty("学生性别")
    private String gendel;

    /** 学生头像地址 */
    @Excel(name = "学生头像地址")
    @ApiModelProperty("学生头像地址")
    private String avatar;

    /** 学生就读所在学校 */
    @Excel(name = "学生就读所在学校")
    @ApiModelProperty("学生就读所在学校")
    private String school;

    /** 学生出生日期 */
    @Excel(name = "学生出生日期")
    @ApiModelProperty("学生出生日期")
    private String birthday;

    /** 紧急联系人 */
    @Excel(name = "紧急联系人")
    @NotBlank(message = "紧急联系人不能为空")
    @ApiModelProperty("紧急联系人")
    private String aidPerson;

    /** 紧急联系人联系方式 */
    @Excel(name = "紧急联系人联系方式")
    @ApiModelProperty("紧急联系人联系方式")
    @NotBlank(message = "手机号码不能为空")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$",message = "手机号码格式有误")
    private String aidPhone;

    /** 学生就读学校区域 */
    @Excel(name = "学生就读学校区域")
    @ApiModelProperty("学生就读学校")
    private String schoolArea;

    /** 关联年级Id */
    @Excel(name = "关联年级Id")
    @ApiModelProperty("关联年级Id")
    private Long gradeId;

    /** 关联家长Id */
    @Excel(name = "关联家长Id")
    @ApiModelProperty("关联家长Id")
    private Long parentId;

    /** 创建人Id */
    @Excel(name = "创建人Id")
    @ApiModelProperty("创建人Id")
    private Long createById;

    /** 删除标志（0代表存在 1代表删除） */
    @ApiModelProperty("删除标志")
    private String delFlag;

    public String getAidPerson() {
        return aidPerson;
    }

    public void setAidPerson(String aidPerson) {
        this.aidPerson = aidPerson;
    }

    public String getAidPhone() {
        return aidPhone;
    }

    public void setAidPhone(String aidPhone) {
        this.aidPhone = aidPhone;
    }

    public String getSchoolArea() {
        return schoolArea;
    }

    public void setSchoolArea(String schoolArea) {
        this.schoolArea = schoolArea;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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
    public void setGendel(String gendel) 
    {
        this.gendel = gendel;
    }

    public String getGendel() 
    {
        return gendel;
    }
    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
    }
    public void setSchool(String school) 
    {
        this.school = school;
    }

    public String getSchool() 
    {
        return school;
    }
    public void setGradeId(Long gradeId) 
    {
        this.gradeId = gradeId;
    }

    public Long getGradeId() 
    {
        return gradeId;
    }
    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
    }
    public void setCreateById(Long createById) 
    {
        this.createById = createById;
    }

    public Long getCreateById() 
    {
        return createById;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("gendel", getGendel())
            .append("avatar", getAvatar())
            .append("school", getSchool())
            .append("gradeId", getGradeId())
            .append("parentId", getParentId())
            .append("createById", getCreateById())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("birthday", getBirthday())
            .append("schoolArea", getSchoolArea())
            .append("aidPerson", getAidPerson())
            .append("aidPhone", getAidPhone())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
