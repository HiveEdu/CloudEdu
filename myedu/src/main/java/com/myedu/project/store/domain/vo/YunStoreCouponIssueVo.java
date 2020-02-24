package com.myedu.project.store.domain.vo;

import com.myedu.project.store.domain.YunStoreCouponIssue;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: ${梁少鹏}
 * Date: 2020/2/24
 * Time: 21:28
 * Description:
 */
@Data
public class YunStoreCouponIssueVo extends YunStoreCouponIssue {
    //当前用户是否领用
    private String receiveStatus;

    public String getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(String receiveStatus) {
        this.receiveStatus = receiveStatus;
    }
}
