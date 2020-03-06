package com.myedu.project.store.domain.vo;

import com.myedu.project.store.domain.YunStoreSignin;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class YunStoreSigninVo extends YunStoreSignin {


    @ApiModelProperty("学生姓名")
    private String studentName;
    @ApiModelProperty("门店名称")
    private String storeName;
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
