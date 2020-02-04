package com.myedu.project.store.mapper;

import com.myedu.project.store.domain.YunStoreCoupon;
import java.util.List;

/**
 * 店铺优惠券Mapper接口
 * 
 * @author 梁少鹏
 * @date 2020-02-03
 */
public interface YunStoreCouponMapper 
{
    /**
     * 查询店铺优惠券
     * 
     * @param id 店铺优惠券ID
     * @return 店铺优惠券
     */
    public YunStoreCoupon selectYunStoreCouponById(Long id);

    /**
     * 查询店铺优惠券列表
     * 
     * @param yunStoreCoupon 店铺优惠券
     * @return 店铺优惠券集合
     */
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
     * 删除店铺优惠券
     * 
     * @param id 店铺优惠券ID
     * @return 结果
     */
    public int deleteYunStoreCouponById(Long id);

    /**
     * 批量删除店铺优惠券
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteYunStoreCouponByIds(Long[] ids);
}
