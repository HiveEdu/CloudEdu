package com.myedu.project.hometeacher.domain;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import com.myedu.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 家教老师表对象 yun_user_info
 * 
 * @author 梁龙飞
 * @date 2020-02-10
 */
@ApiModel("家教老师实体")
public class YunUserInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 关联用户id */
    @ApiModelProperty("关联用户id")
    @Excel(name = "关联用户id")
    private Long userId;

    /** 授课科目 */
    @ApiModelProperty("授课科目")
    @Excel(name = "授课科目")
    private String courseId;

    /** 奖励荣誉 */
    @ApiModelProperty("奖励荣誉")
    @Excel(name = "奖励荣誉")
    private String awards;

    /** 教学经历 */
    @ApiModelProperty("教学经历")
    @Excel(name = "教学经历")
    private String experience;

    /** 教学特点 */
    @ApiModelProperty("教学特点")
    @Excel(name = "教学特点")
    private String trait;

    /** 照片展示 */
    @ApiModelProperty("照片展示")
    @Excel(name = "照片展示")
    private String photos;

    /** 视频风采 */
    @ApiModelProperty("视频风采")
    @Excel(name = "视频风采")
    private String video;

    /** 学校 */
    @ApiModelProperty("学校")
    @Excel(name = "学校")
    private String school;

    /** 是否毕业（0是1否） */
    @ApiModelProperty("是否毕业")
    @Excel(name = "是否毕业", readConverterExp = "0=是1否")
    private String isGraduate;

    /** 学历*/
    @ApiModelProperty("学历")
    @Excel(name = "学历", readConverterExp = "0=博士1硕士")
    private String education;

    /** 证书 */
    @ApiModelProperty("证书")
    @Excel(name = "证书")
    private String credentials;

    /** 教师地图坐标经纬度详情x */
    @ApiModelProperty("纬度")
    private Double mapX;

    /** 教师地图坐标经纬度详情y */
    @ApiModelProperty("纬度")
    private Double mapY;

    /** 省 */
    @ApiModelProperty("省")
    @Excel(name = "省")
    private String province;

    /** 市 */
    @ApiModelProperty("市")
    @Excel(name = "市")
    private String city;

    /** 区 */
    @ApiModelProperty("区")
    @Excel(name = "区")
    private String area;

    /** 详细地址 */
    @ApiModelProperty("详细地址")
    @Excel(name = "详细地址")
    private String address;

    /** 状态（0待审核1审核通过2审核驳回） */
    @ApiModelProperty("状态")
    @Excel(name = "审核状态", readConverterExp = "0=可用1不可用")
    private String status;

    /** 驳回原因 */
    @ApiModelProperty("驳回原因")
    @Excel(name = "驳回原因")
    private String rejectResion;

    public Double getMapX() {
        return mapX;
    }

    public void setMapX(Double mapX) {
        this.mapX = mapX;
    }

    public Double getMapY() {
        return mapY;
    }

    public void setMapY(Double mapY) {
        this.mapY = mapY;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRejectResion() {
        return rejectResion;
    }

    public void setRejectResion(String rejectResion) {
        this.rejectResion = rejectResion;
    }

    public String getEducation() {
        return education;

    }

    public void setEducation(String education) {
        this.education = education;
    }

    /** 身份证 */
    @ApiModelProperty("身份证")
    @Excel(name = "身份证")
    private String identityCard;

    /** 删除标志（0代表存在 1代表删除） */
    @ApiModelProperty("删除标志")
    private String delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setAwards(String awards) 
    {
        this.awards = awards;
    }

    public String getAwards() 
    {
        return awards;
    }
    public void setExperience(String experience) 
    {
        this.experience = experience;
    }

    public String getExperience() 
    {
        return experience;
    }
    public void setTrait(String trait) 
    {
        this.trait = trait;
    }

    public String getTrait() 
    {
        return trait;
    }
    public void setPhotos(String photos) 
    {
        this.photos = photos;
    }

    public String getPhotos() 
    {
        return photos;
    }
    public void setVideo(String video) 
    {
        this.video = video;
    }

    public String getVideo() 
    {
        return video;
    }
    public void setSchool(String school) 
    {
        this.school = school;
    }

    public String getSchool() 
    {
        return school;
    }
    public void setIsGraduate(String isGraduate) 
    {
        this.isGraduate = isGraduate;
    }

    public String getIsGraduate() 
    {
        return isGraduate;
    }
    public void setCredentials(String credentials) 
    {
        this.credentials = credentials;
    }

    public String getCredentials() 
    {
        return credentials;
    }
    public void setIdentityCard(String identityCard) 
    {
        this.identityCard = identityCard;
    }

    public String getIdentityCard() 
    {
        return identityCard;
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
            .append("userId", getUserId())
            .append("courseId", getCourseId())
            .append("awards", getAwards())
            .append("experience", getExperience())
            .append("trait", getTrait())
            .append("photos", getPhotos())
            .append("video", getVideo())
            .append("school", getSchool())
            .append("isGraduate", getIsGraduate())
            .append("credentials", getCredentials())
            .append("identityCard", getIdentityCard())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
                .append("mapX", getMapX())
                .append("mapY", getMapY())
                .append("province", getProvince())
                .append("city", getCity())
                .append("area", getArea())
                .append("address", getAddress())
                .append("status", getStatus())
                .append("rejectResion", getRejectResion())
            .toString();
    }
}
