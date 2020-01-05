package com.myedu.project.store.service.impl;

import com.myedu.project.store.domain.YunStoreLabel;
import com.myedu.project.store.mapper.YunStoreLabelMapper;
import com.myedu.project.store.service.IYunStoreLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 门店类型关联Service业务层处理
 * 
 * @author 梁少鹏
 * @date 2020-01-04
 */
@Service
public class YunStoreLabelServiceImpl implements IYunStoreLabelService 
{
    @Autowired
    private YunStoreLabelMapper yunStoreLabelMapper;

    /**
     * 查询门店类型关联
     * 
     * @param storeId 门店类型关联ID
     * @return 门店类型关联
     */
    @Override
    public YunStoreLabel selectYunStoreLabelById(Long storeId)
    {
        return yunStoreLabelMapper.selectYunStoreLabelById(storeId);
    }

    /**
     * 查询门店类型关联列表
     * 
     * @param yunStoreLabel 门店类型关联
     * @return 门店类型关联
     */
    @Override
    public List<YunStoreLabel> selectYunStoreLabelList(YunStoreLabel yunStoreLabel)
    {
        return yunStoreLabelMapper.selectYunStoreLabelList(yunStoreLabel);
    }

    /**
     * 新增门店类型关联
     * 
     * @param yunStoreLabel 门店类型关联
     * @return 结果
     */
    @Override
    public int insertYunStoreLabel(YunStoreLabel yunStoreLabel)
    {
        return yunStoreLabelMapper.insertYunStoreLabel(yunStoreLabel);
    }

    /**
     * 修改门店类型关联
     * 
     * @param yunStoreLabel 门店类型关联
     * @return 结果
     */
    @Override
    public int updateYunStoreLabel(YunStoreLabel yunStoreLabel)
    {
        return yunStoreLabelMapper.updateYunStoreLabel(yunStoreLabel);
    }

    /**
     * 批量删除门店类型关联
     * 
     * @param storeIds 需要删除的门店类型关联ID
     * @return 结果
     */
    @Override
    public int deleteYunStoreLabelByIds(Long[] storeIds)
    {
        return yunStoreLabelMapper.deleteYunStoreLabelByIds(storeIds);
    }

    /**
     * 删除门店类型关联信息
     * 
     * @param storeId 门店类型关联ID
     * @return 结果
     */
    @Override
    public int deleteYunStoreLabelById(Long storeId)
    {
        return yunStoreLabelMapper.deleteYunStoreLabelById(storeId);
    }
}
