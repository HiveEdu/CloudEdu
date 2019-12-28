package com.myedu.project.thumbsup.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
/**
 * 用户点赞表
 */
@Entity
@Data
public class UserLike {
    //主键id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //被点赞的用户的id
    private String likedUserId;

    //点赞的用户的id
    private String likedPostId;

    //点赞的状态.默认未点赞
    private Integer status = LikedStatusEnum.UNLIKE.getCode();

    public UserLike() {
    }

    public UserLike(String likedUserId, String likedPostId, Integer status) {
        this.likedUserId = likedUserId;
        this.likedPostId = likedPostId;
        this.status = status;
    }
}
