package com.myedu.project.dynamic.domain.vo;

import com.myedu.project.dynamic.domain.YunDynamic;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: ${梁少鹏}
 * Date: 2020/2/22
 * Time: 19:18
 * Description:
 */
@Data
public class YunDynamicVo extends YunDynamic {

    private String status;

    private String likeUserId;

    private String nickName;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLikeUserId() {
        return likeUserId;
    }

    public void setLikeUserId(String likeUserId) {
        this.likeUserId = likeUserId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}

