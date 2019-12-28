package com.myedu.project.dataBasic.mapper;

import com.myedu.project.dataBasic.domain.SysCourse;
import java.util.List;

/**
 * 课程基本数据Mapper接口
 * 
 * @author 梁少鹏
 * @date 2019-12-28
 */
public interface SysCourseMapper 
{
    /**
     * 查询课程基本数据
     * 
     * @param id 课程基本数据ID
     * @return 课程基本数据
     */
    public SysCourse selectSysCourseById(Long id);

    /**
     * 查询课程基本数据列表
     * 
     * @param sysCourse 课程基本数据
     * @return 课程基本数据集合
     */
    public List<SysCourse> selectSysCourseList(SysCourse sysCourse);

    /**
     * 新增课程基本数据
     * 
     * @param sysCourse 课程基本数据
     * @return 结果
     */
    public int insertSysCourse(SysCourse sysCourse);

    /**
     * 修改课程基本数据
     * 
     * @param sysCourse 课程基本数据
     * @return 结果
     */
    public int updateSysCourse(SysCourse sysCourse);

    /**
     * 删除课程基本数据
     * 
     * @param id 课程基本数据ID
     * @return 结果
     */
    public int deleteSysCourseById(Long id);

    /**
     * 批量删除课程基本数据
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysCourseByIds(Long[] ids);
}
