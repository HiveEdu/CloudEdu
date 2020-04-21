package com.myedu.project.store.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.store.mapper.YunStoreClassStuMapper;
import com.myedu.project.store.domain.YunStoreClassStu;
import com.myedu.project.store.service.IYunStoreClassStuService;

/**
 * 班级学生管理Service业务层处理
 * 
 * @author LSP
 * @date 2020-04-21
 */
@Service
public class YunStoreClassStuServiceImpl implements IYunStoreClassStuService 
{
    @Autowired
    private YunStoreClassStuMapper yunStoreClassStuMapper;

    /**
     * 查询班级学生管理
     * 
     * @param classId 班级学生管理ID
     * @return 班级学生管理
     */
    @Override
    public YunStoreClassStu selectYunStoreClassStuById(Long classId)
    {
        return yunStoreClassStuMapper.selectYunStoreClassStuById(classId);
    }

    /**
     * 查询班级学生管理列表
     * 
     * @param yunStoreClassStu 班级学生管理
     * @return 班级学生管理
     */
    @Override
    public List<YunStoreClassStu> selectYunStoreClassStuList(YunStoreClassStu yunStoreClassStu)
    {
        return yunStoreClassStuMapper.selectYunStoreClassStuList(yunStoreClassStu);
    }

    /**
     * 新增班级学生管理
     * 
     * @param yunStoreClassStu 班级学生管理
     * @return 结果
     */
    @Override
    public int insertYunStoreClassStu(YunStoreClassStu yunStoreClassStu)
    {
        return yunStoreClassStuMapper.insertYunStoreClassStu(yunStoreClassStu);
    }

    /**
     * 修改班级学生管理
     * 
     * @param yunStoreClassStu 班级学生管理
     * @return 结果
     */
    @Override
    public int updateYunStoreClassStu(YunStoreClassStu yunStoreClassStu)
    {
        return yunStoreClassStuMapper.updateYunStoreClassStu(yunStoreClassStu);
    }

    /**
     * 批量删除班级学生管理
     * 
     * @param classIds 需要删除的班级学生管理ID
     * @return 结果
     */
    @Override
    public int deleteYunStoreClassStuByIds(Long[] classIds)
    {
        return yunStoreClassStuMapper.deleteYunStoreClassStuByIds(classIds);
    }

    /**
     * 删除班级学生管理信息
     * 
     * @param classId 班级学生管理ID
     * @return 结果
     */
    @Override
    public int deleteYunStoreClassStuById(Long classId)
    {
        return yunStoreClassStuMapper.deleteYunStoreClassStuById(classId);
    }
}
