package com.myedu.project.parents.service;

import com.myedu.project.parents.domain.YunStudent;
import java.util.List;

/**
 * 学生数据Service接口
 * 
 * @author 梁少鹏
 * @date 2019-12-28
 */
public interface IYunStudentService 
{
    /**
     * 查询学生数据
     * 
     * @param id 学生数据ID
     * @return 学生数据
     */
    public YunStudent selectYunStudentById(Long id);

    /**
     * 查询学生数据列表
     * 
     * @param yunStudent 学生数据
     * @return 学生数据集合
     */
    public List<YunStudent> selectYunStudentList(YunStudent yunStudent);

    /**
     * 新增学生数据
     * 
     * @param yunStudent 学生数据
     * @return 结果
     */
    public int insertYunStudent(YunStudent yunStudent);

    /**
     * 修改学生数据
     * 
     * @param yunStudent 学生数据
     * @return 结果
     */
    public int updateYunStudent(YunStudent yunStudent);

    /**
     * 批量删除学生数据
     * 
     * @param ids 需要删除的学生数据ID
     * @return 结果
     */
    public int deleteYunStudentByIds(Long[] ids);

    /**
     * 删除学生数据信息
     * 
     * @param id 学生数据ID
     * @return 结果
     */
    public int deleteYunStudentById(Long id);
}
