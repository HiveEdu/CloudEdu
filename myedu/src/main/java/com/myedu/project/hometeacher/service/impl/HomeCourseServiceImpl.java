package com.myedu.project.hometeacher.service.impl;

import java.util.List;
import com.myedu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.hometeacher.mapper.HomeCourseMapper;
import com.myedu.project.hometeacher.domain.HomeCourse;
import com.myedu.project.hometeacher.service.IHomeCourseService;

/**
 * 课程Service业务层处理
 * 
 * @author 梁龙飞
 * @date 2020-02-12
 */
@Service
public class HomeCourseServiceImpl implements IHomeCourseService 
{
    @Autowired
    private HomeCourseMapper homeCourseMapper;

    /**
     * 查询课程
     * 
     * @param id 课程ID
     * @return 课程
     */
    @Override
    public HomeCourse selectHomeCourseById(Long id)
    {
        return homeCourseMapper.selectHomeCourseById(id);
    }

    /**
     * 查询课程列表
     * 
     * @param homeCourse 课程
     * @return 课程
     */
    @Override
    public List<HomeCourse> selectHomeCourseList(HomeCourse homeCourse)
    {
        return homeCourseMapper.selectHomeCourseList(homeCourse);
    }

    /**
     * 新增课程
     * 
     * @param homeCourse 课程
     * @return 结果
     */
    @Override
    public int insertHomeCourse(HomeCourse homeCourse)
    {
        homeCourse.setCreateTime(DateUtils.getNowDate());
        return homeCourseMapper.insertHomeCourse(homeCourse);
    }

    /**
     * 修改课程
     * 
     * @param homeCourse 课程
     * @return 结果
     */
    @Override
    public int updateHomeCourse(HomeCourse homeCourse)
    {
        homeCourse.setUpdateTime(DateUtils.getNowDate());
        return homeCourseMapper.updateHomeCourse(homeCourse);
    }

    /**
     * 批量删除课程
     * 
     * @param ids 需要删除的课程ID
     * @return 结果
     */
    @Override
    public int deleteHomeCourseByIds(Long[] ids)
    {
        return homeCourseMapper.deleteHomeCourseByIds(ids);
    }

    /**
     * 删除课程信息
     * 
     * @param id 课程ID
     * @return 结果
     */
    @Override
    public int deleteHomeCourseById(Long id)
    {
        return homeCourseMapper.deleteHomeCourseById(id);
    }
}
