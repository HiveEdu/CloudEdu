package com.myedu.project.store.mapper;

import com.myedu.project.store.domain.YunCourse;
import java.util.List;

/**
 * 课程Mapper接口
 * 
 * @author 梁少鹏
 * @date 2020-01-21
 */
public interface YunCourseMapper 
{
    /**
     * 查询课程
     * 
     * @param id 课程ID
     * @return 课程
     */
    public YunCourse selectYunCourseById(Long id);

    /**
     * 查询课程列表
     * 
     * @param yunCourse 课程
     * @return 课程集合
     */
    public List<YunCourse> selectYunCourseList(YunCourse yunCourse);

    /**
     * 新增课程
     * 
     * @param yunCourse 课程
     * @return 结果
     */
    public int insertYunCourse(YunCourse yunCourse);

    /**
     * 修改课程
     * 
     * @param yunCourse 课程
     * @return 结果
     */
    public int updateYunCourse(YunCourse yunCourse);

    /**
     * 删除课程
     * 
     * @param id 课程ID
     * @return 结果
     */
    public int deleteYunCourseById(Long id);

    /**
     * 批量删除课程
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteYunCourseByIds(Long[] ids);
}
