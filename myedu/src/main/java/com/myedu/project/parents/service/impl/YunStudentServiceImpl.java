package com.myedu.project.parents.service.impl;

import java.util.List;
import com.myedu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.parents.mapper.YunStudentMapper;
import com.myedu.project.parents.domain.YunStudent;
import com.myedu.project.parents.service.IYunStudentService;

/**
 * 学生数据Service业务层处理
 * 
 * @author 梁少鹏
 * @date 2019-12-28
 */
@Service
public class YunStudentServiceImpl implements IYunStudentService 
{
    @Autowired
    private YunStudentMapper yunStudentMapper;

    /**
     * 查询学生数据
     * 
     * @param id 学生数据ID
     * @return 学生数据
     */
    @Override
    public YunStudent selectYunStudentById(Long id)
    {
        return yunStudentMapper.selectYunStudentById(id);
    }

    /**
     * 查询学生数据列表
     * 
     * @param yunStudent 学生数据
     * @return 学生数据
     */
    @Override
    public List<YunStudent> selectYunStudentList(YunStudent yunStudent)
    {
        return yunStudentMapper.selectYunStudentList(yunStudent);
    }

    /**
     * 新增学生数据
     * 
     * @param yunStudent 学生数据
     * @return 结果
     */
    @Override
    public int insertYunStudent(YunStudent yunStudent)
    {
        yunStudent.setCreateTime(DateUtils.getNowDate());
        return yunStudentMapper.insertYunStudent(yunStudent);
    }

    /**
     * 修改学生数据
     * 
     * @param yunStudent 学生数据
     * @return 结果
     */
    @Override
    public int updateYunStudent(YunStudent yunStudent)
    {
        yunStudent.setUpdateTime(DateUtils.getNowDate());
        return yunStudentMapper.updateYunStudent(yunStudent);
    }

    /**
     * 批量删除学生数据
     * 
     * @param ids 需要删除的学生数据ID
     * @return 结果
     */
    @Override
    public int deleteYunStudentByIds(Long[] ids)
    {
        return yunStudentMapper.deleteYunStudentByIds(ids);
    }

    /**
     * 删除学生数据信息
     * 
     * @param id 学生数据ID
     * @return 结果
     */
    @Override
    public int deleteYunStudentById(Long id)
    {
        return yunStudentMapper.deleteYunStudentById(id);
    }
}
