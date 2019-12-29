package com.myedu.project.parents.service;

import com.myedu.project.parents.domain.YunScore;
import java.util.List;

/**
 * 学生成绩Service接口
 * 
 * @author 梁龙飞
 * @date 2019-12-29
 */
public interface IYunScoreService 
{
    /**
     * 查询学生成绩
     * 
     * @param scoreId 学生成绩ID
     * @return 学生成绩
     */
    public YunScore selectYunScoreById(Long scoreId);

    /**
     * 查询学生成绩列表
     * 
     * @param yunScore 学生成绩
     * @return 学生成绩集合
     */
    public List<YunScore> selectYunScoreList(YunScore yunScore);

    /**
     * 新增学生成绩
     * 
     * @param yunScore 学生成绩
     * @return 结果
     */
    public int insertYunScore(YunScore yunScore);

    /**
     * 修改学生成绩
     * 
     * @param yunScore 学生成绩
     * @return 结果
     */
    public int updateYunScore(YunScore yunScore);

    /**
     * 批量删除学生成绩
     * 
     * @param scoreIds 需要删除的学生成绩ID
     * @return 结果
     */
    public int deleteYunScoreByIds(Long[] scoreIds);

    /**
     * 删除学生成绩信息
     * 
     * @param scoreId 学生成绩ID
     * @return 结果
     */
    public int deleteYunScoreById(Long scoreId);
}
