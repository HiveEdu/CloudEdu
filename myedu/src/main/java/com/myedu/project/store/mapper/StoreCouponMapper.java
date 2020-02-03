package com.myedu.project.store.mapper;

import com.myedu.project.store.domain.StoreCoupon;
import java.util.List;

/**
 * 优惠券制作Mapper接口
 * 
 * @author 梁龙飞
 * @date 2020-02-03
 */
public interface StoreCouponMapper 
{
    /**
     * 查询优惠券制作
     * 
     * @param id 优惠券制作ID
     * @return 优惠券制作
     */
    public StoreCoupon selectStoreCouponById(Long id);

    /**
     * 查询优惠券制作列表
     * 
     * @param storeCoupon 优惠券制作
     * @return 优惠券制作集合
     */
    public List<StoreCoupon> selectStoreCouponList(StoreCoupon storeCoupon);

    /**
     * 新增优惠券制作
     * 
     * @param storeCoupon 优惠券制作
     * @return 结果
     */
    public int insertStoreCoupon(StoreCoupon storeCoupon);

    /**
     * 修改优惠券制作
     * 
     * @param storeCoupon 优惠券制作
     * @return 结果
     */
    public int updateStoreCoupon(StoreCoupon storeCoupon);

    /**
     * 删除优惠券制作
     * 
     * @param id 优惠券制作ID
     * @return 结果
     */
    public int deleteStoreCouponById(Long id);

    /**
     * 批量删除优惠券制作
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStoreCouponByIds(Long[] ids);
}
