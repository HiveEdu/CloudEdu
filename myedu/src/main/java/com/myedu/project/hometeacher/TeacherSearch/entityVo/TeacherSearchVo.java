package com.myedu.project.hometeacher.TeacherSearch.entityVo;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

/**
 * Created with IntelliJ IDEA.
 * User: ${梁少鹏}
 * Date: 2020/4/11
 * Time: 15:51
 * Description:
 */
@Document(indexName = "teacher", type = "t_teacher", shards = 1, replicas = 0, refreshInterval = "-1")
public class TeacherSearchVo {

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


    /** 教学经历 */
    @ApiModelProperty("教学经历")
    @Excel(name = "教学经历")
    private String experience;

    /** 教学特点 */
    @ApiModelProperty("教学特点")
    @Excel(name = "教学特点")
    private String trait;

    /** 最低费用*/
    @ApiModelProperty("最低费用")
    @Excel(name = "最低费用")
    private Double minfee;

    /** 照片展示 */
    @ApiModelProperty("照片展示")
    @Excel(name = "照片展示")
    private String photos;


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

    /**
     * 到我的距离
     */
    @ApiModelProperty("到我的距离")
    private String distanceMeters;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getTrait() {
        return trait;
    }

    public void setTrait(String trait) {
        this.trait = trait;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getDistanceMeters() {

        return distanceMeters;
    }

    public void setDistanceMeters(String distanceMeters) {
        this.distanceMeters = distanceMeters;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }

    public Double getLon() {
        return lon;
    }

    public Double getMinfee() {
        return minfee;
    }

    @Override
    public String toString() {
        return "TeacherSearchVo{" +
                "id=" + id +
                ", userId=" + userId +
                ", courseId='" + courseId + '\'' +
                ", experience='" + experience + '\'' +
                ", trait='" + trait + '\'' +
                ", minfee=" + minfee +
                ", photos='" + photos + '\'' +
                ", mapX=" + mapX +
                ", mapY=" + mapY +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", address='" + address + '\'' +
                ", status='" + status + '\'' +
                ", distanceMeters='" + distanceMeters + '\'' +
                ", location=" + location +
                ", lon=" + lon +
                ", lat=" + lat +
                '}';
    }

    public void setMinfee(Double minfee) {
        this.minfee = minfee;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }


    public void setLat(Double lat) {
        this.lat = lat;
    }

    @GeoPointField
    private GeoPoint location;

    /**
     * 经度
     */
    @ApiModelProperty("经度")
    private Double lon;

    /**
     * 纬度
     */
    @ApiModelProperty("纬度")
    private Double lat;



}
