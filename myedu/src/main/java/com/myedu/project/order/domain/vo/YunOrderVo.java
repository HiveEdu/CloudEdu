package com.myedu.project.order.domain.vo;

import com.myedu.project.order.domain.YunOrder;

/**
 * Created with IntelliJ IDEA.
 * User: ${梁少鹏}
 * Date: 2020/1/23
 * Time: 10:39
 * Description:
 */
public class YunOrderVo extends YunOrder {

    /*
     * @Description :门店名称
     * @Author : 梁少鹏
     * @Date : 2020/1/23 10:41
     */
    private String storeName;
    /*
     * @Description :课程名称
     * @Date : 2020/1/23 10:41
     */
    private String courseName;
    /*
     * @Description :学生关联姓名
     * @Author : 梁少鹏
     * @Date : 2020/1/23 10:43
     */
    private String studentAssName;
    /*
     * @Description :年级名称
     * @Author : 梁少鹏
     * @Date : 2020/1/23 10:44
     */
    private String gradeName;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStudentAssName() {
        return studentAssName;
    }

    public void setStudentAssName(String studentAssName) {
        this.studentAssName = studentAssName;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }
}