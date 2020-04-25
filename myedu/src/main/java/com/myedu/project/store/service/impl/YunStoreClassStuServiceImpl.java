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
     * 删除班级学生管理信息
     *
     * @return 结果
     */
    @Override
    public int deleteYunStoreClassStu(Long storeId,Long stuId)
    {
        return yunStoreClassStuMapper.deleteYunStoreClassStu(storeId,stuId);
    }
}
