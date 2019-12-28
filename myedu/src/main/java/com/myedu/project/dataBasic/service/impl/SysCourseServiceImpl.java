package com.myedu.project.dataBasic.service.impl;

import java.util.List;
import com.myedu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.dataBasic.mapper.SysCourseMapper;
import com.myedu.project.dataBasic.domain.SysCourse;
import com.myedu.project.dataBasic.service.ISysCourseService;

/**
 * 课程基本数据Service业务层处理
 * 
 * @author 梁少鹏
 * @date 2019-12-28
 */
@Service
public class SysCourseServiceImpl implements ISysCourseService 
{
    @Autowired
    private SysCourseMapper sysCourseMapper;

    /**
     * 查询课程基本数据
     * 
     * @param id 课程基本数据ID
     * @return 课程基本数据
     */
    @Override
    public SysCourse selectSysCourseById(Long id)
    {
        return sysCourseMapper.selectSysCourseById(id);
    }

    /**
     * 查询课程基本数据列表
     * 
     * @param sysCourse 课程基本数据
     * @return 课程基本数据
     */
    @Override
    public List<SysCourse> selectSysCourseList(SysCourse sysCourse)
    {
        return sysCourseMapper.selectSysCourseList(sysCourse);
    }

    /**
     * 新增课程基本数据
     * 
     * @param sysCourse 课程基本数据
     * @return 结果
     */
    @Override
    public int insertSysCourse(SysCourse sysCourse)
    {
        sysCourse.setCreateTime(DateUtils.getNowDate());
        return sysCourseMapper.insertSysCourse(sysCourse);
    }

    /**
     * 修改课程基本数据
     * 
     * @param sysCourse 课程基本数据
     * @return 结果
     */
    @Override
    public int updateSysCourse(SysCourse sysCourse)
    {
        sysCourse.setUpdateTime(DateUtils.getNowDate());
        return sysCourseMapper.updateSysCourse(sysCourse);
    }

    /**
     * 批量删除课程基本数据
     * 
     * @param ids 需要删除的课程基本数据ID
     * @return 结果
     */
    @Override
    public int deleteSysCourseByIds(Long[] ids)
    {
        return sysCourseMapper.deleteSysCourseByIds(ids);
    }

    /**
     * 删除课程基本数据信息
     * 
     * @param id 课程基本数据ID
     * @return 结果
     */
    @Override
    public int deleteSysCourseById(Long id)
    {
        return sysCourseMapper.deleteSysCourseById(id);
    }
}
