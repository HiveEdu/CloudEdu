package com.myedu.project.order.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public static final String TRADE_SUCCESS = "TRADE_SUCCESS"; //支付成功标识
    public static final String TRADE_CLOSED = "TRADE_CLOSED";//交易关闭
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
                "    \"order_id\":\""+yunOrder.getId()+"\"," +
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


    @Override
    public String synchronous(HttpServletRequest request) {

        Map<String, String> parameters = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        System.out.println("支付宝同步参数"+requestParams);
        for (Map.Entry<String, String[]> entry : requestParams.entrySet()) {
            String key = entry.getKey();
            String[] values = entry.getValue();
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            parameters.put(key, valueStr);
        }

        //调用SDK验证签名
        String out_trade_no = request.getParameter("out_trade_no");//订单号
        YunOrder yunOrder= yunOrderMapper.selectYunOrderByNum(out_trade_no);
        YunStore yunStore= yunStoreMapper.selectYunStoreById(yunOrder.getStoreId());
        YunAlipayConfig yunAlipayConfig=new YunAlipayConfig();
        yunAlipayConfig.setCreateById(yunStore.getCreateById());
        List<YunAlipayConfig> yunAlipayConfigs= yunAlipayConfigMapper.selectYunAlipayConfigList(yunAlipayConfig);
        if(yunAlipayConfigs!=null){
            yunAlipayConfig=yunAlipayConfigs.get(0);
        }
        boolean signVerified = false;//验证签名
        try {
            signVerified = AlipaySignature.rsaCheckV1(parameters, yunAlipayConfig.getPublicKey(), yunAlipayConfig.getCharset(), yunAlipayConfig.getSignType());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        ModelAndView mv = new ModelAndView("alipaySuccess");
        //——请在这里编写您的程序（以下代码仅作参考）——
        if(signVerified) {
            //付款金额
            String total_amount = new String(request.getParameter("total_amount"));
            // 修改订单状态为支付成功，已付款; 同时新增支付流水
            yunOrder.setPayWay("1");//支付方式支付宝支付
            yunOrder.setStatus("2");//已支付状态
            yunOrder.setTotalMoney(new BigDecimal(total_amount));
            yunOrderMapper.updateYunOrder(yunOrder);
            System.out.println("支付, 验签成功...");
            return "success";
        }else {
            System.out.println("支付, 验签失败...");
            return "fail";
        }
    }

    @Override
    public void notify(HttpServletRequest request, HttpServletResponse response) {
        //接收参数进行校验
        Map<String, String> parameters = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : requestParams.entrySet()) {
            String key = entry.getKey();
            String[] values = entry.getValue();
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            parameters.put(key, valueStr);
        }
        System.out.println("parameters is [parameters={}]"
                +parameters);
        String appId = request.getParameter("app_id");//appid
        String merchantOrderNo = request.getParameter("out_trade_no");//商户订单号

        String orderId = request.getParameter("order_id");//orderId
        if (orderId == null) {
            System.out.println("orderId is null");
        }
        System.out.println("orderId: {}"+ orderId);
        String payState = request.getParameter("trade_status");//交易状态
        String encodeOrderNum = null;
//        cashLogMapper.add(request.getParameter("out_trade_no"), "NOTIFY", JSON.toJSONString(parameters), new Date());
        try {
//            encodeOrderNum = URLDecoder.decode(request.getParameter("passback_params"), "UTF-8");
//            log.info("encodeOrderNum is [encodeOrderNum={}]", encodeOrderNum);
            YunOrder yunOrder= yunOrderMapper.selectYunOrderById(Long.valueOf(orderId));
            YunStore yunStore= yunStoreMapper.selectYunStoreById(yunOrder.getStoreId());
            YunAlipayConfig yunAlipayConfig=new YunAlipayConfig();
            yunAlipayConfig.setCreateById(yunStore.getCreateById());
            List<YunAlipayConfig> yunAlipayConfigs= yunAlipayConfigMapper.selectYunAlipayConfigList(yunAlipayConfig);
            if(yunAlipayConfigs!=null){
                yunAlipayConfig=yunAlipayConfigs.get(0);
            }
            boolean signVerified;
            signVerified = AlipaySignature.rsaCheckV1(parameters, yunAlipayConfig.getPublicKey(), yunAlipayConfig.getCharset(), yunAlipayConfig.getSignType());//验证签名
            System.out.println("signVerified is [signVerified={}]"+signVerified);
            if (signVerified) { //通过验证
                System.out.println("payState: {}"+ payState);
                if (payState.equals(TRADE_SUCCESS)) {
                    //判断订单号与插入的订单号是否一样
                    if (merchantOrderNo.equals(encodeOrderNum) == false || yunAlipayConfig.getAppId().equals(appId) == false) {
                        System.out.println("vali failure");
//                        cashMapper.update(merchantOrderNo, 4);
//                        response.getOutputStream().print("failure");
                        return;
                    }
//                    cashMapper.update(merchantOrderNo, 3);
//                    orderMapper.afterPay(orderId);
//                    response.getOutputStream().print("success");
                    return;
                } else if (payState.equals(TRADE_CLOSED)) { //交易关闭
//                    cashMapper.update(merchantOrderNo, 7);
                } else {
//                    cashMapper.update(merchantOrderNo, 4);
//                    response.getOutputStream().print("failure");
                    return;
                }
            } else {
                //签名校验失败更状态
//                cashMapper.update(merchantOrderNo, 4);
//                response.getOutputStream().print("failure");
                return;
            }
//            log.info("encodeOrderNum is  [encodeOrderNum={}]", encodeOrderNum);
//            cashMapper.update(merchantOrderNo, 4);
//            response.getOutputStream().print("failure");
            return;
        } catch (AlipayApiException e) {
//            log.error(e.getErrMsg());
            throw new RuntimeException("调用支付宝接口发生异常");
        }

    }

}
