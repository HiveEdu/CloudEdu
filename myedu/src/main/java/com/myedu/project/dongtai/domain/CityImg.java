package com.myedu.project.dongtai.domain;

import com.myedu.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;

@ApiModel("照片实体")
public class CityImg  extends BaseEntity {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
