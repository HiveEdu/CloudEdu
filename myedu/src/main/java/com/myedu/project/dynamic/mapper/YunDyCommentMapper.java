package com.myedu.project.dynamic.mapper;

import com.myedu.project.dynamic.domain.YunDyComment;
import java.util.List;

/**
 * 动态评论Mapper接口
 * 
 * @author 梁少鹏
 * @date 2020-01-01
 */
public interface YunDyCommentMapper 
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
     * 删除动态评论
     * 
     * @param id 动态评论ID
     * @return 结果
     */
    public int deleteYunDyCommentById(Long id);

    public int deleteYunDyCommentByDyId(Long dyId);
    /**
     * 批量删除动态评论
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteYunDyCommentByIds(Long[] ids);
}
