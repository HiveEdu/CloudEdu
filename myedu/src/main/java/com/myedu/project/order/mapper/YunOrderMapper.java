package com.myedu.project.order.mapper;

import com.myedu.project.order.domain.YunOrder;
import java.util.List;

/**
 * 订单Mapper接口
 * 
 * @author 梁少鹏
 * @date 2020-01-22
 */
public interface YunOrderMapper 
{
    /**
     * 查询订单
     * 
     * @param id 订单ID
     * @return 订单
     */
    public YunOrder selectYunOrderById(Long id);

    /**
     * 查询订单列表
     * 
     * @param yunOrder 订单
     * @return 订单集合
     */
    public List<YunOrder> selectYunOrderList(YunOrder yunOrder);

    /**
     * 新增订单
     * 
     * @param yunOrder 订单
     * @return 结果
     */
    public int insertYunOrder(YunOrder yunOrder);

    /**
     * 修改订单
     * 
     * @param yunOrder 订单
     * @return 结果
     */
    public int updateYunOrder(YunOrder yunOrder);

    /**
     * 删除订单
     * 
     * @param id 订单ID
     * @return 结果
     */
    public int deleteYunOrderById(Long id);

    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteYunOrderByIds(Long[] ids);
}
