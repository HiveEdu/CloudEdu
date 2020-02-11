package com.myedu.project.order.service.impl;

import java.util.List;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.myedu.common.utils.DateUtils;
import com.myedu.common.utils.OrderCodeFactory;
import com.myedu.common.utils.SecurityUtils;
import com.myedu.project.account.domain.YunAlipayConfig;
import com.myedu.project.account.mapper.YunAlipayConfigMapper;
import com.myedu.project.order.domain.vo.YunOrderVo;
import com.myedu.project.order.enums.OrderStatus;
import com.myedu.project.store.domain.YunStore;
import com.myedu.project.store.mapper.YunStoreMapper;
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
    @Autowired
    private YunStoreMapper yunStoreMapper;
    @Autowired
    private YunAlipayConfigMapper yunAlipayConfigMapper;
    /**
     * 查询订单
     * 
     * @param id 订单ID
     * @return 订单
     */
    @Override
    public YunOrderVo selectYunOrderById(Long id)
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

    /**
     * 订单支付网页pc支付
     *
     * @param yunOrder 订单
     * @return 结果
     */
    @Override
    public String toPayAsPc(YunOrderVo yunOrder) throws Exception{
        YunStore yunStore= yunStoreMapper.selectYunStoreById(yunOrder.getStoreId());
        YunAlipayConfig yunAlipayConfig=new YunAlipayConfig();
        yunAlipayConfig.setCreateById(yunStore.getCreateById());
        List<YunAlipayConfig> yunAlipayConfigs= yunAlipayConfigMapper.selectYunAlipayConfigList(yunAlipayConfig);
        if(yunAlipayConfigs!=null){
            yunAlipayConfig=yunAlipayConfigs.get(0);
        }
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
        String order=yunOrder.getNum();//订单号
        String totalAmount=String.valueOf(yunOrder.getTotalMoney());//支付金额
        String subject=yunOrder.getCourseName();//商品名称
        String body=yunOrder.getRemark();//商品描述
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
