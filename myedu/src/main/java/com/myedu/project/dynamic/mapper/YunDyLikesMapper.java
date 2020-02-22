package com.myedu.project.dynamic.mapper;

import com.myedu.project.dynamic.domain.YunDyLikes;
import java.util.List;

/**
 * 动态点赞Mapper接口
 * 
 * @author 梁少鹏
 * @date 2020-01-01
 */
public interface YunDyLikesMapper 
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
     * 删除动态点赞
     * 
     * @param id 动态点赞ID
     * @return 结果
     */
    public int deleteYunDyLikesById(Long id);

    /**
     * 批量删除动态点赞
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteYunDyLikesByIds(Long[] ids);

    /*
     * @Description :根据动态ID以及用用户id查询
     * @Author : 梁少鹏
     * @Date : 2020/2/22 13:03
     */
    public YunDyLikes getByDyIdAndUserId(Long dyId,Long createById);
}
