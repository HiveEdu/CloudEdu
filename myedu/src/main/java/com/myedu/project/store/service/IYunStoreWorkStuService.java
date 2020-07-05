package com.myedu.project.store.service;

import com.myedu.project.store.domain.YunStoreWorkStu;
import java.util.List;

/**
 * 作业表关联学生Service接口
 * 
 * @author myedu
 * @date 2020-07-05
 */
public interface IYunStoreWorkStuService 
{
    /**
     * 查询作业表关联学生
     * 
     * @param id 作业表关联学生ID
     * @return 作业表关联学生
     */
    public YunStoreWorkStu selectYunStoreWorkStuById(Long id);

    /**
     * 查询作业表关联学生列表
     * 
     * @param yunStoreWorkStu 作业表关联学生
     * @return 作业表关联学生集合
     */
    public List<YunStoreWorkStu> selectYunStoreWorkStuList(YunStoreWorkStu yunStoreWorkStu);

    /**
     * 新增作业表关联学生
     * 
     * @param yunStoreWorkStu 作业表关联学生
     * @return 结果
     */
    public int insertYunStoreWorkStu(YunStoreWorkStu yunStoreWorkStu);

    /**
     * 修改作业表关联学生
     * 
     * @param yunStoreWorkStu 作业表关联学生
     * @return 结果
     */
    public int updateYunStoreWorkStu(YunStoreWorkStu yunStoreWorkStu);

    /**
     * 批量删除作业表关联学生
     * 
     * @param ids 需要删除的作业表关联学生ID
     * @return 结果
     */
    public int deleteYunStoreWorkStuByIds(Long[] ids);

    /**
     * 删除作业表关联学生信息
     * 
     * @param id 作业表关联学生ID
     * @return 结果
     */
    public int deleteYunStoreWorkStuById(Long id);
    /**
     * 删除门店作业管理信息
     *
     * @param id 门店作业管理ID
     * @return 结果
     */
    public int deleteYunStoreWorkStuByWorkId(Long id);
}
