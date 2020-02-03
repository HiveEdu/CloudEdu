package com.myedu.project.store.mapper;

import com.myedu.project.store.domain.StoreCouponIssue;
import java.util.List;

/**
 * 优惠券制作Mapper接口
 * 
 * @author 梁龙飞
 * @date 2020-02-02
 */
public interface StoreCouponIssueMapper 
{
    /**
     * 查询优惠券制作
     * 
     * @param id 优惠券制作ID
     * @return 优惠券制作
     */
    public StoreCouponIssue selectStoreCouponIssueById(Long id);

    /**
     * 查询优惠券制作列表
     * 
     * @param storeCouponIssue 优惠券制作
     * @return 优惠券制作集合
     */
    public List<StoreCouponIssue> selectStoreCouponIssueList(StoreCouponIssue storeCouponIssue);

    /**
     * 新增优惠券制作
     * 
     * @param storeCouponIssue 优惠券制作
     * @return 结果
     */
    public int insertStoreCouponIssue(StoreCouponIssue storeCouponIssue);

    /**
     * 修改优惠券制作
     * 
     * @param storeCouponIssue 优惠券制作
     * @return 结果
     */
    public int updateStoreCouponIssue(StoreCouponIssue storeCouponIssue);

    /**
     * 删除优惠券制作
     * 
     * @param id 优惠券制作ID
     * @return 结果
     */
    public int deleteStoreCouponIssueById(Long id);

    /**
     * 批量删除优惠券制作
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStoreCouponIssueByIds(Long[] ids);
}
