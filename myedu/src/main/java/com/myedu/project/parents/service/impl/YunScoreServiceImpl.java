package com.myedu.project.parents.service.impl;

import java.util.List;
import com.myedu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.parents.mapper.YunScoreMapper;
import com.myedu.project.parents.domain.YunScore;
import com.myedu.project.parents.service.IYunScoreService;

/**
 * 学生成绩Service业务层处理
 * 
 * @author 梁龙飞
 * @date 2019-12-29
 */
@Service
public class YunScoreServiceImpl implements IYunScoreService 
{
    @Autowired
    private YunScoreMapper yunScoreMapper;

    /**
     * 查询学生成绩
     * 
     * @param scoreId 学生成绩ID
     * @return 学生成绩
     */
    @Override
    public YunScore selectYunScoreById(Long scoreId)
    {
        return yunScoreMapper.selectYunScoreById(scoreId);
    }

    /**
     * 查询学生成绩列表
     * 
     * @param yunScore 学生成绩
     * @return 学生成绩
     */
    @Override
    public List<YunScore> selectYunScoreList(YunScore yunScore)
    {
        return yunScoreMapper.selectYunScoreList(yunScore);
    }

    /**
     * 新增学生成绩
     * 
     * @param yunScore 学生成绩
     * @return 结果
     */
    @Override
    public int insertYunScore(YunScore yunScore)
    {
        return yunScoreMapper.insertYunScore(yunScore);
    }

    /**
     * 修改学生成绩
     * 
     * @param yunScore 学生成绩
     * @return 结果
     */
    @Override
    public int updateYunScore(YunScore yunScore)
    {
        yunScore.setUpdateTime(DateUtils.getNowDate());
        return yunScoreMapper.updateYunScore(yunScore);
    }

    /**
     * 批量删除学生成绩
     * 
     * @param scoreIds 需要删除的学生成绩ID
     * @return 结果
     */
    @Override
    public int deleteYunScoreByIds(Long[] scoreIds)
    {
        return yunScoreMapper.deleteYunScoreByIds(scoreIds);
    }

    /**
     * 删除学生成绩信息
     * 
     * @param scoreId 学生成绩ID
     * @return 结果
     */
    @Override
    public int deleteYunScoreById(Long scoreId)
    {
        return yunScoreMapper.deleteYunScoreById(scoreId);
    }
}
