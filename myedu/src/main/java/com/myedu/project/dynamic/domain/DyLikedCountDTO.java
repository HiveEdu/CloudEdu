package com.myedu.project.dynamic.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class DyLikedCountDTO implements Serializable {
    private static final long serialVersionUID = -2856160546081194945L;

    private Long id;

    private Integer count;

    public DyLikedCountDTO() {
    }

    public DyLikedCountDTO(Long id, Integer count) {
        this.id = id;
        this.count = count;
    }
}
