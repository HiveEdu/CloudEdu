package com.myedu.project.dynamic.service;

import com.myedu.project.dynamic.domain.YunDyComment;
import java.util.List;

/**
 * 动态评论Service接口
 * 
 * @author 梁少鹏
 * @date 2020-01-01
 */
public interface IYunDyCommentService 
{
    /**
     * 查询动态评论
     * 
     * @param id 动态评论ID
     * @return 动态评论
     */
    public YunDyComment selectYunDyCommentById(Long id);

    /**
     * 查询动态评论列表
     * 
     * @param yunDyComment 动态评论
     * @return 动态评论集合
     */
    public List<YunDyComment> selectYunDyCommentList(YunDyComment yunDyComment);

    /**
     * 新增动态评论
     * 
     * @param yunDyComment 动态评论
     * @return 结果
     */
    public int insertYunDyComment(YunDyComment yunDyComment);

    /**
     * 修改动态评论
     * 
     * @param yunDyComment 动态评论
     * @return 结果
     */
    public int updateYunDyComment(YunDyComment yunDyComment);

    /**
     * 批量删除动态评论
     * 
     * @param ids 需要删除的动态评论ID
     * @return 结果
     */
    public int deleteYunDyCommentByIds(Long[] ids);

    /**
     * 删除动态评论信息
     * 
     * @param id 动态评论ID
     * @return 结果
     */
    public int deleteYunDyCommentById(Long id);
}
