package com.myedu.project.store.mapper;
import java.util.List;

import com.myedu.project.store.domain.YunStoreType;

import org.apache.ibatis.annotations.Param;
public interface StoreTypeMapper {

    /**
     * 通过门店ID删除门店和类型关联
     *
     * @param storeId 门店ID
     * @return 结果
     */
    public int deleteStoreTypeByStoreId(Long storeId);

    /**
     * 批量删除门店和类型关联
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStoreType(Long[] ids);

    /**
     * 通过角色ID查询角色使用数量
     *
     * @param typeId 类型ID
     * @return 结果
     */
    public int countStoreTypeByTypeId(Long typeId);

    /**
     * 批量新增门店和类型信息
     *
     * @param storeTypeList 门店类型表
     * @return 结果
     */
    public int batchStoreType(List<YunStoreType> storeTypeList);

    /**
     * 删除门店和类型关联信息
     *
     * @param storeType  门店和类型关联信息
     * @return 结果
     */
    public int deleteStoreTypeInfo(YunStoreType storeType);

    /**
     * 批量取消授权门店类型
     *
     * @param typeId 类型ID
     * @param storeIds 需要删除的门店数据ID
     * @return 结果
     */
    public int deleteStoreTypeInfos(@Param("typeId") Long typeId, @Param("storeIds") Long[] storeIds);
}
