package com.myedu.project.parents.domain.vo;

import com.myedu.project.parents.domain.YunStuLeave;
import com.myedu.project.parents.domain.YunStuScore;

/**
 * Created with IntelliJ IDEA.
 * User: ${梁少鹏}
 * Date: 2019/12/29
 * Time: 13:45
 * Description:学生请假关联实体表
 */
public class YunStuScoreVo extends YunStuScore {

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "YunStuScoreVo{" +
                "courseName='" + courseName + '\'' +
                ", studentName='" + studentName + '\'' +
                '}';
    }

    private String courseName;

    public String getCourseName() {
        return courseName;
    }

    private String studentName;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}