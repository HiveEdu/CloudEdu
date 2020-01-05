package com.myedu.project.store.domain;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import com.myedu.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Arrays;

/**
 * 门店对象 yun_store
 * 
 * @author 梁少鹏
 * @date 2020-01-04
 */
public class YunStore extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 门店名称 */
    @Excel(name = "门店名称")
    private String name;

    /** 门店logo */
    private String logo;

    /** 门店负责人 */
    @Excel(name = "门店负责人")
    private String manager;

    /** 负责人电话 */
    @Excel(name = "负责人电话")
    private String managerPhone;

    /** 门店照片墙 */
    private String photos;

    /** 门店宣传视频地址 */
    private String video;

    /** 门店营业执照 */
    private String license;

    /** 省 */
    @Excel(name = "省")
    private String province;

    /** 市 */
    @Excel(name = "市")
    private String city;

    /** 区 */
    @Excel(name = "区")
    private String area;

    /** 门店详细地址 */
    @Excel(name = "门店详细地址")
    private String address;

    /** 门店地图坐标经纬度详情x */
    private Double mapX;

    /** 门店地图坐标经纬度详情y */
    private Double mapY;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 创建人Id */
    private Long createById;

    /** 门店类型组 */
    private Long[] storeTypeIds;

    /** 标签类型组 */
    private Long[] storeLabelIds;

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

    @Override
    public String toString() {
        return "YunStore{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                ", manager='" + manager + '\'' +
                ", managerPhone='" + managerPhone + '\'' +
                ", photos='" + photos + '\'' +
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
