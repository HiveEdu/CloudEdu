package com.myedu.project.thumbsup.domain;

import lombok.Data;

import java.io.Serializable;


import java.io.Serializable;

@Data
public class LikedCountDTO implements Serializable {
    private static final long serialVersionUID = -2856160546081194945L;

    private Long id;

    private Integer count;

    public LikedCountDTO() {
    }

    public LikedCountDTO(Long id, Integer count) {
        this.id = id;
        this.count = count;
    }
}
