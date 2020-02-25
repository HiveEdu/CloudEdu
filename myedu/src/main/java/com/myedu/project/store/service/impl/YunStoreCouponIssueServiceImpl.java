package com.myedu.project.store.service.impl;

import java.util.List;
import com.myedu.common.utils.DateUtils;
import com.myedu.common.utils.SecurityUtils;
import com.myedu.project.store.domain.vo.YunStoreCouponIssueVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.store.mapper.YunStoreCouponIssueMapper;
import com.myedu.project.store.domain.YunStoreCouponIssue;
import com.myedu.project.store.service.IYunStoreCouponIssueService;

/**
 * 店铺优惠券发布Service业务层处理
 * 
 * @author 梁少鹏
 * @date 2020-02-03
 */
@Service
public class YunStoreCouponIssueServiceImpl implements IYunStoreCouponIssueService 
{
    @Autowired
    private YunStoreCouponIssueMapper yunStoreCouponIssueMapper;

    /**
     * 查询店铺优惠券发布
     * 
     * @param id 店铺优惠券发布ID
     * @return 店铺优惠券发布
     */
    @Override
    public YunStoreCouponIssueVo selectYunStoreCouponIssueById(Long id)
    {
        return yunStoreCouponIssueMapper.selectYunStoreCouponIssueById(id);
    }

    /**
     * 查询店铺优惠券发布列表
     * 
     * @param yunStoreCouponIssue 店铺优惠券发布
     * @return 店铺优惠券发布
     */
    @Override
    public List<YunStoreCouponIssueVo> selectYunStoreCouponIssueList(YunStoreCouponIssueVo yunStoreCouponIssue)
    {
        return yunStoreCouponIssueMapper.selectYunStoreCouponIssueList(yunStoreCouponIssue);
    }

    /**
     * 新增店铺优惠券发布
     * 
     * @param yunStoreCouponIssue 店铺优惠券发布
     * @return 结果
     */
    @Override
    public int insertYunStoreCouponIssue(YunStoreCouponIssue yunStoreCouponIssue)
    {
        yunStoreCouponIssue.setCreateById(SecurityUtils.getUserId());
        yunStoreCouponIssue.setCreateBy(SecurityUtils.getUsername());
        yunStoreCouponIssue.setCreateTime(DateUtils.getNowDate());
        return yunStoreCouponIssueMapper.insertYunStoreCouponIssue(yunStoreCouponIssue);
    }

    /**
     * 修改店铺优惠券发布
     * 
     * @param yunStoreCouponIssue 店铺优惠券发布
     * @return 结果
     */
    @Override
    public int updateYunStoreCouponIssue(YunStoreCouponIssue yunStoreCouponIssue)
    {
        yunStoreCouponIssue.setUpdateTime(DateUtils.getNowDate());
        return yunStoreCouponIssueMapper.updateYunStoreCouponIssue(yunStoreCouponIssue);
    }

    /**
     * 批量删除店铺优惠券发布
     * 
     * @param ids 需要删除的店铺优惠券发布ID
     * @return 结果
     */
    @Override
    public int deleteYunStoreCouponIssueByIds(Integer[] ids)
    {
        return yunStoreCouponIssueMapper.deleteYunStoreCouponIssueByIds(ids);
    }

    /**
     * 删除店铺优惠券发布信息
     * 
     * @param id 店铺优惠券发布ID
     * @return 结果
     */
    @Override
    public int deleteYunStoreCouponIssueById(Integer id)
    {
        return yunStoreCouponIssueMapper.deleteYunStoreCouponIssueById(id);
    }
}
