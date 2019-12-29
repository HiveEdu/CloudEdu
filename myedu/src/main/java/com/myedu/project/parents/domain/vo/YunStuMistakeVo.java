package com.myedu.project.parents.domain.vo;

import com.myedu.project.parents.domain.YunStuMistake;

/**
 * Created with IntelliJ IDEA.
 * User: ${梁少鹏}
 * Date: 2019/12/29
 * Time: 16:51
 * Description:学生错题关联实体
 */
public class YunStuMistakeVo extends YunStuMistake {

    private String studentName;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}