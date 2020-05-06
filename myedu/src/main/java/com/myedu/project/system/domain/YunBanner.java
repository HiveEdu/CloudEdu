package com.myedu.project.system.domain;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import com.myedu.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 轮廓图设置对象 yun_banner
 * 
 * @author lsp
 * @date 2020-05-06
 */
public class YunBanner extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** H5跳转url */
    @Excel(name = "H5跳转url")
    private String url;

    /** mpvue小程序路由 */
    @Excel(name = "mpvue小程序路由")
    private String myvue;

    /** uniapp路由 */
    @Excel(name = "uniapp路由")
    private String uniapp;

    /** 图片 */
    @Excel(name = "图片")
    private String photo;

    /** 排序 */
    @Excel(name = "排序")
    private Long sort;

    /** 是否显示 */
    @Excel(name = "是否显示")
    private String isshow;

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
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setMyvue(String myvue) 
    {
        this.myvue = myvue;
    }

    public String getMyvue() 
    {
        return myvue;
    }
    public void setUniapp(String uniapp) 
    {
        this.uniapp = uniapp;
    }

    public String getUniapp() 
    {
        return uniapp;
    }
    public void setPhoto(String photo) 
    {
        this.photo = photo;
    }

    public String getPhoto() 
    {
        return photo;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }
    public void setIsshow(String isshow) 
    {
        this.isshow = isshow;
    }

    public String getIsshow() 
    {
        return isshow;
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
            .append("title", getTitle())
            .append("url", getUrl())
            .append("myvue", getMyvue())
            .append("uniapp", getUniapp())
            .append("photo", getPhoto())
            .append("sort", getSort())
            .append("isshow", getIsshow())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .append("createById", getCreateById())
            .toString();
    }
}
