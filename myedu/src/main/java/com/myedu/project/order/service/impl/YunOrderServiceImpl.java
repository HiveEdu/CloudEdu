package com.myedu.project.order.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.cert.Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.*;
import com.alipay.api.response.AlipayFundTransUniTransferResponse;
import com.alipay.api.response.AlipayOfflineMarketShopCreateResponse;
import com.alipay.api.response.AlipayTradeOrderSettleResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.myedu.common.utils.DateUtils;
import com.myedu.common.utils.OrderCodeFactory;
import com.myedu.common.utils.PayUtils;
import com.myedu.common.utils.SecurityUtils;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.project.account.domain.YunAccountChange;
import com.myedu.project.account.domain.YunAlipayConfig;
import com.myedu.project.account.enums.AccountChangeType;
import com.myedu.project.account.mapper.YunAccountChangeMapper;
import com.myedu.project.account.mapper.YunAlipayConfigMapper;
import com.myedu.project.order.domain.vo.YunOrderVo;
import com.myedu.project.order.enums.OrderStatus;
import com.myedu.project.order.enums.PaymentType;
import com.myedu.project.store.domain.YunStore;
import com.myedu.project.store.mapper.YunStoreMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.order.mapper.YunOrderMapper;
import com.myedu.project.order.domain.YunOrder;
import com.myedu.project.order.service.IYunOrderService;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alipay.api.CertAlipayRequest;
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
    private YunAccountChangeMapper yunAccountChangeMapper;
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
        YunAlipayConfig yunAlipayConfig=new YunAlipayConfig();
        yunAlipayConfig.setPayMentType(PaymentType.PAYMENTOFANORDER.getCode());
        List<YunAlipayConfig> yunAlipayConfigs= yunAlipayConfigMapper.selectYunAlipayConfigList(yunAlipayConfig);
        if(yunAlipayConfigs!=null){
            yunAlipayConfig=yunAlipayConfigs.get(0);
        }
        //https://openasyncapi.alipay.com/gateway.do  (收单资金结算到银行账户，结算成功的异步通知)
        //https://openapi.alipaydev.com/gateway.do  (支付指定支付宝账户)
        AlipayClient alipayClient = new DefaultAlipayClient(yunAlipayConfig.getGatewayUrl(),
                yunAlipayConfig.getAppId(),
                yunAlipayConfig.getPrivateKey(),
                yunAlipayConfig.getFormat(),
                yunAlipayConfig.getCharset(),
                yunAlipayConfig.getPublicKey(),
                yunAlipayConfig.getSignType());
        // 填充订单参数
        String order=yunOrder.getNum();//订单号
        YunOrder yunOrderStatus= yunOrderMapper.selectYunOrderByNum(order);
        Integer status=PayUtils.checkAlipay(alipayClient,order);
        if(status!=null){
            yunOrderStatus.setStatus(String.valueOf(status));
            yunOrderMapper.updateYunOrder(yunOrderStatus);
        }
        // 创建API对应的request(电脑网页版)
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        //订单完成后返回的页面和异步通知地址
        request.setReturnUrl(yunAlipayConfig.getReturnUrl());
        request.setNotifyUrl(yunAlipayConfig.getNotifyUrl());

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
        return alipayClient.pageExecute(request, "GET").getBody();
    }


    @Override
    public String toPayAsWeb(YunOrderVo yunOrder) throws Exception{
        YunAlipayConfig yunAlipayConfig=new YunAlipayConfig();
        yunAlipayConfig.setPayMentType(PaymentType.PAYMENTOFANORDER.getCode());
        List<YunAlipayConfig> yunAlipayConfigs= yunAlipayConfigMapper.selectYunAlipayConfigList(yunAlipayConfig);
        if(yunAlipayConfigs!=null){
            yunAlipayConfig=yunAlipayConfigs.get(0);
        }
        AlipayClient alipayClient = new DefaultAlipayClient(yunAlipayConfig.getGatewayUrl(), yunAlipayConfig.getAppId(), yunAlipayConfig.getPrivateKey(), yunAlipayConfig.getFormat(), yunAlipayConfig.getCharset(), yunAlipayConfig.getPublicKey(), yunAlipayConfig.getSignType());
        String order=yunOrder.getNum();//订单号
        double money = Double.parseDouble(String.valueOf(yunOrder.getTotalMoney()));
        YunOrder yunOrderStatus= yunOrderMapper.selectYunOrderByNum(order);
        Integer status=PayUtils.checkAlipay(alipayClient,order);
        if(status!=null){
            yunOrderStatus.setStatus(String.valueOf(status));
            yunOrderMapper.updateYunOrder(yunOrderStatus);
        }
        // 创建API对应的request(手机网页版)
        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
        request.setReturnUrl(yunAlipayConfig.getReturnUrl());
        request.setNotifyUrl(yunAlipayConfig.getNotifyUrl());
        String subject=yunOrder.getCourseName();//商品名称
        String body=yunOrder.getRemark();//商品描述
        request.setBizContent("{" +
                "    \"order_id\":\""+yunOrder.getId()+"\"," +
                "    \"out_trade_no\":\""+order+"\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":"+money+"," +
                "    \"subject\":\""+subject+"\"," +
                "    \"body\":\""+body+"\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\""+yunAlipayConfig.getSysServiceProviderId()+"\"" +
                "    }"+
                "  }");

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
        //支付宝的交易号
        String tradeNo = request.getParameter("trade_no");
        System.out.println(tradeNo);
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
        String payState = request.getParameter("trade_status");//交易状态
        ModelAndView mv = new ModelAndView("alipaySuccess");
        //——请在这里编写您的程序（以下代码仅作参考）——
        if(signVerified) {
                //付款金额
                String total_amount = new String(request.getParameter("total_amount"));
                // 修改订单状态为支付成功，已付款; 同时新增支付流水
                yunOrder.setPayWay("1");//支付方式支付宝支付
                yunOrder.setStatus(OrderStatus.HAVETOPAY.getCode());//已支付状态
                yunOrder.setTotalMoney(new BigDecimal(total_amount));
                yunOrder.setTradeNo(tradeNo);
                yunOrderMapper.updateYunOrder(yunOrder);
                //增加支付账单流水
                YunAccountChange yunAccountChange=new YunAccountChange();
                yunAccountChange.setUserId(yunOrder.getCreateById());
                yunAccountChange.setUncashAmount(yunOrder.getTotalMoney());
                yunAccountChange.setChangeType(AccountChangeType.PAYMENT.getCode());
                yunAccountChange.setRefId(tradeNo);
                yunAccountChange.setCreateById(yunOrder.getCreateById());
                yunAccountChange.setCreateBy(yunOrder.getCreateBy());
                yunAccountChange.setCreateTime(DateUtils.getNowDate());
                yunAccountChangeMapper.insertYunAccountChange(yunAccountChange);
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
                //支付宝的交易号
                String tradeNo = request.getParameter("trade_no");
                System.out.println("payState: {}"+ payState);
                if (payState.equals(TRADE_SUCCESS)) {
                    //判断订单号与插入的订单号是否一样
                    if (merchantOrderNo.equals(encodeOrderNum) == false || yunAlipayConfig.getAppId().equals(appId) == false) {
                        System.out.println("vali failure");
                        return;
                    }
                    return;
                } else if (payState.equals(TRADE_CLOSED)) { //交易关闭

                } else {
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

    @Override
    public AjaxResult rebund(Long id) {
        YunAlipayConfig yunAlipayConfig=new YunAlipayConfig();
        yunAlipayConfig.setPayMentType(PaymentType.PAYMENTOFANORDER.getCode());
        List<YunAlipayConfig> yunAlipayConfigs= yunAlipayConfigMapper.selectYunAlipayConfigList(yunAlipayConfig);
        if(yunAlipayConfigs!=null){
            yunAlipayConfig=yunAlipayConfigs.get(0);
        }
        AlipayClient alipayClient = new DefaultAlipayClient(yunAlipayConfig.getGatewayUrl(), yunAlipayConfig.getAppId(), yunAlipayConfig.getPrivateKey(), yunAlipayConfig.getFormat(), yunAlipayConfig.getCharset(), yunAlipayConfig.getPublicKey(), yunAlipayConfig.getSignType());
        YunOrder yunOrder=yunOrderMapper.selectYunOrderById(id);
        AjaxResult result=PayUtils.alipayRefundRequest(alipayClient,yunOrder.getNum(),yunOrder.getTradeNo(),yunOrder.getTotalMoney().doubleValue());
        if(String.valueOf(result.get("code")).equals("200")){
            yunOrder.setStatus(OrderStatus.HAVEAREFUND.getCode());
            yunOrderMapper.updateYunOrder(yunOrder);
            //增加退款账单流水
            YunAccountChange yunAccountChange=new YunAccountChange();
            yunAccountChange.setUserId(yunOrder.getCreateById());
            yunAccountChange.setPreAmount(null);
            yunAccountChange.setCashAmount(null);
            yunAccountChange.setUncashAmount(yunOrder.getTotalMoney());
            yunAccountChange.setChangeType(AccountChangeType.REFUND.getCode());
            yunAccountChange.setRefId(String.valueOf(result.get("msg")));
            yunAccountChange.setCreateById(SecurityUtils.getUserId());
            yunAccountChange.setCreateBy(SecurityUtils.getUsername());
            yunAccountChange.setCreateTime(DateUtils.getNowDate());
            yunAccountChangeMapper.insertYunAccountChange(yunAccountChange);
        }
        return result;
    }

    public static void main(String[] args) {
        //单笔转账到支付宝账户
        CertAlipayRequest certAlipayRequest = new CertAlipayRequest();
        certAlipayRequest.setServerUrl("https://openapi.alipay.com/gateway.do");
        certAlipayRequest.setAppId("2021001141615718");
        certAlipayRequest.setPrivateKey("MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCDXeZL15XDePyu9Q82kpxMGabwhXWanGzL37EKqLIxzKKMflxNOVc463pHxqo7MCm+8Gz4CWcRhIppvk1ZrbCOEyaczFImg3jAbuu/tSjfMAScIGKKBIPQfIbvOZqLynF9PZGeTLY7b9sQnnaE1HeF6a59Zo79RuSAPEJ2kZZfYqAV8rBxSH+/9vqZI0U7p/mmsNApyx+OQxdZyZfcA3fZTTQ17goIqkKjS+HTPERuT/n68H049wgnr/kw60kbWiybgdmKb0y9GrM0TPYHzkcmowwN+EiLD2aaMmiwdeVV30NnRAh34+BVEmwon0s0qpRXEF81o76He8a3sxVYYVOBAgMBAAECggEBAINc35ERuNoqXwuZVKguvYq/0BcObga4y3anboOJI//2cMlrBmPWpijuGG4z9ECRrf0YsO7rgBydXEfXm8PEkxvPfLoo+zE2IQbVJHuk7YUZoFJ7c+4uC53tGIWaJbgKLaiea8JPVZ9kADzDKSM0z3yiq/EMIIqXXKH7ID8u4J7qMhsQvP5l0jNL/cxOIvPfXf2V8q6X8voxW77SBcinAXZ6UvpQfBSz4YHIusEdVLz3sv81t3Y3+HrvY1EB9TJUbC8kJ5k5a2d/rxbGokwaIb3ewfZkn2UUXnHpb4OO+7dcOeFFnsILJUBLY+3GBZOTfH/dVrpGgw69+9wPxJY/KP0CgYEAxNB/A75QWTLA+jI6er2YWLQ+V+9Yg7drYfo/xjAf7UJWYU12BghlBXVuKpB4L/Fqgg+dk2ztpQ9jyZtiM9T5FKYYuLbZyW0SXp3XhG9IXXZ3kivlJMgQhGzP641/tOGimaTGUmm3P0fcTVANKMXvCC7wVy03o2xBiXGaoqiolb8CgYEAqt8AbjCW05LE1N1xUcSUlZpzL/aftnHN+mWhEyh4PQ4tgKqCT6Vg7BPiYL2VeXP9/wzxVL6E5uDoGfEOKpcsyj2LEjgl4z99LXTYlujuzEENiu79muPCP2p32MtTWYsNz1YjyayWWCt8mOXiPGU8cpWsnv1l0r+w1yKmrpfw5r8CgYAdk9vSuzImBYuczhmdwop3ojbQTkb2HIWFxu74oNjbdiG3I8X96BsOc/Rnbyiww84m16PtuOwJ50Mu1Cww7+uy37dNYyVdsQeWso/l9jdg3GHh1LjuCyb4T8X7d1te2p3uCyZG0l5YozsHX2Vq4Dwo9azm3QPEiota1XjQwxn6zwKBgAYFJ1m6goXfxOmPB8AOwgFnWKmW8deWuOAalos7QMk956KB32d49yo6JR6tb0fBK3DB/vqyX7JWuDa5xHsBqoVRl8XDjqosAXgJhtwCRu+RVy3O78Z2QdDEKobL2xrriEgGy+n+F6nxlkk4yDjChFexKEzNtrmvIUbjJKZAGi+3AoGAQ7zMhX5kyTkcBuMuaL/VijcfBFbT9zmbIEfW/DbZ1lMqpGqDnsU9doFPqiVn4saTohEpMo2U3ZiLKMsahkUOdi2MFYxyfuDYsXxQkHR3LAgOAmGtkXfo5i56sXsxWemFaHld1ArvdI8nomWGwKRXirb6aWIVJ5jcxg+c1oYlErM=");
        certAlipayRequest.setFormat("json");
        certAlipayRequest.setCharset("utf-8");
        certAlipayRequest.setSignType("RSA2");
        //请更换为您的应用公钥证书文件路径
        certAlipayRequest.setCertPath("G:\\MyProject\\MyEdu\\appCertPublicKey_2021001141615718.crt");
        //请更换您的支付宝公钥证书文件路径
        certAlipayRequest.setAlipayPublicCertPath("G:\\MyProject\\MyEdu\\alipayCertPublicKey_RSA2.crt");
        //更换为支付宝根证书文件路径
        certAlipayRequest.setRootCertPath("G:\\MyProject\\MyEdu\\alipayRootCert.crt");
        DefaultAlipayClient alipayClient =null;
        try {
            alipayClient = new DefaultAlipayClient(certAlipayRequest);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        AlipayFundTransUniTransferRequest request = new AlipayFundTransUniTransferRequest();
        request.setBizContent("{" +
                "\"out_biz_no\":\"201806300001\"," +
                "\"trans_amount\":1.68," +
                "\"product_code\":\"TRANS_ACCOUNT_NO_PWD\"," +
                "\"biz_scene\":\"DIRECT_TRANSFER\"," +
                "\"order_title\":\"201905代发\"," +
                "\"payee_info\":{" +
                "\"identity\":\"2088123412341234\"," +
                "\"identity_type\":\"ALIPAY_USER_ID\"," +
                "\"name\":\"黄龙国际有限公司\"," +
                "    }," +
                "\"remark\":\"201905代发\"," +
                "\"business_params\":\"{\\\"payer_show_name\\\":\\\"服务代理\\\"}\"," +
                "  }");
        AlipayFundTransUniTransferResponse response = null;
        try {
            response = alipayClient.certificateExecute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(response.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }



        //收单资金结算到银行账户，结算成功的异步通知测试开始
//        AlipayClient alipayClient = new DefaultAlipayClient("https://openasyncapi.alipay.com/gateway.do",
//                "2021001141615718","MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCDXeZL15XDePyu9Q82kpxMGabwhXWanGzL37EKqLIxzKKMflxNOVc463pHxqo7MCm+8Gz4CWcRhIppvk1ZrbCOEyaczFImg3jAbuu/tSjfMAScIGKKBIPQfIbvOZqLynF9PZGeTLY7b9sQnnaE1HeF6a59Zo79RuSAPEJ2kZZfYqAV8rBxSH+/9vqZI0U7p/mmsNApyx+OQxdZyZfcA3fZTTQ17goIqkKjS+HTPERuT/n68H049wgnr/kw60kbWiybgdmKb0y9GrM0TPYHzkcmowwN+EiLD2aaMmiwdeVV30NnRAh34+BVEmwon0s0qpRXEF81o76He8a3sxVYYVOBAgMBAAECggEBAINc35ERuNoqXwuZVKguvYq/0BcObga4y3anboOJI//2cMlrBmPWpijuGG4z9ECRrf0YsO7rgBydXEfXm8PEkxvPfLoo+zE2IQbVJHuk7YUZoFJ7c+4uC53tGIWaJbgKLaiea8JPVZ9kADzDKSM0z3yiq/EMIIqXXKH7ID8u4J7qMhsQvP5l0jNL/cxOIvPfXf2V8q6X8voxW77SBcinAXZ6UvpQfBSz4YHIusEdVLz3sv81t3Y3+HrvY1EB9TJUbC8kJ5k5a2d/rxbGokwaIb3ewfZkn2UUXnHpb4OO+7dcOeFFnsILJUBLY+3GBZOTfH/dVrpGgw69+9wPxJY/KP0CgYEAxNB/A75QWTLA+jI6er2YWLQ+V+9Yg7drYfo/xjAf7UJWYU12BghlBXVuKpB4L/Fqgg+dk2ztpQ9jyZtiM9T5FKYYuLbZyW0SXp3XhG9IXXZ3kivlJMgQhGzP641/tOGimaTGUmm3P0fcTVANKMXvCC7wVy03o2xBiXGaoqiolb8CgYEAqt8AbjCW05LE1N1xUcSUlZpzL/aftnHN+mWhEyh4PQ4tgKqCT6Vg7BPiYL2VeXP9/wzxVL6E5uDoGfEOKpcsyj2LEjgl4z99LXTYlujuzEENiu79muPCP2p32MtTWYsNz1YjyayWWCt8mOXiPGU8cpWsnv1l0r+w1yKmrpfw5r8CgYAdk9vSuzImBYuczhmdwop3ojbQTkb2HIWFxu74oNjbdiG3I8X96BsOc/Rnbyiww84m16PtuOwJ50Mu1Cww7+uy37dNYyVdsQeWso/l9jdg3GHh1LjuCyb4T8X7d1te2p3uCyZG0l5YozsHX2Vq4Dwo9azm3QPEiota1XjQwxn6zwKBgAYFJ1m6goXfxOmPB8AOwgFnWKmW8deWuOAalos7QMk956KB32d49yo6JR6tb0fBK3DB/vqyX7JWuDa5xHsBqoVRl8XDjqosAXgJhtwCRu+RVy3O78Z2QdDEKobL2xrriEgGy+n+F6nxlkk4yDjChFexKEzNtrmvIUbjJKZAGi+3AoGAQ7zMhX5kyTkcBuMuaL/VijcfBFbT9zmbIEfW/DbZ1lMqpGqDnsU9doFPqiVn4saTohEpMo2U3ZiLKMsahkUOdi2MFYxyfuDYsXxQkHR3LAgOAmGtkXfo5i56sXsxWemFaHld1ArvdI8nomWGwKRXirb6aWIVJ5jcxg+c1oYlErM=",
//                "json","GBK","MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAg13mS9eVw3j8rvUPNpKcTBmm8IV1mpxsy9+xCqiyMcyijH5cTTlXOOt6R8aqOzApvvBs+AlnEYSKab5NWa2wjhMmnMxSJoN4wG7rv7Uo3zAEnCBiigSD0HyG7zmai8pxfT2Rnky2O2/bEJ52hNR3hemufWaO/UbkgDxCdpGWX2KgFfKwcUh/v/b6mSNFO6f5prDQKcsfjkMXWcmX3AN32U00Ne4KCKpCo0vh0zxEbk/5+vB9OPcIJ6/5MOtJG1osm4HZim9MvRqzNEz2B85HJqMMDfhIiw9mmjJosHXlVd9DZ0QId+PgVRJsKJ9LNKqUVxBfNaO+h3vGt7MVWGFTgQIDAQAB","RSA2");
//        AlipayTradeOrderSettleRequest request = new AlipayTradeOrderSettleRequest();
//        request.setBizContent("{" +
//                "\"settle_time\":\"2009-05-06 10:07:41\"," +
//                "\"partner_id\":\"2088101106499364\"," +
//                "\"settle_id\":\"50002016083000032007000000011833\"," +
//                "\"settle_amount\":\"1.01\"," +
//                "\"settle_currency\":\"CNY\"," +
//                "\"settle_period_begin_time\":\"2018-01-01 00:00:00\"," +
//                "\"settle_period_end_time\":\"2018-01-02 00:00:00\"," +
//                "\"biz_params\":\"{\\\"account_type\\\":\\\"BANK_ACCOUNT\\\",\\\"bank_name\\\":\\\"招商银行\\\",\\\"bank_account_no\\\":\\\"****1234\\\",\\\"bank_account_name\\\":\\\"支付宝公司\\\",\\\"alipay_card_id\\\":\\\"A1234\\\",\\\"remit_memo\\\":\\\"打款备注\\\",\\\"second_merchant_id\\\":\\\"2088000069652893\\\",\\\"store_id\\\":\\\"100001\\\"}\"," +
//                "\"biz_product\":\"FACE_TO_FACE_PAYMENT\"," +
//                "\"extend_params\":\"{\\\"alipay_trade_no\\\":\\\"2019121910032009460200611223\\\",\\\"out_request_no\\\":\\\"201848121193\\\"}\"" +
//                "  }");
//        AlipayTradeOrderSettleResponse response = null;
//        try {
//            response = alipayClient.execute(request);
//        } catch (AlipayApiException e) {
//            e.printStackTrace();
//        }
//        if(response.isSuccess()){
//            System.out.println("调用成功");
//        } else {
//            System.out.println("调用失败");
//        }
        //收单资金结算到银行账户，结算成功的异步通知测试结束


//        CertAlipayRequest certAlipayRequest = new CertAlipayRequest();
//        certAlipayRequest.setServerUrl("https://openapi.alipay.com/gateway.do");
//        certAlipayRequest.setAppId("2021001141615718");
//        //应用私匙
//        certAlipayRequest.setPrivateKey("MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCDXeZL15XDePyu9Q82kpxMGabwhXWanGzL37EKqLIxzKKMflxNOVc463pHxqo7MCm+8Gz4CWcRhIppvk1ZrbCOEyaczFImg3jAbuu/tSjfMAScIGKKBIPQfIbvOZqLynF9PZGeTLY7b9sQnnaE1HeF6a59Zo79RuSAPEJ2kZZfYqAV8rBxSH+/9vqZI0U7p/mmsNApyx+OQxdZyZfcA3fZTTQ17goIqkKjS+HTPERuT/n68H049wgnr/kw60kbWiybgdmKb0y9GrM0TPYHzkcmowwN+EiLD2aaMmiwdeVV30NnRAh34+BVEmwon0s0qpRXEF81o76He8a3sxVYYVOBAgMBAAECggEBAINc35ERuNoqXwuZVKguvYq/0BcObga4y3anboOJI//2cMlrBmPWpijuGG4z9ECRrf0YsO7rgBydXEfXm8PEkxvPfLoo+zE2IQbVJHuk7YUZoFJ7c+4uC53tGIWaJbgKLaiea8JPVZ9kADzDKSM0z3yiq/EMIIqXXKH7ID8u4J7qMhsQvP5l0jNL/cxOIvPfXf2V8q6X8voxW77SBcinAXZ6UvpQfBSz4YHIusEdVLz3sv81t3Y3+HrvY1EB9TJUbC8kJ5k5a2d/rxbGokwaIb3ewfZkn2UUXnHpb4OO+7dcOeFFnsILJUBLY+3GBZOTfH/dVrpGgw69+9wPxJY/KP0CgYEAxNB/A75QWTLA+jI6er2YWLQ+V+9Yg7drYfo/xjAf7UJWYU12BghlBXVuKpB4L/Fqgg+dk2ztpQ9jyZtiM9T5FKYYuLbZyW0SXp3XhG9IXXZ3kivlJMgQhGzP641/tOGimaTGUmm3P0fcTVANKMXvCC7wVy03o2xBiXGaoqiolb8CgYEAqt8AbjCW05LE1N1xUcSUlZpzL/aftnHN+mWhEyh4PQ4tgKqCT6Vg7BPiYL2VeXP9/wzxVL6E5uDoGfEOKpcsyj2LEjgl4z99LXTYlujuzEENiu79muPCP2p32MtTWYsNz1YjyayWWCt8mOXiPGU8cpWsnv1l0r+w1yKmrpfw5r8CgYAdk9vSuzImBYuczhmdwop3ojbQTkb2HIWFxu74oNjbdiG3I8X96BsOc/Rnbyiww84m16PtuOwJ50Mu1Cww7+uy37dNYyVdsQeWso/l9jdg3GHh1LjuCyb4T8X7d1te2p3uCyZG0l5YozsHX2Vq4Dwo9azm3QPEiota1XjQwxn6zwKBgAYFJ1m6goXfxOmPB8AOwgFnWKmW8deWuOAalos7QMk956KB32d49yo6JR6tb0fBK3DB/vqyX7JWuDa5xHsBqoVRl8XDjqosAXgJhtwCRu+RVy3O78Z2QdDEKobL2xrriEgGy+n+F6nxlkk4yDjChFexKEzNtrmvIUbjJKZAGi+3AoGAQ7zMhX5kyTkcBuMuaL/VijcfBFbT9zmbIEfW/DbZ1lMqpGqDnsU9doFPqiVn4saTohEpMo2U3ZiLKMsahkUOdi2MFYxyfuDYsXxQkHR3LAgOAmGtkXfo5i56sXsxWemFaHld1ArvdI8nomWGwKRXirb6aWIVJ5jcxg+c1oYlErM=");
//        certAlipayRequest.setFormat("json");
//        certAlipayRequest.setCharset("utf-8");
//        certAlipayRequest.setSignType("RSA2");
//        //请更换为您的应用公钥证书文件路径
//        certAlipayRequest.setCertPath("G:\\MyProject\\MyEdu\\appCertPublicKey_2021001141615718.crt");
//        //请更换您的支付宝公钥证书文件路径
//        certAlipayRequest.setAlipayPublicCertPath("G:\\MyProject\\MyEdu\\alipayCertPublicKey_RSA2.crt");
//        //更换为支付宝根证书文件路径
//        certAlipayRequest.setRootCertPath("G:\\MyProject\\MyEdu\\alipayRootCert.crt");
//        DefaultAlipayClient alipayClient = null;
//        try {
//            alipayClient = new DefaultAlipayClient(certAlipayRequest);
//        } catch (AlipayApiException e) {
//            e.printStackTrace();
//        }
//        AlipayFundTransUniTransferRequest request = new AlipayFundTransUniTransferRequest();
//        request.setBizContent("{" +
//                "\"out_biz_no\":\"201806300001\"," +
//                "\"trans_amount\":1.68," +
//                "\"product_code\":\"TRANS_BANKCARD_NO_PWD\"," +
//                "\"biz_scene\":\"DIRECT_TRANSFER\"," +
//                "\"order_title\":\"201905代发\"," +
//                "\"payee_info\":{" +
//                "\"identity\":\"6226732518263115\"," +
//                "\"identity_type\":\"BANKCARD_ACCOUNT\"," +
//                "\"name\":\"黄龙国际有限公司\"," +
//                "\"bankcard_ext_info\": \"{" +
//                "\\\"inst_branch_name\\\": \\\"上海市支行\\\","+
//                "\\\"inst_name\\\": \\\"中国光大银行\\\","+
//                "\\\"inst_city\\\": \\\"上海市\\\","+
//                "\\\"inst_province\\\": \\\"上海市\\\","+
//                "\\\"bank_code\\\": \\\"123456\\\","+
//                "\\\"account_type\\\": \\\"1\\\","+
//                "            }, " +
//                "    }," +
//                "\"remark\":\"201905代发\"," +
//                "\"business_params\":\"{\\\"payer_show_name\\\":\\\"服务代理\\\"}\"," +
//                "  }");
//        AlipayFundTransUniTransferResponse response = null;
//        try {
//            response = alipayClient.certificateExecute(request);
//        } catch (AlipayApiException e) {
//            e.printStackTrace();
//        }
//        if(response.isSuccess()){
//            System.out.println("调用成功");
//        } else {
//            System.out.println("调用失败");
//        }




//                AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do","2016102100728796","MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCR+IlGP/WSR1xoeiqgMsJ6OSWMst0MNajfhj2OafVLhwlRYC8QkshuORvJY2aG6KEQL7qJybMbqaXlD/8OLUiMYK0BD7CSdbF9oMAx2dHc87u0JwhBi7n7DInGam9v7iZhq5Pj0ME8CDuRDxPCnZQFh09jcKnIAWOrcEk8S9GCyQn8qYlxd/Gex9y3b51UhMtpURmUIFRszvFiWDMGIO//vq2cCOb4Pm2w6v5eCEABqtenvotmncotVoRn4H6yLfJW0putMTPql0YCLhplceA5m5e/VSKv3/Vmu1Is/EtB20p/dQtth72fResyabpaBAEZX+yOQkaJ/bXYrLaATVqxAgMBAAECggEAO6kbYk0N78We7UzRUzyr5AyrXkBUvILKDOHvEuAX09DTbCEl215C8VoCE2O/KfC2s8juMxbvT6UdGuxY0NCNUGLLEx34i0PzUWd8ZmOitO4hj9ZMN44Ga6eKQECMX3E02sKn5XsTH5g10GobJ3YEY+YT+o63tflrZUy1giFy9kLavmjjw5MrHmJtwvQflSgwDDfcjOBcM5Yuosj4cm42o11eOFeIZ/m2LetW2qKYcDyOfCVCeNiS2oF5C2W/6agqnK9aMw7KReUTJxiD0h+19R+o1iCcXGYYgSDUbbQnH0puINtMHrAuUwplnQjDwozS+XbtAdwRJks9r4iIRU/oAQKBgQDMjezFwF95gOmLYd/tuc4u+reHwNG4QziITyb91yO6QliPyXPXa+Nx0RSOW5nhEWipWLEeToxClqcWjgoe3ROaIVJHWHVIxnQ29tYxyL7TAj3yf38RJqmI5CJTt+bOIFVicsRAJxHZABKVplc0mZAFTI0gbskO6eW+zgvr713UwQKBgQC2rsFUxB/qzsbwd9DD/LwHLrN7Q2eZ9vzmaxp4CFVWJJQl6SinzLZx8quEp0KqOPD5y27FLCXVAtAK7N4pGCL2bi0O8QrszC2tKFL35cdLBkOwPgGp9tRlcflzJWPjb9bcm1jqX+YkgoWdnTbjw2QfhbMQFiKoSFXbmtg5n2dR8QKBgQCQKlK+iLeBXd67km6NEFAFgkRJVBc6w32bbBWx/6e5hTr/k96l6+7v74iKUHriSi+2csDZ+EgYQi9e0RrG588B6QwhJnH6qYuMlWkeyznaY5EfUMZQYyxHQ2oCEPGFQvqKmRJBmIeWpdtzRznc44HCilJIkmv3q9d1I6j5TsmVwQKBgQCslWg8BpUIpZS4NNlbbnbMakhRmRpVEEtX+7Mj1uuX2DLFpgIslG+AMvvq9IE8g1LVCmV5jP2owWF+EMzJEe5l1SxoQzxI/ZPhVIkdfUzMCf0EkW/M/E0oEYbSS26UvWHFRFW6BiNMNqq4foAOqNb8GLlT5zWSudOzBhRkS722YQKBgEvmTa5B344fo9PFIWuH6MnUaQQNN2A3tqXNF1uF/mx2MrpkZ6CBx7KJDYF889OMrVUrUi5iFAx0UmGfOqt6S0q5lTAFXk+6rJDqk4kAW36O7oerEHBL815WYNWuZ7n3ERt1iouz21B2TdB5mZbzmsEKb5V0dGRIa+NmVxD4Ql+e",
//                "json","GBK","+Lgx2OAAULZ4s0TMTCnbEKz60fVENwLxBV51Ic28tIW/DZbB4Iy4ut17aIrnNCusN/m3b0AOjg3Ykdb387lr8Zy5IRjQVKND0j1eTHPd0/EOGDaJJN9Ku4CTKw2+PwIDAQAB","RSA2");
//        AlipayOfflineMarketShopCreateRequest request = new AlipayOfflineMarketShopCreateRequest();
//        request.setBizContent("{" +
//                "\"store_id\":\"hz001\"," +
//                "\"category_id\":\"2015050700000018\"," +
//                "\"brand_name\":\"肯德基\"," +
//                "\"brand_logo\":\"1T8Pp00AT7eo9NoAJkMR3AAAACMAAQEC\"," +
//                "\"main_shop_name\":\"海底捞\"," +
//                "\"branch_shop_name\":\"万塘路店\"," +
//                "\"province_code\":\"110000\"," +
//                "\"city_code\":\"140500\"," +
//                "\"district_code\":\"140521\"," +
//                "\"address\":\"万塘路18号黄龙时代广场\"," +
//                "\"longitude\":114.266418," +
//                "\"latitude\":\"30.548828\"," +
//                "\"contact_number\":\"13612344321,021-12336754\"," +
//                "\"notify_mobile\":\"13867498729\"," +
//                "\"main_image\":\"1T8Pp00AT7eo9NoAJkMR3AAAACMAAQEC\"," +
//                "\"audit_images\":\"1T8Pp00AT7eo9NoAJkMR3AAAACMAAQEC,4Q8Pp00AT7eo9NoAJkMR3AAAACMAAUYT\"," +
//                "\"business_time\":\"周一-周五 09:00-20:00,周六-周日 10:00-22:00\"," +
//                "\"wifi\":\"T\"," +
//                "\"parking\":\"F\"," +
//                "\"value_added\":\"免费茶水、免费糖果\"," +
//                "\"isv_uid\":\"2088001969784501\"," +
//                "\"licence\":\"1T8Pp00AT7eo9NoAJkMR3AAAACMAAQEC\"," +
//                "\"licence_code\":\"H001232\"," +
//                "\"licence_name\":\"来伊份上海分公司\"," +
//                "\"business_certificate\":\"1T8Pp00AT7eo9NoAJkMR3AAAACMAAQEC\"," +
//                "\"business_certificate_expires\":\"2020-03-20\"," +
//                "\"auth_letter\":\"1T8Pp00AT7eo9NoAJkMR3AAAACMAAQEC\"," +
//                "\"is_operating_online\":\"T\"," +
//                "\"online_url\":\"http://www.******.com/shop/21831830\"," +
//                "\"operate_notify_url\":\"http://abc.com\"," +
//                "\"implement_id\":\"HU002,HT002\"," +
//                "\"no_smoking\":\"T\"," +
//                "\"box\":\"T\"," +
//                "\"request_id\":\"2015123235324534\"," +
//                "\"other_authorization\":\"1T8Pp00AT7eo9NoAJkMR3AAAACMAAQEC,1T8Pp00AT7eo9NoAJkMR3AAAACMAAQEC\"," +
//                "\"licence_expires\":\"2020-10-20\"," +
//                "\"op_role\":\"ISV\"," +
//                "\"biz_version\":\"2.0\"" +
//                "  }");
//        AlipayOfflineMarketShopCreateResponse response = null;
//        try {
//            response = alipayClient.execute(request);
//        } catch (AlipayApiException e) {
//            e.printStackTrace();
//        }
//        if(response.isSuccess()){
//            System.out.println("调用成功");
//        } else {
//            System.out.println("调用失败");
//
//        }

    }
}
