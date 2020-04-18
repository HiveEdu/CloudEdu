package com.myedu.project.store.service.impl;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myedu.common.utils.DateUtils;
import com.myedu.common.utils.SecurityUtils;
import com.myedu.project.store.domain.vo.EvaluationStandard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.store.mapper.YunCourseCommentMapper;
import com.myedu.project.store.domain.YunCourseComment;
import com.myedu.project.store.service.IYunCourseCommentService;

/**
 * 课程评论Service业务层处理
 * 
 * @author 梁龙飞
 * @date 2020-04-13
 */
@Service
public class YunCourseCommentServiceImpl implements IYunCourseCommentService 
{
    @Autowired
    private YunCourseCommentMapper yunCourseCommentMapper;

    /**
     * 查询课程评论
     * 
     * @param id 课程评论ID
     * @return 课程评论
     */
    @Override
    public YunCourseComment selectYunCourseCommentById(Long id)
    {
        return yunCourseCommentMapper.selectYunCourseCommentById(id);
    }

    /**
     * 查询课程评论列表
     * 
     * @param yunCourseComment 课程评论
     * @return 课程评论
     */
    @Override
    public List<YunCourseComment> selectYunCourseCommentList(YunCourseComment yunCourseComment)
    {
        return yunCourseCommentMapper.selectYunCourseCommentList(yunCourseComment);
    }

    /**
     * 新增课程评论
     * 
     * @param yunCourseComment 课程评论
     * @return 结果
     */
    @Override
    public int insertYunCourseComment(YunCourseComment yunCourseComment)
    {
        yunCourseComment.setCreateById(SecurityUtils.getUserId());
        yunCourseComment.setCreateBy(SecurityUtils.getUsername());
        yunCourseComment.setCreateTime(DateUtils.getNowDate());
        yunCourseComment.setType("1");
        //计算平均分
        BigDecimal sum=new BigDecimal(0);
        BigDecimal avg=new BigDecimal(0);
        if(yunCourseComment.getScoreContent()!=null){
            String[] dom=yunCourseComment.getScoreContent().split(",");
            for (int i = 0; i < dom.length; i++) {
                sum=sum.add(new BigDecimal(dom[i].split(":")[1]));

            }
            avg=sum.divide(BigDecimal.valueOf(dom.length),2, RoundingMode.HALF_UP);
        }
        yunCourseComment.setScore(avg);
        return yunCourseCommentMapper.insertYunCourseComment(yunCourseComment);
    }

    /**
     * 修改课程评论
     * 
     * @param yunCourseComment 课程评论
     * @return 结果
     */
    @Override
    public int updateYunCourseComment(YunCourseComment yunCourseComment)
    {
        yunCourseComment.setUpdateTime(DateUtils.getNowDate());
        return yunCourseCommentMapper.updateYunCourseComment(yunCourseComment);
    }

    /**
     * 批量删除课程评论
     * 
     * @param ids 需要删除的课程评论ID
     * @return 结果
     */
    @Override
    public int deleteYunCourseCommentByIds(Long[] ids)
    {
        return yunCourseCommentMapper.deleteYunCourseCommentByIds(ids);
    }

    /**
     * 删除课程评论信息
     * 
     * @param id 课程评论ID
     * @return 结果
     */
    @Override
    public int deleteYunCourseCommentById(Long id)
    {
        return yunCourseCommentMapper.deleteYunCourseCommentById(id);
    }
}
