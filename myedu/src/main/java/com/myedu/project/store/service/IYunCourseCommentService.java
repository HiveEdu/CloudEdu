package com.myedu.project.store.service;

import com.myedu.project.store.domain.YunCourseComment;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * 课程评论Service接口
 * 
 * @author 梁龙飞
 * @date 2020-04-13
 */
@CacheConfig(cacheNames = "commnets")
public interface IYunCourseCommentService 
{
    /**
     * 查询课程评论
     * 
     * @param id 课程评论ID
     * @return 课程评论
     */
    @Cacheable
    public YunCourseComment selectYunCourseCommentById(Long id);

    /**
     * 查询课程评论列表
     * 
     * @param yunCourseComment 课程评论
     * @return 课程评论集合
     */
    @Cacheable
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
     * 批量删除课程评论
     * 
     * @param ids 需要删除的课程评论ID
     * @return 结果
     */
    @CacheEvict
    public int deleteYunCourseCommentByIds(Long[] ids);

    /**
     * 删除课程评论信息
     * 
     * @param id 课程评论ID
     * @return 结果
     */
    public int deleteYunCourseCommentById(Long id);
}
