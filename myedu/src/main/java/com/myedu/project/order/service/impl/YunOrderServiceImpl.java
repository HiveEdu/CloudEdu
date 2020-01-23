package com.myedu.project.order.service.impl;

import java.util.List;
import com.myedu.common.utils.DateUtils;
import com.myedu.common.utils.OrderCodeFactory;
import com.myedu.common.utils.SecurityUtils;
import com.myedu.project.order.domain.vo.YunOrderVo;
import com.myedu.project.order.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.order.mapper.YunOrderMapper;
import com.myedu.project.order.domain.YunOrder;
import com.myedu.project.order.service.IYunOrderService;

/**
 * 订单Service业务层处理
 * 
 * @author 梁少鹏
 * @date 2020-01-22
 */
@Service
public class YunOrderServiceImpl implements IYunOrderService 
{
    @Autowired
    private YunOrderMapper yunOrderMapper;

    /**
     * 查询订单
     * 
     * @param id 订单ID
     * @return 订单
     */
    @Override
    public YunOrder selectYunOrderById(Long id)
    {
        return yunOrderMapper.selectYunOrderById(id);
    }

    /**
     * 查询订单列表
     * 
     * @param yunOrder 订单
     * @return 订单
     */
    @Override
    public List<YunOrderVo> selectYunOrderList(YunOrderVo yunOrder)
    {
        return yunOrderMapper.selectYunOrderList(yunOrder);
    }

    /**
     * 新增订单
     * 
     * @param yunOrder 订单
     * @return 结果
     */
    @Override
    public int insertYunOrder(YunOrder yunOrder)
    {
        yunOrder.setCreateById(SecurityUtils.getUserId());
        yunOrder.setCreateBy(SecurityUtils.getUsername());
        yunOrder.setCreateTime(DateUtils.getNowDate());
        yunOrder.setNum(OrderCodeFactory.getOrderCode(SecurityUtils.getUserId()));
        yunOrder.setStatus(OrderStatus.TOBEPAID.getCode());
        return yunOrderMapper.insertYunOrder(yunOrder);
    }

    /**
     * 修改订单
     * 
     * @param yunOrder 订单
     * @return 结果
     */
    @Override
    public int updateYunOrder(YunOrder yunOrder)
    {
        yunOrder.setUpdateTime(DateUtils.getNowDate());
        return yunOrderMapper.updateYunOrder(yunOrder);
    }

    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的订单ID
     * @return 结果
     */
    @Override
    public int deleteYunOrderByIds(Long[] ids)
    {
        return yunOrderMapper.deleteYunOrderByIds(ids);
    }

    /**
     * 删除订单信息
     * 
     * @param id 订单ID
     * @return 结果
     */
    @Override
    public int deleteYunOrderById(Long id)
    {
        return yunOrderMapper.deleteYunOrderById(id);
    }
}
