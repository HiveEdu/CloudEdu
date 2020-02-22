package com.myedu.project.dynamic.service;

import com.myedu.project.dynamic.domain.DyLikedCountDTO;
import com.myedu.project.dynamic.domain.YunDyLikes;
import com.myedu.project.thumbsup.domain.LikedCountDTO;
import com.myedu.project.thumbsup.domain.UserLike;

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



    /**
     * 点赞。状态为1
     * @param likedUserId
     * @param likedDynamicId
     */
    void saveLiked2Redis(Long likedDynamicId, Long likedUserId);

    /**
     * 取消点赞。将状态改变为0
     * @param likedUserId
     * @param likedDynamicId
     */
    void unlikeFromRedis(Long likedDynamicId, Long likedUserId);

    /**
     * 从Redis中删除一条点赞数据
     * @param likedUserId
     * @param likedDynamicId
     */
    void deleteLikedFromRedis(Long likedDynamicId, Long likedUserId);

    /**
     * 该动态的点赞数加1
     * @param likedDynamicId
     */
    void incrementLikedCount(Long likedDynamicId);

    /**
     * 该动态的点赞数减1
     * @param likedDynamicId
     */
    void decrementLikedCount(Long likedDynamicId);

    /**
     * 获取Redis中存储的所有动态点赞数据
     * @return
     */
    List<YunDyLikes> getLikedDataFromRedis();

    /**
     * 获取Redis中存储的所有点赞数量
     * @return
     */
    List<DyLikedCountDTO> getLikedCountFromRedis();

    /**
     * 将Redis里的点赞数据存入数据库中
     */
    void transLikedFromRedis2DB();

    /**
     * 将Redis中的点赞数量数据存入数据库
     */
    void transLikedCountFromRedis2DB();
}
