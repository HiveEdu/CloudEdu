package com.myedu.project.store.domain.vo;

import com.myedu.project.store.domain.YunStoreWork;
import io.swagger.annotations.ApiModelProperty;

public class YunStoreWorkVo extends YunStoreWork {
    @ApiModelProperty("门店名称")
    private String storeName;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
