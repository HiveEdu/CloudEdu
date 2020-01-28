package com.myedu.project.account.mapper;

import com.myedu.project.account.domain.YunAlipayConfig;
import java.util.List;

/**
 * 支付宝配置类Mapper接口
 * 
 * @author 梁少鹏
 * @date 2020-01-27
 */
public interface YunAlipayConfigMapper 
{
    /**
     * 查询支付宝配置类
     * 
     * @param id 支付宝配置类ID
     * @return 支付宝配置类
     */
    public YunAlipayConfig selectYunAlipayConfigById(Long id);

    /**
     * 查询支付宝配置类列表
     * 
     * @param yunAlipayConfig 支付宝配置类
     * @return 支付宝配置类集合
     */
    public List<YunAlipayConfig> selectYunAlipayConfigList(YunAlipayConfig yunAlipayConfig);

    /**
     * 新增支付宝配置类
     * 
     * @param yunAlipayConfig 支付宝配置类
     * @return 结果
     */
    public int insertYunAlipayConfig(YunAlipayConfig yunAlipayConfig);

    /**
     * 修改支付宝配置类
     * 
     * @param yunAlipayConfig 支付宝配置类
     * @return 结果
     */
    public int updateYunAlipayConfig(YunAlipayConfig yunAlipayConfig);

    /**
     * 删除支付宝配置类
     * 
     * @param id 支付宝配置类ID
     * @return 结果
     */
    public int deleteYunAlipayConfigById(Long id);

    /**
     * 批量删除支付宝配置类
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteYunAlipayConfigByIds(Long[] ids);
}
