package com.myedu.project.parents.service;

import com.myedu.project.parents.domain.YunStuHwork;
import java.util.List;

/**
 * 学生作业Service接口
 * 
 * @author 梁少鹏
 * @date 2019-12-29
 */
public interface IYunStuHworkService 
{
    /**
     * 查询学生作业
     * 
     * @param id 学生作业ID
     * @return 学生作业
     */
    public YunStuHwork selectYunStuHworkById(Long id);

    /**
     * 查询学生作业列表
     * 
     * @param yunStuHwork 学生作业
     * @return 学生作业集合
     */
    public List<YunStuHwork> selectYunStuHworkList(YunStuHwork yunStuHwork);

    /**
     * 新增学生作业
     * 
     * @param yunStuHwork 学生作业
     * @return 结果
     */
    public int insertYunStuHwork(YunStuHwork yunStuHwork);

    /**
     * 修改学生作业
     * 
     * @param yunStuHwork 学生作业
     * @return 结果
     */
    public int updateYunStuHwork(YunStuHwork yunStuHwork);

    /**
     * 批量删除学生作业
     * 
     * @param ids 需要删除的学生作业ID
     * @return 结果
     */
    public int deleteYunStuHworkByIds(Long[] ids);

    /**
     * 删除学生作业信息
     * 
     * @param id 学生作业ID
     * @return 结果
     */
    public int deleteYunStuHworkById(Long id);
}
