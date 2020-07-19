package com.myedu.project.parents.service;

import com.myedu.project.parents.domain.YunStuScore;
import com.myedu.project.parents.domain.vo.YunStuScoreVo;

import java.util.List;

/**
 * 学生成绩Service接口
 * 
 * @author 梁龙飞
 * @date 2019-12-30
 */
public interface IYunStuScoreService 
{
    /**
     * 查询学生成绩
     * 
     * @param scoreId 学生成绩ID
     * @return 学生成绩
     */
    public YunStuScoreVo selectYunStuScoreById(Long scoreId);

    /**
     * 查询学生成绩列表
     * 
     * @param yunStuScore 学生成绩
     * @return 学生成绩集合
     */
    public List<YunStuScoreVo> selectYunStuScoreList(YunStuScoreVo yunStuScore);

    /**
     * 新增学生成绩
     * 
     * @param yunStuScore 学生成绩
     * @return 结果
     */
    public int insertYunStuScore(YunStuScore yunStuScore);

    /**
     * 修改学生成绩
     * 
     * @param yunStuScore 学生成绩
     * @return 结果
     */
    public int updateYunStuScore(YunStuScore yunStuScore);

    /**
     * 批量删除学生成绩
     * 
     * @param scoreIds 需要删除的学生成绩ID
     * @return 结果
     */
    public int deleteYunStuScoreByIds(Long[] scoreIds);

    /**
     * 删除学生成绩信息
     * 
     * @param scoreId 学生成绩ID
     * @return 结果
     */
    public int deleteYunStuScoreById(Long scoreId);
}
