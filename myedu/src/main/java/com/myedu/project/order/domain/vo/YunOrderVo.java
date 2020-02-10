package com.myedu.project.order.domain.vo;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import com.myedu.project.order.domain.YunOrder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: ${梁少鹏}
 * Date: 2020/1/23
 * Time: 10:39
 * Description:
 */
@Data
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

    /** 课程名称 */
    private String name;

    /** 课程类型（0托管班1辅导班2兴趣班） */
    private String classify;

    /** 托管类型（0午托1晚托2全托3早教4临托） */
    private String reclassifyColl;

    /** 是否一对一（0是1否） */
    private String isOneToOne;

    /** 招生人数 */
    private Long stuCount;

    /** 招生对象 */
    private String gradeId;

    /** 开始时间 */
    private Date cStarttime;

    /** 结束时间 */
    private Date cEndtime;

    /** 课程费用 */
    private Double courseCost;

    /** 餐费 */
    private BigDecimal meals;

    /** 课程介绍 */
    private String introduce;

    /** 课程封面 */
    private String picture;

    /** 门店Id */
    private Long storeId;

    /** 上课时间 */
    private String classTime;

    /** 上课时长 */
    private Double classHour;

    /** 总课时 */
    private Long classAll;

    /** 总费用 */
    private BigDecimal totalCost;

    /** 上课周期 */
    private String week;

    /** 课程内容 */
    private String content;

    /** 课程状态 */
    private String courseStatus;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getReclassifyColl() {
        return reclassifyColl;
    }

    public void setReclassifyColl(String reclassifyColl) {
        this.reclassifyColl = reclassifyColl;
    }

    public String getIsOneToOne() {
        return isOneToOne;
    }

    public void setIsOneToOne(String isOneToOne) {
        this.isOneToOne = isOneToOne;
    }

    public Long getStuCount() {
        return stuCount;
    }

    public void setStuCount(Long stuCount) {
        this.stuCount = stuCount;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public Date getcStarttime() {
        return cStarttime;
    }

    public void setcStarttime(Date cStarttime) {
        this.cStarttime = cStarttime;
    }

    public Date getcEndtime() {
        return cEndtime;
    }

    public void setcEndtime(Date cEndtime) {
        this.cEndtime = cEndtime;
    }

    public Double getCourseCost() {
        return courseCost;
    }

    public void setCourseCost(Double courseCost) {
        this.courseCost = courseCost;
    }

    public BigDecimal getMeals() {
        return meals;
    }

    public void setMeals(BigDecimal meals) {
        this.meals = meals;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public Long getStoreId() {
        return storeId;
    }

    @Override
    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public Double getClassHour() {
        return classHour;
    }

    public void setClassHour(Double classHour) {
        this.classHour = classHour;
    }

    public Long getClassAll() {
        return classAll;
    }

    public void setClassAll(Long classAll) {
        this.classAll = classAll;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }
}
