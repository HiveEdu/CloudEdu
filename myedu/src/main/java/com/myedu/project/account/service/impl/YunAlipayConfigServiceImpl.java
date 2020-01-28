package com.myedu.project.account.service.impl;

import java.util.List;
import com.myedu.common.utils.DateUtils;
import com.myedu.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.account.mapper.YunAlipayConfigMapper;
import com.myedu.project.account.domain.YunAlipayConfig;
import com.myedu.project.account.service.IYunAlipayConfigService;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
/**
 * 支付宝配置类Service业务层处理
 * 
 * @author 梁少鹏
 * @date 2020-01-27
 */
@Service
public class YunAlipayConfigServiceImpl implements IYunAlipayConfigService 
{
    @Autowired
    private YunAlipayConfigMapper yunAlipayConfigMapper;

    /**
     * 查询支付宝配置类
     * 
     * @param id 支付宝配置类ID
     * @return 支付宝配置类
     */
    @Override
    public YunAlipayConfig selectYunAlipayConfigById(Long id)
    {
        return yunAlipayConfigMapper.selectYunAlipayConfigById(id);
    }

    /**
     * 查询支付宝配置类列表
     * 
     * @param yunAlipayConfig 支付宝配置类
     * @return 支付宝配置类
     */
    @Override
    public List<YunAlipayConfig> selectYunAlipayConfigList(YunAlipayConfig yunAlipayConfig)
    {
        return yunAlipayConfigMapper.selectYunAlipayConfigList(yunAlipayConfig);
    }

    /**
     * 新增支付宝配置类
     * 
     * @param yunAlipayConfig 支付宝配置类
     * @return 结果
     */
    @Override
    public int insertYunAlipayConfig(YunAlipayConfig yunAlipayConfig)
    {
        yunAlipayConfig.setCreateById(SecurityUtils.getUserId());
        yunAlipayConfig.setCreateBy(SecurityUtils.getUsername());
        yunAlipayConfig.setCreateTime(DateUtils.getNowDate());
        yunAlipayConfig.setSignType("RSA2");
        yunAlipayConfig.setGatewayUrl("https://openapi.alipaydev.com/gateway.do");
        yunAlipayConfig.setCharset("utf-8");
        yunAlipayConfig.setFormat("json");
        return yunAlipayConfigMapper.insertYunAlipayConfig(yunAlipayConfig);
    }

    /**
     * 修改支付宝配置类
     * 
     * @param yunAlipayConfig 支付宝配置类
     * @return 结果
     */
    @Override
    public int updateYunAlipayConfig(YunAlipayConfig yunAlipayConfig)
    {
        yunAlipayConfig.setUpdateTime(DateUtils.getNowDate());
        return yunAlipayConfigMapper.updateYunAlipayConfig(yunAlipayConfig);
    }

    /**
     * 批量删除支付宝配置类
     * 
     * @param ids 需要删除的支付宝配置类ID
     * @return 结果
     */
    @Override
    public int deleteYunAlipayConfigByIds(Long[] ids)
    {
        return yunAlipayConfigMapper.deleteYunAlipayConfigByIds(ids);
    }

    /**
     * 删除支付宝配置类信息
     * 
     * @param id 支付宝配置类ID
     * @return 结果
     */
    @Override
    public int deleteYunAlipayConfigById(Long id)
    {
        return yunAlipayConfigMapper.deleteYunAlipayConfigById(id);
    }
    /*
     * @Description :网页pc支付
     * @Author : 梁少鹏
     * @Date : 2020/1/28 10:35
     */
    @Override
    public String toPayAsPc(YunAlipayConfig yunAlipayConfig) throws Exception{
        AlipayClient alipayClient = new DefaultAlipayClient(yunAlipayConfig.getGatewayUrl(),
                yunAlipayConfig.getAppId(),
                yunAlipayConfig.getPrivateKey(),
                yunAlipayConfig.getFormat(),
                yunAlipayConfig.getCharset(),
                yunAlipayConfig.getPublicKey(),
                yunAlipayConfig.getSignType());
        // 创建API对应的request(电脑网页版)
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        //订单完成后返回的页面和异步通知地址
        request.setReturnUrl(yunAlipayConfig.getReturnUrl());
        request.setNotifyUrl(yunAlipayConfig.getNotifyUrl());
        // 填充订单参数
        String order="0012364255";//订单号
        String totalAmount="0.1";//支付金额
        String subject="手机";//商品名称
        String body="苹果";//商品描述
        request.setBizContent("{" +
                "    \"out_trade_no\":\""+order+"\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":"+totalAmount+"," +
                "    \"subject\":\""+subject+"\"," +
                "    \"body\":\""+body+"\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\""+yunAlipayConfig.getSysServiceProviderId()+"\"" +
                "    }"+
                "  }");//填充业务参数
        // 调用SDK生成表单, 通过GET方式，口可以获取url
        System.out.println(alipayClient.pageExecute(request, "GET").getBody());
        return alipayClient.pageExecute(request, "GET").getBody();
    }
}
