package com.myedu.project.store.domain;

import com.myedu.framework.web.domain.BaseEntity;

public class YunStoreLabel extends BaseEntity {

    /** 门店id */
    private Long storeId;

    public Long getStoreId() {
        return storeId;
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

    @Override
    public String toString() {
        return "YunStoreLabel{" +
                "storeId=" + storeId +
                ", typeId=" + typeId +
                '}';
    }

    /** 标签类型关联 */
    private Long typeId;
}
