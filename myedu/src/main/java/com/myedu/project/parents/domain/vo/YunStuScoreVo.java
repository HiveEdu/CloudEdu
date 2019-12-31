package com.myedu.project.parents.domain.vo;

import com.myedu.project.parents.domain.YunStuScore;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created with IntelliJ IDEA.
 * User: ${梁龙飞}
 * Date: 2019/12/29
 * Time: 13:45
 * Description:学生成绩关联实体表
 */
public class YunStuScoreVo extends YunStuScore {

    @ApiModelProperty("学生名称")
    private String courseName;

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getStudentName() {
        return studentName;
    }
    @ApiModelProperty("课程名称")
    private String studentName;

}