package com.myedu.project.store.service.impl;

import java.util.List;
import com.myedu.common.utils.DateUtils;
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
        yunCourseComment.setCreateTime(DateUtils.getNowDate());
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
