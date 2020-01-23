package com.myedu.project.parents.domain.vo;

import com.myedu.project.parents.domain.YunStuHwork;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created with IntelliJ IDEA.
 * User: ${梁少鹏}
 * Date: 2019/12/30
 * Time: 21:12
 * Description:学生作业关联实体
 */
public class YunStuHworkVo extends YunStuHwork {
    @ApiModelProperty("学生姓名")
    private String studentName;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}