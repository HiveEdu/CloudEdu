package com.myedu.project.store.domain;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import com.myedu.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 优惠券领用对象 yun_store_coupon_receive
 * 
 * @author 梁少鹏
 * @date 2020-02-24
 */
public class YunStoreCouponReceive extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 已发布优惠券id */
    @Excel(name = "已发布优惠券id")
    private Long sciId;

    /** 0未使用1已使用 */
    @Excel(name = "0未使用1已使用")
    private String status;

    @Excel(name = "领取用户id")
    private Long  createById;
    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSciId(Long sciId) 
    {
        this.sciId = sciId;
    }

    public Long getSciId() 
    {
        return sciId;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public Long getCreateById() {
        return createById;
    }

    public void setCreateById(Long createById) {
        this.createById = createById;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sciId", getSciId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("status", getStatus())
                .append("createById", getCreateById())
            .toString();
    }
}
