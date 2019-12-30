package com.myedu.project.parents.service.impl;

import java.util.List;
import com.myedu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.parents.mapper.YunScoreMapper;
import com.myedu.project.parents.domain.YunScore;
import com.myedu.project.parents.service.IYunScoreService;

/**
 * scoreService业务层处理
 * 
 * @author 梁龙飞
 * @date 2019-12-30
 */
@Service
public class YunScoreServiceImpl implements IYunScoreService 
{
    @Autowired
    private YunScoreMapper yunScoreMapper;

    /**
     * 查询score
     * 
     * @param scoreId scoreID
     * @return score
     */
    @Override
    public YunScore selectYunScoreById(Long scoreId)
    {
        return yunScoreMapper.selectYunScoreById(scoreId);
    }

    /**
     * 查询score列表
     * 
     * @param yunScore score
     * @return score
     */
    @Override
    public List<YunScore> selectYunScoreList(YunScore yunScore)
    {
        return yunScoreMapper.selectYunScoreList(yunScore);
    }

    /**
     * 新增score
     * 
     * @param yunScore score
     * @return 结果
     */
    @Override
    public int insertYunScore(YunScore yunScore)
    {
        return yunScoreMapper.insertYunScore(yunScore);
    }

    /**
     * 修改score
     * 
     * @param yunScore score
     * @return 结果
     */
    @Override
    public int updateYunScore(YunScore yunScore)
    {
        yunScore.setUpdateTime(DateUtils.getNowDate());
        return yunScoreMapper.updateYunScore(yunScore);
    }

    /**
     * 批量删除score
     * 
     * @param scoreIds 需要删除的scoreID
     * @return 结果
     */
    @Override
    public int deleteYunScoreByIds(Long[] scoreIds)
    {
        return yunScoreMapper.deleteYunScoreByIds(scoreIds);
    }

    /**
     * 删除score信息
     * 
     * @param scoreId scoreID
     * @return 结果
     */
    @Override
    public int deleteYunScoreById(Long scoreId)
    {
        return yunScoreMapper.deleteYunScoreById(scoreId);
    }
}
