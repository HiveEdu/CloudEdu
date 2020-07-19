package com.myedu.project.store.service;

import com.myedu.project.store.domain.YunStoreType;

import java.util.List;

/**
 * 门店类型关联Service接口
 * 
 * @author 梁少鹏
 * @date 2020-01-04
 */
public interface IYunStoreTypeService 
{
    /**
     * 查询门店类型关联
     * 
     * @param storeId 门店类型关联ID
     * @return 门店类型关联
     */
    public List<YunStoreType> selectYunStoreTypeByStoreId(Long storeId);

    /**
     * 查询门店类型关联列表
     * 
     * @param yunStoreType 门店类型关联
     * @return 门店类型关联集合
     */
    public List<YunStoreType> selectYunStoreTypeList(YunStoreType yunStoreType);

    /**
     * 新增门店类型关联
     * 
     * @param yunStoreType 门店类型关联
     * @return 结果
     */
    public int insertYunStoreType(YunStoreType yunStoreType);

    /**
     * 修改门店类型关联
     * 
     * @param yunStoreType 门店类型关联
     * @return 结果
     */
    public int updateYunStoreType(YunStoreType yunStoreType);

    /**
     * 批量删除门店类型关联
     * 
     * @param storeIds 需要删除的门店类型关联ID
     * @return 结果
     */
    public int deleteYunStoreTypeByIds(Long[] storeIds);

    /**
     * 删除门店类型关联信息
     * 
     * @param storeId 门店类型关联ID
     * @return 结果
     */
    public int deleteYunStoreTypeById(Long storeId);
}
