package com.myedu.project.parents.service;

import com.myedu.project.parents.domain.YunStuLeave;
import java.util.List;

/**
 * 学生请假Service接口
 * 
 * @author 梁少鹏
 * @date 2019-12-28
 */
public interface IYunStuLeaveService 
{
    /**
     * 查询学生请假
     * 
     * @param id 学生请假ID
     * @return 学生请假
     */
    public YunStuLeave selectYunStuLeaveById(Long id);

    /**
     * 查询学生请假列表
     * 
     * @param yunStuLeave 学生请假
     * @return 学生请假集合
     */
    public List<YunStuLeave> selectYunStuLeaveList(YunStuLeave yunStuLeave);

    /**
     * 新增学生请假
     * 
     * @param yunStuLeave 学生请假
     * @return 结果
     */
    public int insertYunStuLeave(YunStuLeave yunStuLeave);

    /**
     * 修改学生请假
     * 
     * @param yunStuLeave 学生请假
     * @return 结果
     */
    public int updateYunStuLeave(YunStuLeave yunStuLeave);

    /**
     * 批量删除学生请假
     * 
     * @param ids 需要删除的学生请假ID
     * @return 结果
     */
    public int deleteYunStuLeaveByIds(Long[] ids);

    /**
     * 删除学生请假信息
     * 
     * @param id 学生请假ID
     * @return 结果
     */
    public int deleteYunStuLeaveById(Long id);
}
