package com.myedu.project.store.mapper;

import com.myedu.project.store.domain.YunStoreClassStu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 班级学生管理Mapper接口
 * 
 * @author LSP
 * @date 2020-04-21
 */
public interface YunStoreClassStuMapper 
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
     * 删除班级学生管理
     *
     * @return 结果
     */
    public int deleteYunStoreClassStu(@Param("storeId") Long storeId,
                                      @Param("stuId") Long stuId);

    /**
     * 批量删除班级学生管理
     * 
     * @param classIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteYunStoreClassStuByIds(Long[] classIds);
}
