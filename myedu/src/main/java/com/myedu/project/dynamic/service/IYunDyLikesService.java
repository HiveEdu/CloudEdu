package com.myedu.project.dynamic.service;

import com.myedu.project.dynamic.domain.YunDyLikes;
import java.util.List;

/**
 * 动态点赞Service接口
 * 
 * @author 梁少鹏
 * @date 2020-01-01
 */
public interface IYunDyLikesService 
{
    /**
     * 查询动态点赞
     * 
     * @param id 动态点赞ID
     * @return 动态点赞
     */
    public YunDyLikes selectYunDyLikesById(Long id);

    /**
     * 查询动态点赞列表
     * 
     * @param yunDyLikes 动态点赞
     * @return 动态点赞集合
     */
    public List<YunDyLikes> selectYunDyLikesList(YunDyLikes yunDyLikes);

    /**
     * 新增动态点赞
     * 
     * @param yunDyLikes 动态点赞
     * @return 结果
     */
    public int insertYunDyLikes(YunDyLikes yunDyLikes);

    /**
     * 修改动态点赞
     * 
     * @param yunDyLikes 动态点赞
     * @return 结果
     */
    public int updateYunDyLikes(YunDyLikes yunDyLikes);

    /**
     * 批量删除动态点赞
     * 
     * @param ids 需要删除的动态点赞ID
     * @return 结果
     */
    public int deleteYunDyLikesByIds(Long[] ids);

    /**
     * 删除动态点赞信息
     * 
     * @param id 动态点赞ID
     * @return 结果
     */
    public int deleteYunDyLikesById(Long id);
}
