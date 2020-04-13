package com.myedu.project.store.mapper;

import com.myedu.project.store.domain.YunCourseComment;
import java.util.List;

/**
 * 课程评论Mapper接口
 * 
 * @author 梁龙飞
 * @date 2020-04-13
 */
public interface YunCourseCommentMapper 
{
    /**
     * 查询课程评论
     * 
     * @param id 课程评论ID
     * @return 课程评论
     */
    public YunCourseComment selectYunCourseCommentById(Long id);

    /**
     * 查询课程评论列表
     * 
     * @param yunCourseComment 课程评论
     * @return 课程评论集合
     */
    public List<YunCourseComment> selectYunCourseCommentList(YunCourseComment yunCourseComment);

    /**
     * 新增课程评论
     * 
     * @param yunCourseComment 课程评论
     * @return 结果
     */
    public int insertYunCourseComment(YunCourseComment yunCourseComment);

    /**
     * 修改课程评论
     * 
     * @param yunCourseComment 课程评论
     * @return 结果
     */
    public int updateYunCourseComment(YunCourseComment yunCourseComment);

    /**
     * 删除课程评论
     * 
     * @param id 课程评论ID
     * @return 结果
     */
    public int deleteYunCourseCommentById(Long id);

    /**
     * 批量删除课程评论
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteYunCourseCommentByIds(Long[] ids);
}
