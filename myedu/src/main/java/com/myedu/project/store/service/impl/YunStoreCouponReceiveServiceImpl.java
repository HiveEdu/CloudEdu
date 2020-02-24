package com.myedu.project.store.service.impl;

import java.util.List;
import com.myedu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.store.mapper.YunStoreCouponReceiveMapper;
import com.myedu.project.store.domain.YunStoreCouponReceive;
import com.myedu.project.store.service.IYunStoreCouponReceiveService;

/**
 * 优惠券领用Service业务层处理
 * 
 * @author 梁少鹏
 * @date 2020-02-24
 */
@Service
public class YunStoreCouponReceiveServiceImpl implements IYunStoreCouponReceiveService 
{
    @Autowired
    private YunStoreCouponReceiveMapper yunStoreCouponReceiveMapper;

    /**
     * 查询优惠券领用
     * 
     * @param id 优惠券领用ID
     * @return 优惠券领用
     */
    @Override
    public YunStoreCouponReceive selectYunStoreCouponReceiveById(Long id)
    {
        return yunStoreCouponReceiveMapper.selectYunStoreCouponReceiveById(id);
    }

    /**
     * 查询优惠券领用列表
     * 
     * @param yunStoreCouponReceive 优惠券领用
     * @return 优惠券领用
     */
    @Override
    public List<YunStoreCouponReceive> selectYunStoreCouponReceiveList(YunStoreCouponReceive yunStoreCouponReceive)
    {
        return yunStoreCouponReceiveMapper.selectYunStoreCouponReceiveList(yunStoreCouponReceive);
    }

    /**
     * 新增优惠券领用
     * 
     * @param yunStoreCouponReceive 优惠券领用
     * @return 结果
     */
    @Override
    public int insertYunStoreCouponReceive(YunStoreCouponReceive yunStoreCouponReceive)
    {
        yunStoreCouponReceive.setCreateTime(DateUtils.getNowDate());
        return yunStoreCouponReceiveMapper.insertYunStoreCouponReceive(yunStoreCouponReceive);
    }

    /**
     * 修改优惠券领用
     * 
     * @param yunStoreCouponReceive 优惠券领用
     * @return 结果
     */
    @Override
    public int updateYunStoreCouponReceive(YunStoreCouponReceive yunStoreCouponReceive)
    {
        return yunStoreCouponReceiveMapper.updateYunStoreCouponReceive(yunStoreCouponReceive);
    }

    /**
     * 批量删除优惠券领用
     * 
     * @param ids 需要删除的优惠券领用ID
     * @return 结果
     */
    @Override
    public int deleteYunStoreCouponReceiveByIds(Long[] ids)
    {
        return yunStoreCouponReceiveMapper.deleteYunStoreCouponReceiveByIds(ids);
    }

    /**
     * 删除优惠券领用信息
     * 
     * @param id 优惠券领用ID
     * @return 结果
     */
    @Override
    public int deleteYunStoreCouponReceiveById(Long id)
    {
        return yunStoreCouponReceiveMapper.deleteYunStoreCouponReceiveById(id);
    }
}
