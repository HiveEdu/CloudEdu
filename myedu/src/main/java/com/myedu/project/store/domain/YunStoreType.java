package com.myedu.project.store.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class YunStoreType {
    /** 门店ID */
    private Long storeId;

    public Long getStoreId() {
        return storeId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("storeId", getStoreId())
                .append("typeId", getTypeId())
                .toString();
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    /** ，类型ID */
    private Long typeId;
}
