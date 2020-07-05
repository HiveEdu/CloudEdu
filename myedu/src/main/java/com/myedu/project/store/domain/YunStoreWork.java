package com.myedu.project.store.domain;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import com.myedu.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 门店作业管理对象 yun_store_work
 * 
 * @author lianglongfei
 * @date 2020-07-05
 */
public class YunStoreWork extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 作业名称 */
    @Excel(name = "作业名称")
    private String workName;

    /** 作业标题 */
    @Excel(name = "作业标题")
    private String workTitle;

    /** 作业内容 */
    @Excel(name = "作业内容")
    private String workContent;

    /** 作业照片 */
    @Excel(name = "作业照片")
    private String workPicture;

    /** 门店id */
    @Excel(name = "门店id")
    private Long storeId;

    /** 作业状态 */
    @Excel(name = "作业状态")
    private String workStatus;

    /** 发布人id */
    private Long createById;

    /** 修改用户id */
    private Long updateById;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setWorkName(String workName) 
    {
        this.workName = workName;
    }

    public String getWorkName() 
    {
        return workName;
    }
    public void setWorkTitle(String workTitle) 
    {
        this.workTitle = workTitle;
    }

    public String getWorkTitle() 
    {
        return workTitle;
    }
    public void setWorkContent(String workContent) 
    {
        this.workContent = workContent;
    }

    public String getWorkContent() 
    {
        return workContent;
    }
    public void setWorkPicture(String workPicture) 
    {
        this.workPicture = workPicture;
    }

    public String getWorkPicture() 
    {
        return workPicture;
    }
    public void setStoreId(Long storeId) 
    {
        this.storeId = storeId;
    }

    public Long getStoreId() 
    {
        return storeId;
    }
    public void setWorkStatus(String workStatus) 
    {
        this.workStatus = workStatus;
    }

    public String getWorkStatus() 
    {
        return workStatus;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("workName", getWorkName())
            .append("workTitle", getWorkTitle())
            .append("workContent", getWorkContent())
            .append("workPicture", getWorkPicture())
            .append("storeId", getStoreId())
            .append("workStatus", getWorkStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("createById", getCreateById())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("updateById", getUpdateById())
            .toString();
    }
}
