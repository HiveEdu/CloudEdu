package com.myedu.project.hometeacher.service;

import com.myedu.project.hometeacher.domain.HomeCourse;
import java.util.List;

/**
 * 课程Service接口
 * 
 * @author 梁龙飞
 * @date 2020-02-12
 */
public interface IHomeCourseService 
{
    /**
     * 查询课程
     * 
     * @param id 课程ID
     * @return 课程
     */
    public HomeCourse selectHomeCourseById(Long id);

    /**
     * 查询课程列表
     * 
     * @param homeCourse 课程
     * @return 课程集合
     */
    public List<HomeCourse> selectHomeCourseList(HomeCourse homeCourse);

    /**
     * 新增课程
     * 
     * @param homeCourse 课程
     * @return 结果
     */
    public int insertHomeCourse(HomeCourse homeCourse);

    /**
     * 修改课程
     * 
     * @param homeCourse 课程
     * @return 结果
     */
    public int updateHomeCourse(HomeCourse homeCourse);

    /**
     * 批量删除课程
     * 
     * @param ids 需要删除的课程ID
     * @return 结果
     */
    public int deleteHomeCourseByIds(Long[] ids);

    /**
     * 删除课程信息
     * 
     * @param id 课程ID
     * @return 结果
     */
    public int deleteHomeCourseById(Long id);
}
