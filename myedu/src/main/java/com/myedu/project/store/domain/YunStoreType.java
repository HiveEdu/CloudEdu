package com.myedu.project.store.domain;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import com.myedu.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 门店类型关联对象 yun_store_type
 * 
 * @author 梁少鹏
 * @date 2020-01-04
 */
public class YunStoreType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 门店id */
    private Long storeId;

    /** 门店类型关联 */
    private Long typeId;

    public void setStoreId(Long storeId) 
    {
        this.storeId = storeId;
    }

    public Long getStoreId() 
    {
        return storeId;
    }
    public void setTypeId(Long typeId) 
    {
        this.typeId = typeId;
    }

    public Long getTypeId() 
    {
        return typeId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("storeId", getStoreId())
            .append("typeId", getTypeId())
            .toString();
    }
}
