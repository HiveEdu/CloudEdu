package com.myedu.project.store.domain;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import com.myedu.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 门店日访问量统计对象 yun_store_hits
 * 
 * @author LSP
 * @date 2020-04-18
 */
public class YunStoreHits
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 门店关联ID */
    @Excel(name = "门店关联ID")
    private Long storeId;

    /** 访问量 */
    @Excel(name = "访问量")
    private Long hits;

    /** 点击时间*/
    @Excel(name = "点击时间")
    private String  hitTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setStoreId(Long storeId) 
    {
        this.storeId = storeId;
    }

    public Long getStoreId() 
    {
        return storeId;
    }
    public void setHits(Long hits) 
    {
        this.hits = hits;
    }

    public Long getHits() 
    {
        return hits;
    }

    public String getHitTime() {
        return hitTime;
    }

    public void setHitTime(String hitTime) {
        this.hitTime = hitTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("storeId", getStoreId())
            .append("hits", getHits())
            .append("hitTime", getHitTime())
            .toString();
    }

}
