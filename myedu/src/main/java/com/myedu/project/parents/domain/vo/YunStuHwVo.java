package com.myedu.project.parents.domain.vo;

import com.myedu.project.parents.domain.YunStuHw;
import io.swagger.annotations.ApiModelProperty;


public class YunStuHwVo extends YunStuHw {
    @ApiModelProperty("学生姓名")
    private String studentName;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
