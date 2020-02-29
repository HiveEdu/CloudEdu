package com.myedu.project.store.domain.vo;

import com.myedu.project.store.domain.YunStoreSignin;
import io.swagger.annotations.ApiModelProperty;

public class YunStoreSigninVo extends YunStoreSignin {
    @ApiModelProperty("学生姓名")
    private String studentName;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
