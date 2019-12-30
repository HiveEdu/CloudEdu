package com.myedu.project.parents.service.impl;

import java.util.List;
import com.myedu.common.utils.DateUtils;
import com.myedu.project.parents.domain.vo.YunStuScoreVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.parents.mapper.YunStuScoreMapper;
import com.myedu.project.parents.domain.YunStuScore;
import com.myedu.project.parents.service.IYunStuScoreService;

/**
 * 学生成绩Service业务层处理
 * 
 * @author 梁龙飞
 * @date 2019-12-30
 */
@Service
public class YunStuScoreServiceImpl implements IYunStuScoreService 
{
    @Autowired
    private YunStuScoreMapper yunStuScoreMapper;

    /**
     * 查询学生成绩
     * 
     * @param scoreId 学生成绩ID
     * @return 学生成绩
     */
    @Override
    public YunStuScoreVo selectYunStuScoreById(Long scoreId)
    {
        return yunStuScoreMapper.selectYunStuScoreById(scoreId);
    }

    /**
     * 查询学生成绩列表
     * 
     * @param yunStuScore 学生成绩
     * @return 学生成绩
     */
    @Override
    public List<YunStuScoreVo> selectYunStuScoreList(YunStuScoreVo yunStuScore)
    {
        return yunStuScoreMapper.selectYunStuScoreList(yunStuScore);
    }

    /**
     * 新增学生成绩
     * 
     * @param yunStuScore 学生成绩
     * @return 结果
     */
    @Override
    public int insertYunStuScore(YunStuScore yunStuScore)
    {
        yunStuScore.setCreateTime(DateUtils.getNowDate());
        return yunStuScoreMapper.insertYunStuScore(yunStuScore);
    }

    /**
     * 修改学生成绩
     * 
     * @param yunStuScore 学生成绩
     * @return 结果
     */
    @Override
    public int updateYunStuScore(YunStuScore yunStuScore)
    {
        yunStuScore.setUpdateTime(DateUtils.getNowDate());
        return yunStuScoreMapper.updateYunStuScore(yunStuScore);
    }

    /**
     * 批量删除学生成绩
     * 
     * @param scoreIds 需要删除的学生成绩ID
     * @return 结果
     */
    @Override
    public int deleteYunStuScoreByIds(Long[] scoreIds)
    {
        return yunStuScoreMapper.deleteYunStuScoreByIds(scoreIds);
    }

    /**
     * 删除学生成绩信息
     * 
     * @param scoreId 学生成绩ID
     * @return 结果
     */
    @Override
    public int deleteYunStuScoreById(Long scoreId)
    {
        return yunStuScoreMapper.deleteYunStuScoreById(scoreId);
    }
}
