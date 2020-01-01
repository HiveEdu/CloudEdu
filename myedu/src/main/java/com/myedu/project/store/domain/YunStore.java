package com.myedu.project.store.domain;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import com.myedu.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 门店对象 yun_store
 * 
 * @author 梁龙飞
 * @date 2020-01-01
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
    @Excel(name = "门店logo")
    private String logo;

    /** 门店负责人 */
    @Excel(name = "门店负责人")
    private String manager;

    /** 负责人电话 */
    @Excel(name = "负责人电话")
    private String managerPhone;

    /** 门店照片墙 */
    @Excel(name = "门店照片墙")
    private String photos;

    /** 门店宣传视频地址 */
    @Excel(name = "门店宣传视频地址")
    private String video;

    /** 门店营业执照 */
    @Excel(name = "门店营业执照")
    private String license;

    /** 门店详细地址 */
    @Excel(name = "门店详细地址")
    private String address;

    /** 门店地图坐标经纬度详情x */
    @Excel(name = "门店地图坐标经纬度详情x")
    private Double mapX;

    /** 门店地图坐标经纬度详情y */
    @Excel(name = "门店地图坐标经纬度详情y")
    private Double mapY;

    /** 门店类型（1托管班2培训机构3早教机构4家教5幼儿园） */
    @Excel(name = "门店类型", readConverterExp = "1=托管班2培训机构3早教机构4家教5幼儿园")
    private String type;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

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
    public void setMapY(Double mapY) 
    {
        this.mapY = mapY;
    }

    public Double getMapY() 
    {
        return mapY;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("logo", getLogo())
            .append("manager", getManager())
            .append("managerPhone", getManagerPhone())
            .append("photos", getPhotos())
            .append("video", getVideo())
            .append("license", getLicense())
            .append("address", getAddress())
            .append("mapX", getMapX())
            .append("mapY", getMapY())
            .append("type", getType())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .append("createById", getCreateById())
            .toString();
    }
}
