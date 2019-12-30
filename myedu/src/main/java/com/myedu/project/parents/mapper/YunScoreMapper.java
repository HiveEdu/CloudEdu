package com.myedu.project.parents.mapper;

import com.myedu.project.parents.domain.YunScore;
import java.util.List;

/**
 * scoreMapper接口
 * 
 * @author 梁龙飞
 * @date 2019-12-30
 */
public interface YunScoreMapper 
{
    /**
     * 查询score
     * 
     * @param scoreId scoreID
     * @return score
     */
    public YunScore selectYunScoreById(Long scoreId);

    /**
     * 查询score列表
     * 
     * @param yunScore score
     * @return score集合
     */
    public List<YunScore> selectYunScoreList(YunScore yunScore);

    /**
     * 新增score
     * 
     * @param yunScore score
     * @return 结果
     */
    public int insertYunScore(YunScore yunScore);

    /**
     * 修改score
     * 
     * @param yunScore score
     * @return 结果
     */
    public int updateYunScore(YunScore yunScore);

    /**
     * 删除score
     * 
     * @param scoreId scoreID
     * @return 结果
     */
    public int deleteYunScoreById(Long scoreId);

    /**
     * 批量删除score
     * 
     * @param scoreIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteYunScoreByIds(Long[] scoreIds);
}
