package com.myedu.project.dynamic.service;

import com.myedu.project.dynamic.domain.YunDynamic;
import com.myedu.project.dynamic.domain.vo.YunDynamicVo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * 云托管动态管理Service接口
 * 
 * @author 梁少鹏
 * @date 2020-01-01
 */
@CacheConfig(cacheNames = "dynamics")
public interface IYunDynamicService 
{
    /**
     * 查询云托管动态管理
     * 
     * @param id 云托管动态管理ID
     * @return 云托管动态管理
     */
    @Cacheable
    public YunDynamicVo selectYunDynamicById(Long id);

    /**
     * 查询云托管动态管理列表
     * 
     * @param yunDynamic 云托管动态管理
     * @return 云托管动态管理集合
     */
    @Cacheable
    public List<YunDynamicVo> selectYunDynamicList(YunDynamicVo yunDynamic);

    /**
     * 查询动态评论列表
     *
     * @param yunDynamic 动态评论列表
     * @return 动态评论列表
     */
    @Cacheable
    public List<YunDynamicVo> selectYunDyCommentList(YunDynamicVo yunDynamic);


    /**
     * 新增云托管动态管理
     * 
     * @param yunDynamic 云托管动态管理
     * @return 结果
     */
    public int insertYunDynamic(YunDynamic yunDynamic);

    /**
     * 修改云托管动态管理
     * 
     * @param yunDynamic 云托管动态管理
     * @return 结果
     */
    public int updateYunDynamic(YunDynamic yunDynamic);

    /**
     * 批量删除云托管动态管理
     * 
     * @param ids 需要删除的云托管动态管理ID
     * @return 结果
     */
    public int deleteYunDynamicByIds(Long[] ids);

    /**
     * 删除云托管动态管理信息
     * 
     * @param id 云托管动态管理ID
     * @return 结果
     */
    public int deleteYunDynamicById(Long id);


}
