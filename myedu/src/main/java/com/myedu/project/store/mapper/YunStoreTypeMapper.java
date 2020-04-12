package com.myedu.project.store.mapper;

import com.myedu.project.store.domain.YunStoreType;
import com.myedu.project.system.domain.SysUserPost;

import java.util.List;

/**
 * 门店类型关联Mapper接口
 * 
 * @author 梁少鹏
 * @date 2020-01-04
 */
public interface YunStoreTypeMapper 
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
     * 删除门店类型关联
     * 
     * @param storeId 门店类型关联ID
     * @return 结果
     */
    public int deleteYunStoreTypeById(Long storeId);

    /**
     * 批量删除门店类型关联
     * 
     * @param storeIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteYunStoreTypeByIds(Long[] storeIds);



    /**
     * 批量新增门店类型关联
     *
     * @param yunStoreTypes 门店类型列表
     * @return 结果
     */
    public int batchStoreType(List<YunStoreType> yunStoreTypes);

    /**
     * 通过门店ID删除门店与门店类型关联
     *
     * @param storeId 门店Id
     * @return 结果
     */
    public int deleteStoreTypeByStoreId(Long storeId);
}
