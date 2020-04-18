package com.myedu.project.store.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class StoreHitsCountDTO implements Serializable {
    private static final long serialVersionUID = -2856160546081194945L;

    private Long id;

    private Integer count;

    public StoreHitsCountDTO() {
    }

    public StoreHitsCountDTO(Long id, Integer count) {
        this.id = id;
        this.count = count;
    }
}
