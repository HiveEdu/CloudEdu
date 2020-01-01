package com.myedu.project.dynamic.service.impl;

import java.util.List;
import com.myedu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.dynamic.mapper.YunDyCommentMapper;
import com.myedu.project.dynamic.domain.YunDyComment;
import com.myedu.project.dynamic.service.IYunDyCommentService;

/**
 * 动态评论Service业务层处理
 * 
 * @author 梁少鹏
 * @date 2020-01-01
 */
@Service
public class YunDyCommentServiceImpl implements IYunDyCommentService 
{
    @Autowired
    private YunDyCommentMapper yunDyCommentMapper;

    /**
     * 查询动态评论
     * 
     * @param id 动态评论ID
     * @return 动态评论
     */
    @Override
    public YunDyComment selectYunDyCommentById(Long id)
    {
        return yunDyCommentMapper.selectYunDyCommentById(id);
    }

    /**
     * 查询动态评论列表
     * 
     * @param yunDyComment 动态评论
     * @return 动态评论
     */
    @Override
    public List<YunDyComment> selectYunDyCommentList(YunDyComment yunDyComment)
    {
        return yunDyCommentMapper.selectYunDyCommentList(yunDyComment);
    }

    /**
     * 新增动态评论
     * 
     * @param yunDyComment 动态评论
     * @return 结果
     */
    @Override
    public int insertYunDyComment(YunDyComment yunDyComment)
    {
        yunDyComment.setCreateTime(DateUtils.getNowDate());
        return yunDyCommentMapper.insertYunDyComment(yunDyComment);
    }

    /**
     * 修改动态评论
     * 
     * @param yunDyComment 动态评论
     * @return 结果
     */
    @Override
    public int updateYunDyComment(YunDyComment yunDyComment)
    {
        yunDyComment.setUpdateTime(DateUtils.getNowDate());
        return yunDyCommentMapper.updateYunDyComment(yunDyComment);
    }

    /**
     * 批量删除动态评论
     * 
     * @param ids 需要删除的动态评论ID
     * @return 结果
     */
    @Override
    public int deleteYunDyCommentByIds(Long[] ids)
    {
        return yunDyCommentMapper.deleteYunDyCommentByIds(ids);
    }

    /**
     * 删除动态评论信息
     * 
     * @param id 动态评论ID
     * @return 结果
     */
    @Override
    public int deleteYunDyCommentById(Long id)
    {
        return yunDyCommentMapper.deleteYunDyCommentById(id);
    }
}
