package com.myedu.project.store.domain;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import com.myedu.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Arrays;
import java.util.Date;

/**
 * 门店对象 yun_store
 * 
 * @author 梁少鹏
 * @date 2020-01-04
 */
@ApiModel("门店对象实体")
public class YunStore extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("门店ID")
    private Long id;

    /** 门店名称 */
    @ApiModelProperty("门店名称")
    @Excel(name = "门店名称")
    private String name;

    /** 门店logo */
    @ApiModelProperty("门店logo")
    private String logo;

    /** 门店负责人 */
    @ApiModelProperty("门店负责人")
    @Excel(name = "门店负责人")
    private String manager;

    /** 负责人电话 */
    @ApiModelProperty("负责人电话")
    @Excel(name = "负责人电话")
    private String managerPhone;

    /** 门店照片墙 */
    @ApiModelProperty("门店照片墙")
    private String photos;

    /** 健康证 */
    @ApiModelProperty("健康证")
    private String healths;

    /** 门店宣传视频地址 */
    @ApiModelProperty("门店宣传视频地址")
    private String video;

    /** 门店营业执照 */
    @ApiModelProperty("门店营业执照")
    private String license;

    /** 省 */
    @Excel(name = "省")
    @ApiModelProperty("省")
    private String province;

    /** 市 */
    @ApiModelProperty("市")
    @Excel(name = "市")
    private String city;

    /** 区 */
    @ApiModelProperty("区")
    @Excel(name = "区")
    private String area;

    /** 门店详细地址 */
    @ApiModelProperty("门店详细地址")
    @Excel(name = "门店详细地址")
    private String address;

    /** 门店地图坐标经纬度详情x */
    @ApiModelProperty("门店地图坐标经纬度详情x")
    private Double mapX;

    /** 门店地图坐标经纬度详情y */
    @ApiModelProperty("门店地图坐标经纬度详情y")
    private Double mapY;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 创建人Id */
    @ApiModelProperty("创建人Id")
    private Long createById;

    /** 门店类型组 */
    @ApiModelProperty("门店类型组")
    private Long[] storeTypeIds;

    /** 标签类型组 */
    @ApiModelProperty("门店标签类型组")
    private Long[] storeLabelIds;

    /** 状态（0待审核1审核通过2审核驳回） */
    @ApiModelProperty("门店状态（0待审核1审核通过2审核驳回）")
    @Excel(name = "审核状态", readConverterExp = "0=可用1不可用")
    private String status;

    /** 驳回原因 */
    @ApiModelProperty("驳回原因")
    @Excel(name = "驳回原因")
    private String rejectResion;

    /** 体验开始时间 */
    @ApiModelProperty("vip体验开始时间")
    private Date beginExperienceTime;

    /** 体验结束时间 */
    @ApiModelProperty("vip体验结束时间")
    private Date endExperienceTime;

    /** 门店等级关联id */
    @ApiModelProperty("门店等级关联id")
    private Long vipLevelId;


    public String getRejectResion() {
        return rejectResion;
    }

    public void setRejectResion(String rejectResion) {
        this.rejectResion = rejectResion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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
    public void setLogo(String logo) 
    {
        this.logo = logo;
    }

    public String getLogo() 
    {
        return logo;
    }
    public void setManager(String manager) 
    {
        this.manager = manager;
    }

    public String getManager() 
    {
        return manager;
    }
    public void setManagerPhone(String managerPhone) 
    {
        this.managerPhone = managerPhone;
    }

    public String getManagerPhone() 
    {
        return managerPhone;
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
    public void setLicense(String license) 
    {
        this.license = license;
    }

    public String getLicense() 
    {
        return license;
    }
    public void setProvince(String province) 
    {
        this.province = province;
    }

    public String getProvince() 
    {
        return province;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setArea(String area) 
    {
        this.area = area;
    }

    public String getArea() 
    {
        return area;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setMapX(Double mapX) 
    {
        this.mapX = mapX;
    }

    public Double getMapX() 
    {
        return mapX;
    }

    public String getHealths() {
        return healths;
    }

    public void setHealths(String healths) {
        this.healths = healths;
    }

    public Date getBeginExperienceTime() {
        return beginExperienceTime;
    }

    public void setBeginExperienceTime(Date beginExperienceTime) {
        this.beginExperienceTime = beginExperienceTime;
    }

    public Date getEndExperienceTime() {
        return endExperienceTime;
    }

    public void setEndExperienceTime(Date endExperienceTime) {
        this.endExperienceTime = endExperienceTime;
    }

    public Long getVipLevelId() {
        return vipLevelId;
    }

    public void setVipLevelId(Long vipLevelId) {
        this.vipLevelId = vipLevelId;
    }

    @Override
    public String toString() {
        return "YunStore{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                ", manager='" + manager + '\'' +
                ", managerPhone='" + managerPhone + '\'' +
                ", photos='" + photos + '\'' +
                ", healths='" + healths + '\'' +
                ", video='" + video + '\'' +
                ", license='" + license + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", address='" + address + '\'' +
                ", mapX=" + mapX +
                ", mapY=" + mapY +
                ", delFlag='" + delFlag + '\'' +
                ", createById=" + createById +
                ", storeTypeIds=" + Arrays.toString(storeTypeIds) +
                ", storeLabelIds=" + Arrays.toString(storeLabelIds) +
                ", status='" + status + '\'' +
                ", rejectResion='" + rejectResion + '\'' +
                ", beginExperienceTime='" + beginExperienceTime + '\'' +
                ", endExperienceTime='" + endExperienceTime + '\'' +
                ", vipLevelId='" + vipLevelId + '\'' +
                '}';
    }

    public void setMapY(Double mapY)
    {
        this.mapY = mapY;
    }

    public Long[] getStoreLabelIds() {
        return storeLabelIds;
    }

    public void setStoreLabelIds(Long[] storeLabelIds) {
        this.storeLabelIds = storeLabelIds;
    }

    public Double getMapY()
    {
        return mapY;
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

    public Long[] getStoreTypeIds() {
        return storeTypeIds;
    }

    public void setStoreTypeIds(Long[] storeTypeIds) {
        this.storeTypeIds = storeTypeIds;
    }


}
