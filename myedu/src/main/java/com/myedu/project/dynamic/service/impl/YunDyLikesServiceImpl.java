package com.myedu.project.dynamic.service.impl;

import java.util.List;
import com.myedu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.dynamic.mapper.YunDyLikesMapper;
import com.myedu.project.dynamic.domain.YunDyLikes;
import com.myedu.project.dynamic.service.IYunDyLikesService;

/**
 * 动态点赞Service业务层处理
 * 
 * @author 梁少鹏
 * @date 2020-01-01
 */
@Service
public class YunDyLikesServiceImpl implements IYunDyLikesService 
{
    @Autowired
    private YunDyLikesMapper yunDyLikesMapper;

    /**
     * 查询动态点赞
     * 
     * @param id 动态点赞ID
     * @return 动态点赞
     */
    @Override
    public YunDyLikes selectYunDyLikesById(Long id)
    {
        return yunDyLikesMapper.selectYunDyLikesById(id);
    }

    /**
     * 查询动态点赞列表
     * 
     * @param yunDyLikes 动态点赞
     * @return 动态点赞
     */
    @Override
    public List<YunDyLikes> selectYunDyLikesList(YunDyLikes yunDyLikes)
    {
        return yunDyLikesMapper.selectYunDyLikesList(yunDyLikes);
    }

    /**
     * 新增动态点赞
     * 
     * @param yunDyLikes 动态点赞
     * @return 结果
     */
    @Override
    public int insertYunDyLikes(YunDyLikes yunDyLikes)
    {
        yunDyLikes.setCreateTime(DateUtils.getNowDate());
        return yunDyLikesMapper.insertYunDyLikes(yunDyLikes);
    }

    /**
     * 修改动态点赞
     * 
     * @param yunDyLikes 动态点赞
     * @return 结果
     */
    @Override
    public int updateYunDyLikes(YunDyLikes yunDyLikes)
    {
        yunDyLikes.setUpdateTime(DateUtils.getNowDate());
        return yunDyLikesMapper.updateYunDyLikes(yunDyLikes);
    }

    /**
     * 批量删除动态点赞
     * 
     * @param ids 需要删除的动态点赞ID
     * @return 结果
     */
    @Override
    public int deleteYunDyLikesByIds(Long[] ids)
    {
        return yunDyLikesMapper.deleteYunDyLikesByIds(ids);
    }

    /**
     * 删除动态点赞信息
     * 
     * @param id 动态点赞ID
     * @return 结果
     */
    @Override
    public int deleteYunDyLikesById(Long id)
    {
        return yunDyLikesMapper.deleteYunDyLikesById(id);
    }
}
