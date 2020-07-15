package com.myedu.project.store.service;

import com.myedu.project.store.domain.YunStoreCoupon;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * 店铺优惠券Service接口
 * 
 * @author 梁少鹏
 * @date 2020-02-03
 */
@CacheConfig(cacheNames = "yunStoreCoupons")
public interface IYunStoreCouponService 
{
    /**
     * 查询店铺优惠券
     * 
     * @param id 店铺优惠券ID
     * @return 店铺优惠券
     */
    @Cacheable
    public YunStoreCoupon selectYunStoreCouponById(Long id);

    /**
     * 查询店铺优惠券列表
     * 
     * @param yunStoreCoupon 店铺优惠券
     * @return 店铺优惠券集合
     */
    @Cacheable
    public List<YunStoreCoupon> selectYunStoreCouponList(YunStoreCoupon yunStoreCoupon);

    /**
     * 新增店铺优惠券
     * 
     * @param yunStoreCoupon 店铺优惠券
     * @return 结果
     */
    public int insertYunStoreCoupon(YunStoreCoupon yunStoreCoupon);

    /**
     * 修改店铺优惠券
     * 
     * @param yunStoreCoupon 店铺优惠券
     * @return 结果
     */
    public int updateYunStoreCoupon(YunStoreCoupon yunStoreCoupon);

    /**
     * 批量删除店铺优惠券
     * 
     * @param ids 需要删除的店铺优惠券ID
     * @return 结果
     */
    @CacheEvict
    public int deleteYunStoreCouponByIds(Long[] ids);

    /**
     * 删除店铺优惠券信息
     * 
     * @param id 店铺优惠券ID
     * @return 结果
     */
    public int deleteYunStoreCouponById(Long id);
}
