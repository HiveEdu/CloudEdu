package com.myedu.project.store.service;

import com.myedu.project.store.domain.YunStoreLabel;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * 门店标签关联Service接口
 * 
 * @author 梁少鹏
 * @date 2020-01-04
 */
@CacheConfig(cacheNames = "yunStoreLabels")
public interface IYunStoreLabelService 
{
    /**
     * 查询门店类型关联
     * 
     * @param storeId 门店类型关联ID
     * @return 门店类型关联
     */
    @Cacheable
    public YunStoreLabel selectYunStoreLabelById(Long storeId);

    /**
     * 查询门店类型关联列表
     * 
     * @param yunStoreLabel 门店类型关联
     * @return 门店类型关联集合
     */
    @Cacheable
    public List<YunStoreLabel> selectYunStoreLabelList(YunStoreLabel yunStoreLabel);

    /**
     * 新增门店类型关联
     * 
     * @param yunStoreLabel 门店类型关联
     * @return 结果
     */
    public int insertYunStoreLabel(YunStoreLabel yunStoreLabel);

    /**
     * 修改门店类型关联
     * 
     * @param yunStoreLabel 门店类型关联
     * @return 结果
     */
    public int updateYunStoreLabel(YunStoreLabel yunStoreLabel);

    /**
     * 批量删除门店类型关联
     * 
     * @param storeIds 需要删除的门店类型关联ID
     * @return 结果
     */
    @CacheEvict
    public int deleteYunStoreLabelByIds(Long[] storeIds);

    /**
     * 删除门店类型关联信息
     * 
     * @param storeId 门店类型关联ID
     * @return 结果
     */
    public int deleteStoreLabelByStoreId(Long storeId);
}
