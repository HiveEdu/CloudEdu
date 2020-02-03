package com.myedu.project.store.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.store.mapper.StoreCouponIssueMapper;
import com.myedu.project.store.domain.StoreCouponIssue;
import com.myedu.project.store.service.IStoreCouponIssueService;

/**
 * 优惠券制作Service业务层处理
 * 
 * @author 梁龙飞
 * @date 2020-02-02
 */
@Service
public class StoreCouponIssueServiceImpl implements IStoreCouponIssueService 
{
    @Autowired
    private StoreCouponIssueMapper storeCouponIssueMapper;

    /**
     * 查询优惠券制作
     * 
     * @param id 优惠券制作ID
     * @return 优惠券制作
     */
    @Override
    public StoreCouponIssue selectStoreCouponIssueById(Long id)
    {
        return storeCouponIssueMapper.selectStoreCouponIssueById(id);
    }

    /**
     * 查询优惠券制作列表
     * 
     * @param storeCouponIssue 优惠券制作
     * @return 优惠券制作
     */
    @Override
    public List<StoreCouponIssue> selectStoreCouponIssueList(StoreCouponIssue storeCouponIssue)
    {
        return storeCouponIssueMapper.selectStoreCouponIssueList(storeCouponIssue);
    }

    /**
     * 新增优惠券制作
     * 
     * @param storeCouponIssue 优惠券制作
     * @return 结果
     */
    @Override
    public int insertStoreCouponIssue(StoreCouponIssue storeCouponIssue)
    {
        return storeCouponIssueMapper.insertStoreCouponIssue(storeCouponIssue);
    }

    /**
     * 修改优惠券制作
     * 
     * @param storeCouponIssue 优惠券制作
     * @return 结果
     */
    @Override
    public int updateStoreCouponIssue(StoreCouponIssue storeCouponIssue)
    {
        return storeCouponIssueMapper.updateStoreCouponIssue(storeCouponIssue);
    }

    /**
     * 批量删除优惠券制作
     * 
     * @param ids 需要删除的优惠券制作ID
     * @return 结果
     */
    @Override
    public int deleteStoreCouponIssueByIds(Long[] ids)
    {
        return storeCouponIssueMapper.deleteStoreCouponIssueByIds(ids);
    }

    /**
     * 删除优惠券制作信息
     * 
     * @param id 优惠券制作ID
     * @return 结果
     */
    @Override
    public int deleteStoreCouponIssueById(Long id)
    {
        return storeCouponIssueMapper.deleteStoreCouponIssueById(id);
    }
}
