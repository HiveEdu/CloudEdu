package com.myedu.project.account.service;

import com.myedu.project.account.domain.YunAlipayConfig;
import java.util.List;

/**
 * 支付宝配置类Service接口
 * 
 * @author 梁少鹏
 * @date 2020-01-27
 */
public interface IYunAlipayConfigService 
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
     * 批量删除支付宝配置类
     * 
     * @param ids 需要删除的支付宝配置类ID
     * @return 结果
     */
    public int deleteYunAlipayConfigByIds(Long[] ids);

    /**
     * 删除支付宝配置类信息
     * 
     * @param id 支付宝配置类ID
     * @return 结果
     */
    public int deleteYunAlipayConfigById(Long id);

    /*
     * @Description :支付
     * @Author : 梁少鹏
     * @Date : 2020/1/28 10:19
     */
    public String toPayAsPc(YunAlipayConfig yunAlipayConfig) throws Exception;
}
