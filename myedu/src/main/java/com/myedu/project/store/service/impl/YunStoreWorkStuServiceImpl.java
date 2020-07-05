package com.myedu.project.store.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.store.mapper.YunStoreWorkStuMapper;
import com.myedu.project.store.domain.YunStoreWorkStu;
import com.myedu.project.store.service.IYunStoreWorkStuService;

/**
 * 作业表关联学生Service业务层处理
 * 
 * @author myedu
 * @date 2020-07-05
 */
@Service
public class YunStoreWorkStuServiceImpl implements IYunStoreWorkStuService 
{
    @Autowired
    private YunStoreWorkStuMapper yunStoreWorkStuMapper;

    /**
     * 查询作业表关联学生
     * 
     * @param id 作业表关联学生ID
     * @return 作业表关联学生
     */
    @Override
    public YunStoreWorkStu selectYunStoreWorkStuById(Long id)
    {
        return yunStoreWorkStuMapper.selectYunStoreWorkStuById(id);
    }

    /**
     * 查询作业表关联学生列表
     * 
     * @param yunStoreWorkStu 作业表关联学生
     * @return 作业表关联学生
     */
    @Override
    public List<YunStoreWorkStu> selectYunStoreWorkStuList(YunStoreWorkStu yunStoreWorkStu)
    {
        return yunStoreWorkStuMapper.selectYunStoreWorkStuList(yunStoreWorkStu);
    }

    /**
     * 新增作业表关联学生
     * 
     * @param yunStoreWorkStu 作业表关联学生
     * @return 结果
     */
    @Override
    public int insertYunStoreWorkStu(YunStoreWorkStu yunStoreWorkStu)
    {
        return yunStoreWorkStuMapper.insertYunStoreWorkStu(yunStoreWorkStu);
    }

    /**
     * 修改作业表关联学生
     * 
     * @param yunStoreWorkStu 作业表关联学生
     * @return 结果
     */
    @Override
    public int updateYunStoreWorkStu(YunStoreWorkStu yunStoreWorkStu)
    {
        return yunStoreWorkStuMapper.updateYunStoreWorkStu(yunStoreWorkStu);
    }

    /**
     * 批量删除作业表关联学生
     * 
     * @param ids 需要删除的作业表关联学生ID
     * @return 结果
     */
    @Override
    public int deleteYunStoreWorkStuByIds(Long[] ids)
    {
        return yunStoreWorkStuMapper.deleteYunStoreWorkStuByIds(ids);
    }

    @Override
    public int deleteYunStoreWorkStuByWorkId(Long id)
    {
        return yunStoreWorkStuMapper.deleteYunStoreWorkStuByWorkId(id);
    }

    /**
     * 删除作业表关联学生信息
     * 
     * @param id 作业表关联学生ID
     * @return 结果
     */
    @Override
    public int deleteYunStoreWorkStuById(Long id)
    {
        return yunStoreWorkStuMapper.deleteYunStoreWorkStuById(id);
    }
}
