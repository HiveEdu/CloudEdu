package com.myedu.project.store.service;

import com.myedu.project.store.domain.YunStoreCouponReceive;

import java.util.List;

/**
 * 优惠券领用Service接口
 * 
 * @author 梁少鹏
 * @date 2020-02-24
 */
public interface IYunStoreCouponReceiveService 
{
    /**
     * 查询优惠券领用
     * 
     * @param id 优惠券领用ID
     * @return 优惠券领用
     */
    public YunStoreCouponReceive selectYunStoreCouponReceiveById(Long id);

    /**
     * 查询优惠券领用列表
     * 
     * @param yunStoreCouponReceive 优惠券领用
     * @return 优惠券领用集合
     */
    public List<YunStoreCouponReceive> selectYunStoreCouponReceiveList(YunStoreCouponReceive yunStoreCouponReceive);

    /**
     * 新增优惠券领用
     * 
     * @param yunStoreCouponReceive 优惠券领用
     * @return 结果
     */
    public int insertYunStoreCouponReceive(YunStoreCouponReceive yunStoreCouponReceive);

    /**
     * 修改优惠券领用
     * 
     * @param yunStoreCouponReceive 优惠券领用
     * @return 结果
     */
    public int updateYunStoreCouponReceive(YunStoreCouponReceive yunStoreCouponReceive);

    /**
     * 批量删除优惠券领用
     * 
     * @param ids 需要删除的优惠券领用ID
     * @return 结果
     */
    public int deleteYunStoreCouponReceiveByIds(Long[] ids);

    /**
     * 删除优惠券领用信息
     * 
     * @param id 优惠券领用ID
     * @return 结果
     */
    public int deleteYunStoreCouponReceiveById(Long id);
}
