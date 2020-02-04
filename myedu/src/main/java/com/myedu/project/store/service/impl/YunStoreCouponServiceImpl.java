package com.myedu.project.store.service.impl;

import java.util.List;
import com.myedu.common.utils.DateUtils;
import com.myedu.common.utils.SecurityUtils;
import com.myedu.project.store.domain.YunStore;
import com.myedu.project.store.mapper.YunStoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.store.mapper.YunStoreCouponMapper;
import com.myedu.project.store.domain.YunStoreCoupon;
import com.myedu.project.store.service.IYunStoreCouponService;

/**
 * 店铺优惠券Service业务层处理
 * 
 * @author 梁少鹏
 * @date 2020-02-03
 */
@Service
public class YunStoreCouponServiceImpl implements IYunStoreCouponService 
{
    @Autowired
    private YunStoreCouponMapper yunStoreCouponMapper;
    @Autowired
    private YunStoreMapper yunStoreMapper;
    /**
     * 查询店铺优惠券
     * 
     * @param id 店铺优惠券ID
     * @return 店铺优惠券
     */
    @Override
    public YunStoreCoupon selectYunStoreCouponById(Long id)
    {
        return yunStoreCouponMapper.selectYunStoreCouponById(id);
    }

    /**
     * 查询店铺优惠券列表
     * 
     * @param yunStoreCoupon 店铺优惠券
     * @return 店铺优惠券
     */
    @Override
    public List<YunStoreCoupon> selectYunStoreCouponList(YunStoreCoupon yunStoreCoupon)
    {
        return yunStoreCouponMapper.selectYunStoreCouponList(yunStoreCoupon);
    }

    /**
     * 新增店铺优惠券
     * 
     * @param yunStoreCoupon 店铺优惠券
     * @return 结果
     */
    @Override
    public int insertYunStoreCoupon(YunStoreCoupon yunStoreCoupon)
    {
        YunStore yunStore=new YunStore();
        yunStore.setCreateById(SecurityUtils.getUserId());
        List<YunStore> yunStores=yunStoreMapper.selectYunStoreList(yunStore);
        Long storeId=null;
        if(!yunStores.isEmpty()){
            storeId=yunStores.get(0).getId();
            yunStoreCoupon.setStoreId(storeId);
        }
        yunStoreCoupon.setCreateById(SecurityUtils.getUserId());
        yunStoreCoupon.setCreateBy(SecurityUtils.getUsername());
        yunStoreCoupon.setCreateTime(DateUtils.getNowDate());
        return yunStoreCouponMapper.insertYunStoreCoupon(yunStoreCoupon);
    }

    /**
     * 修改店铺优惠券
     * 
     * @param yunStoreCoupon 店铺优惠券
     * @return 结果
     */
    @Override
    public int updateYunStoreCoupon(YunStoreCoupon yunStoreCoupon)
    {
        yunStoreCoupon.setUpdateTime(DateUtils.getNowDate());
        return yunStoreCouponMapper.updateYunStoreCoupon(yunStoreCoupon);
    }

    /**
     * 批量删除店铺优惠券
     * 
     * @param ids 需要删除的店铺优惠券ID
     * @return 结果
     */
    @Override
    public int deleteYunStoreCouponByIds(Long[] ids)
    {
        return yunStoreCouponMapper.deleteYunStoreCouponByIds(ids);
    }

    /**
     * 删除店铺优惠券信息
     * 
     * @param id 店铺优惠券ID
     * @return 结果
     */
    @Override
    public int deleteYunStoreCouponById(Long id)
    {
        return yunStoreCouponMapper.deleteYunStoreCouponById(id);
    }
}
