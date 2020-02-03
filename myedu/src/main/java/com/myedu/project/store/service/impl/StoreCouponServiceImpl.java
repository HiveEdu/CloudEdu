package com.myedu.project.store.service.impl;

import java.util.List;

import com.myedu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.store.mapper.StoreCouponMapper;
import com.myedu.project.store.domain.StoreCoupon;
import com.myedu.project.store.service.IStoreCouponService;

/**
 * 优惠券制作Service业务层处理
 * 
 * @author 梁龙飞
 * @date 2020-02-03
 */
@Service
public class StoreCouponServiceImpl implements IStoreCouponService 
{
    @Autowired
    private StoreCouponMapper storeCouponMapper;

    /**
     * 查询优惠券制作
     * 
     * @param id 优惠券制作ID
     * @return 优惠券制作
     */
    @Override
    public StoreCoupon selectStoreCouponById(Long id)
    {
        return storeCouponMapper.selectStoreCouponById(id);
    }

    /**
     * 查询优惠券制作列表
     * 
     * @param storeCoupon 优惠券制作
     * @return 优惠券制作
     */
    @Override
    public List<StoreCoupon> selectStoreCouponList(StoreCoupon storeCoupon)
    {
        return storeCouponMapper.selectStoreCouponList(storeCoupon);
    }

    /**
     * 新增优惠券制作
     * 
     * @param storeCoupon 优惠券制作
     * @return 结果
     */
    @Override
    public int insertStoreCoupon(StoreCoupon storeCoupon)
    {   storeCoupon.setAddTime(DateUtils.getNowDate());
        return storeCouponMapper.insertStoreCoupon(storeCoupon);
    }

    /**
     * 修改优惠券制作
     * 
     * @param storeCoupon 优惠券制作
     * @return 结果
     */
    @Override
    public int updateStoreCoupon(StoreCoupon storeCoupon)
    {
        return storeCouponMapper.updateStoreCoupon(storeCoupon);
    }

    /**
     * 批量删除优惠券制作
     * 
     * @param ids 需要删除的优惠券制作ID
     * @return 结果
     */
    @Override
    public int deleteStoreCouponByIds(Long[] ids)
    {
        return storeCouponMapper.deleteStoreCouponByIds(ids);
    }

    /**
     * 删除优惠券制作信息
     * 
     * @param id 优惠券制作ID
     * @return 结果
     */
    @Override
    public int deleteStoreCouponById(Long id)
    {
        return storeCouponMapper.deleteStoreCouponById(id);
    }
}
