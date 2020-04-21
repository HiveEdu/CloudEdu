package com.myedu.project.store.service;

import com.myedu.project.store.domain.YunStoreClassStu;
import java.util.List;

/**
 * 班级学生管理Service接口
 * 
 * @author LSP
 * @date 2020-04-21
 */
public interface IYunStoreClassStuService 
{
    /**
     * 查询班级学生管理
     * 
     * @param classId 班级学生管理ID
     * @return 班级学生管理
     */
    public YunStoreClassStu selectYunStoreClassStuById(Long classId);

    /**
     * 查询班级学生管理列表
     * 
     * @param yunStoreClassStu 班级学生管理
     * @return 班级学生管理集合
     */
    public List<YunStoreClassStu> selectYunStoreClassStuList(YunStoreClassStu yunStoreClassStu);

    /**
     * 新增班级学生管理
     * 
     * @param yunStoreClassStu 班级学生管理
     * @return 结果
     */
    public int insertYunStoreClassStu(YunStoreClassStu yunStoreClassStu);

    /**
     * 修改班级学生管理
     * 
     * @param yunStoreClassStu 班级学生管理
     * @return 结果
     */
    public int updateYunStoreClassStu(YunStoreClassStu yunStoreClassStu);

    /**
     * 批量删除班级学生管理
     * 
     * @param classIds 需要删除的班级学生管理ID
     * @return 结果
     */
    public int deleteYunStoreClassStuByIds(Long[] classIds);

    /**
     * 删除班级学生管理信息
     * 
     * @param classId 班级学生管理ID
     * @return 结果
     */
    public int deleteYunStoreClassStuById(Long classId);
}
