package com.myedu.project.store.service;

import com.myedu.project.store.domain.YunStoreCouponIssue;
import com.myedu.project.store.domain.vo.YunStoreCouponIssueVo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * 店铺优惠券发布Service接口
 * 
 * @author 梁少鹏
 * @date 2020-02-03
 */
@CacheConfig(cacheNames = "storecoupons")
public interface IYunStoreCouponIssueService 
{
    /**
     * 查询店铺优惠券发布
     * 
     * @param id 店铺优惠券发布ID
     * @return 店铺优惠券发布
     */
    @Cacheable
    public YunStoreCouponIssueVo selectYunStoreCouponIssueById(Long id);

    /**
     * 查询店铺优惠券发布列表
     * 
     * @param yunStoreCouponIssue 店铺优惠券发布
     * @return 店铺优惠券发布集合
     */
    @Cacheable
    public List<YunStoreCouponIssueVo> selectYunStoreCouponIssueList(YunStoreCouponIssueVo yunStoreCouponIssue);

    /**
     * 新增店铺优惠券发布
     * 
     * @param yunStoreCouponIssue 店铺优惠券发布
     * @return 结果
     */
    public int insertYunStoreCouponIssue(YunStoreCouponIssue yunStoreCouponIssue);

    /**
     * 修改店铺优惠券发布
     * 
     * @param yunStoreCouponIssue 店铺优惠券发布
     * @return 结果
     */
    public int updateYunStoreCouponIssue(YunStoreCouponIssue yunStoreCouponIssue);

    /**
     * 批量删除店铺优惠券发布
     * 
     * @param ids 需要删除的店铺优惠券发布ID
     * @return 结果
     */
    @CacheEvict
    public int deleteYunStoreCouponIssueByIds(Integer[] ids);

    /**
     * 删除店铺优惠券发布信息
     * 
     * @param id 店铺优惠券发布ID
     * @return 结果
     */
    public int deleteYunStoreCouponIssueById(Integer id);
}
