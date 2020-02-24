package com.myedu.project.store.mapper;

import com.myedu.project.store.domain.YunStoreCouponIssue;
import com.myedu.project.store.domain.vo.YunStoreCouponIssueVo;

import java.util.List;

/**
 * 店铺优惠券发布Mapper接口
 * 
 * @author 梁少鹏
 * @date 2020-02-03
 */
public interface YunStoreCouponIssueMapper 
{
    /**
     * 查询店铺优惠券发布
     * 
     * @param id 店铺优惠券发布ID
     * @return 店铺优惠券发布
     */
    public YunStoreCouponIssueVo selectYunStoreCouponIssueById(Integer id);

    /**
     * 查询店铺优惠券发布列表
     * 
     * @param yunStoreCouponIssue 店铺优惠券发布
     * @return 店铺优惠券发布集合
     */
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
     * 删除店铺优惠券发布
     * 
     * @param id 店铺优惠券发布ID
     * @return 结果
     */
    public int deleteYunStoreCouponIssueById(Integer id);

    /**
     * 批量删除店铺优惠券发布
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteYunStoreCouponIssueByIds(Integer[] ids);
}
