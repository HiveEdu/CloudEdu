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
 * @author 梁龙飞
 * @date 2020-01-04
 */
@Service
public class YunStoreLabelServiceImpl implements IYunStoreLabelService {


    @Autowired
    YunStoreLabelMapper yunStoreLabelMapper;

    @Override
    public YunStoreLabel selectYunStoreLabelById(Long storeId) {
        return yunStoreLabelMapper.selectYunStoreLabelById(storeId);
    }

    @Override
    public List<YunStoreLabel> selectYunStoreLabelList(YunStoreLabel yunStoreLabel) {
        return yunStoreLabelMapper.selectYunStoreLabelList(yunStoreLabel);
    }

    @Override
    public int insertYunStoreLabel(YunStoreLabel yunStoreLabel) {
        return yunStoreLabelMapper.insertYunStoreLabel(yunStoreLabel);
    }

    @Override
    public int updateYunStoreLabel(YunStoreLabel yunStoreLabel) {
        return yunStoreLabelMapper.updateYunStoreLabel(yunStoreLabel);
    }

    @Override
    public int deleteYunStoreLabelByIds(Long[] storeIds) {
        return yunStoreLabelMapper.deleteYunStoreLabelByIds(storeIds);
    }

    @Override
    public int deleteStoreLabelByStoreId(Long storeId) {
        return yunStoreLabelMapper.deleteYunStoreLabelById(storeId);
    }
}