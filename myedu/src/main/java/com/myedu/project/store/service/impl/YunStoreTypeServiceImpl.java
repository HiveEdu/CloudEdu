package com.myedu.project.store.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.store.mapper.YunStoreTypeMapper;
import com.myedu.project.store.domain.YunStoreType;
import com.myedu.project.store.service.IYunStoreTypeService;

/**
 * 门店类型关联Service业务层处理
 * 
 * @author 梁少鹏
 * @date 2020-01-04
 */
@Service
public class YunStoreTypeServiceImpl implements IYunStoreTypeService 
{
    @Autowired
    private YunStoreTypeMapper yunStoreTypeMapper;

    /**
     * 查询门店类型关联
     * 
     * @param storeId 门店类型关联ID
     * @return 门店类型关联
     */
    @Override
    public YunStoreType selectYunStoreTypeById(Long storeId)
    {
        return yunStoreTypeMapper.selectYunStoreTypeById(storeId);
    }

    /**
     * 查询门店类型关联列表
     * 
     * @param yunStoreType 门店类型关联
     * @return 门店类型关联
     */
    @Override
    public List<YunStoreType> selectYunStoreTypeList(YunStoreType yunStoreType)
    {
        return yunStoreTypeMapper.selectYunStoreTypeList(yunStoreType);
    }

    /**
     * 新增门店类型关联
     * 
     * @param yunStoreType 门店类型关联
     * @return 结果
     */
    @Override
    public int insertYunStoreType(YunStoreType yunStoreType)
    {
        return yunStoreTypeMapper.insertYunStoreType(yunStoreType);
    }

    /**
     * 修改门店类型关联
     * 
     * @param yunStoreType 门店类型关联
     * @return 结果
     */
    @Override
    public int updateYunStoreType(YunStoreType yunStoreType)
    {
        return yunStoreTypeMapper.updateYunStoreType(yunStoreType);
    }

    /**
     * 批量删除门店类型关联
     * 
     * @param storeIds 需要删除的门店类型关联ID
     * @return 结果
     */
    @Override
    public int deleteYunStoreTypeByIds(Long[] storeIds)
    {
        return yunStoreTypeMapper.deleteYunStoreTypeByIds(storeIds);
    }

    /**
     * 删除门店类型关联信息
     * 
     * @param storeId 门店类型关联ID
     * @return 结果
     */
    @Override
    public int deleteYunStoreTypeById(Long storeId)
    {
        return yunStoreTypeMapper.deleteYunStoreTypeById(storeId);
    }
}
