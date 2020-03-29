package com.myedu.project.store.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.myedu.common.utils.DateUtils;
import com.myedu.common.utils.StringUtils;
import com.myedu.project.account.domain.YunAlipayConfig;
import com.myedu.project.order.domain.YunOrder;
import com.myedu.project.order.domain.vo.YunOrderVo;
import com.myedu.project.store.domain.YunStore;
import com.myedu.project.store.domain.YunStoreLabel;
import com.myedu.project.store.domain.YunStoreType;
import com.myedu.project.store.domain.vo.YunStoreVo;
import com.myedu.project.store.mapper.YunStoreLabelMapper;
import com.myedu.project.store.mapper.YunStoreMapper;
import com.myedu.project.store.mapper.YunStoreTypeMapper;
import com.myedu.project.store.service.IYunStoreService;
import com.myedu.project.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 门店Service业务层处理
 * 
 * @author 梁少鹏
 * @date 2020-01-04
 */
@Service
public class YunStoreServiceImpl implements IYunStoreService 
{
    @Autowired
    private YunStoreMapper yunStoreMapper;
    @Autowired
    private YunStoreTypeMapper yunStoreTypeMapper;
    @Autowired
    private YunStoreLabelMapper yunStorelabelMapper;
    /**
     * 查询门店
     * 
     * @param id 门店ID
     * @return 门店
     */
    @Override
    public YunStoreVo selectYunStoreById(Long id)
    {
        return yunStoreMapper.selectYunStoreById(id);
    }

    /**
     * 查询门店列表
     * 
     * @param yunStore 门店
     * @return 门店
     */
    @Override
    public List<YunStoreVo> selectYunStoreList(YunStoreVo yunStore)
    {
        return yunStoreMapper.selectYunStoreList(yunStore);
    }

    /**
     * 新增门店
     * 
     * @param yunStore 门店
     * @return 结果
     */
    @Override
    public int insertYunStore(YunStore yunStore)
    {
        yunStore.setCreateTime(DateUtils.getNowDate());
        // 新增门店
        int rows =yunStoreMapper.insertYunStore(yunStore);
        // 新增门店门店类型关联表
        insertStoreType(yunStore);
        // 新增门店门店标签关联表
        insertStoreLabel(yunStore);
        return rows;
    }

    /**
     * 修改门店
     * 
     * @param yunStore 门店
     * @return 结果
     */
    @Override
    public int updateYunStore(YunStore yunStore)
    {
        yunStore.setUpdateTime(DateUtils.getNowDate());
        Long storeId = yunStore.getId();
        // 删除门店与门店类型关联
        yunStoreTypeMapper.deleteStoreTypeByStoreId(storeId);
        // 删除门店与标签类型关联
        yunStorelabelMapper.deleteYunStoreLabelById(storeId);
        // 新增门店门店类型关联表
        insertStoreType(yunStore);
        // 新增门店标签关联表
        insertStoreLabel(yunStore);
        return yunStoreMapper.updateYunStore(yunStore);
    }

    /**
     * 批量删除门店
     * 
     * @param ids 需要删除的门店ID
     * @return 结果
     */
    @Override
    public int deleteYunStoreByIds(Long[] ids)
    {
        //删除与门店类型关联
        //删除与标签类型关联
        for (Long id : ids)
        {
            yunStoreTypeMapper.deleteStoreTypeByStoreId(id);
            yunStorelabelMapper.deleteYunStoreLabelById(id);
        }
        return yunStoreMapper.deleteYunStoreByIds(ids);
    }

    /**
     * 删除门店信息
     * 
     * @param id 门店ID
     * @return 结果
     */
    @Override
    public int deleteYunStoreById(Long id)
    {
        //删除与门店类型关联
        yunStoreTypeMapper.deleteStoreTypeByStoreId(id);
        //删除与标签类型关联
        yunStorelabelMapper.deleteYunStoreLabelById(id);
        return yunStoreMapper.deleteYunStoreById(id);
    }

    /*
     * @Description :新增门店类型
     * @Author : 梁少鹏
     * @Date : 2020/1/5 10:13
     */
    public void insertStoreType(YunStore yunStore)
    {
        Long[] storeTypeIds = yunStore.getStoreTypeIds();
        if (StringUtils.isNotNull(storeTypeIds))
        {
            // 新增门店与门店类型
            List<YunStoreType> list = new ArrayList<YunStoreType>();
            for (Long storeTypeId : storeTypeIds)
            {
                YunStoreType yunStoreType = new YunStoreType();
                yunStoreType.setStoreId(yunStore.getId());
                yunStoreType.setTypeId(storeTypeId);
                list.add(yunStoreType);
            }
            if (list.size() > 0)
            {
                yunStoreTypeMapper.batchStoreType(list);
            }
        }
    }

    /*
     * @Description :新增门店标签
     * @Author : 梁龙飞
     * @Date : 2020/1/5 14:50
     */
    public void insertStoreLabel(YunStore yunStore)
    {
        Long[] storeLabelIds = yunStore.getStoreLabelIds();
        if (StringUtils.isNotNull(storeLabelIds))
        {
            // 新增门店与标签
            List<YunStoreLabel> list1 = new ArrayList<YunStoreLabel>();
            for (Long storeLabelId : storeLabelIds)
            {
                YunStoreLabel yunStoreLabel = new YunStoreLabel();
                yunStoreLabel.setStoreId(yunStore.getId());
                yunStoreLabel.setLabelId(storeLabelId);
                list1.add(yunStoreLabel);
            }
            if (list1.size() > 0)
            {
                yunStorelabelMapper.batchStoreLabel(list1);
            }
        }
    }


//    /**
//     * 订单支付网页pc支付
//     *
//     * @param yunOrder 订单
//     * @return 结果
//     */
//    @Override
//    public String toPayAsPc(YunStore yunStore) throws Exception{
//        YunStore yunStore= yunStoreMapper.selectYunStoreById(yunOrder.getStoreId());
//        YunAlipayConfig yunAlipayConfig=new YunAlipayConfig();
//        yunAlipayConfig.setCreateById(yunStore.getCreateById());
//        List<YunAlipayConfig> yunAlipayConfigs= yunAlipayConfigMapper.selectYunAlipayConfigList(yunAlipayConfig);
//        if(yunAlipayConfigs!=null){
//            yunAlipayConfig=yunAlipayConfigs.get(0);
//        }
//
//        //https://openasyncapi.alipay.com/gateway.do  (收单资金结算到银行账户，结算成功的异步通知)
//        //https://openapi.alipaydev.com/gateway.do  (支付指定支付宝账户)
//        AlipayClient alipayClient = new DefaultAlipayClient(yunAlipayConfig.getGatewayUrl(),
//                yunAlipayConfig.getAppId(),
//                yunAlipayConfig.getPrivateKey(),
//                yunAlipayConfig.getFormat(),
//                yunAlipayConfig.getCharset(),
//                yunAlipayConfig.getPublicKey(),
//                yunAlipayConfig.getSignType());
//        // 创建API对应的request(电脑网页版)
//        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
//        //订单完成后返回的页面和异步通知地址
//        request.setReturnUrl(yunAlipayConfig.getReturnUrl());
//        request.setNotifyUrl(yunAlipayConfig.getNotifyUrl());
//        // 填充订单参数
//        String order=yunOrder.getNum();//订单号
//        String totalAmount=String.valueOf(yunOrder.getTotalMoney());//支付金额
//        String subject=yunOrder.getCourseName();//商品名称
//        String body=yunOrder.getRemark();//商品描述
//        request.setBizContent("{" +
//                "    \"order_id\":\""+yunOrder.getId()+"\"," +
//                "    \"out_trade_no\":\""+order+"\"," +
//                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
//                "    \"total_amount\":"+totalAmount+"," +
//                "    \"subject\":\""+subject+"\"," +
//                "    \"body\":\""+body+"\"," +
//                "    \"extend_params\":{" +
//                "    \"sys_service_provider_id\":\""+yunAlipayConfig.getSysServiceProviderId()+"\"" +
//                "    }"+
//                "  }");//填充业务参数
//        // 调用SDK生成表单, 通过GET方式，口可以获取url
//        System.out.println(alipayClient.pageExecute(request, "GET").getBody());
//        return alipayClient.pageExecute(request, "GET").getBody();
//    }
//
//
//    @Override
//    public String synchronous(HttpServletRequest request) {
//
//        Map<String, String> parameters = new HashMap<>();
//        Map<String, String[]> requestParams = request.getParameterMap();
//        System.out.println("支付宝同步参数"+requestParams);
//        for (Map.Entry<String, String[]> entry : requestParams.entrySet()) {
//            String key = entry.getKey();
//            String[] values = entry.getValue();
//            String valueStr = "";
//            for (int i = 0; i < values.length; i++) {
//                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
//            }
//            parameters.put(key, valueStr);
//        }
//
//        //调用SDK验证签名
//        String out_trade_no = request.getParameter("out_trade_no");//订单号
//        YunOrder yunOrder= yunOrderMapper.selectYunOrderByNum(out_trade_no);
//        YunStore yunStore= yunStoreMapper.selectYunStoreById(yunOrder.getStoreId());
//        YunAlipayConfig yunAlipayConfig=new YunAlipayConfig();
//        yunAlipayConfig.setCreateById(yunStore.getCreateById());
//        List<YunAlipayConfig> yunAlipayConfigs= yunAlipayConfigMapper.selectYunAlipayConfigList(yunAlipayConfig);
//        if(yunAlipayConfigs!=null){
//            yunAlipayConfig=yunAlipayConfigs.get(0);
//        }
//        boolean signVerified = false;//验证签名
//        try {
//            signVerified = AlipaySignature.rsaCheckV1(parameters, yunAlipayConfig.getPublicKey(), yunAlipayConfig.getCharset(), yunAlipayConfig.getSignType());
//        } catch (AlipayApiException e) {
//            e.printStackTrace();
//        }
//        ModelAndView mv = new ModelAndView("alipaySuccess");
//        //——请在这里编写您的程序（以下代码仅作参考）——
//        if(signVerified) {
//            //付款金额
//            String total_amount = new String(request.getParameter("total_amount"));
//            // 修改订单状态为支付成功，已付款; 同时新增支付流水
//            yunOrder.setPayWay("1");//支付方式支付宝支付
//            yunOrder.setStatus("2");//已支付状态
//            yunOrder.setTotalMoney(new BigDecimal(total_amount));
//            yunOrderMapper.updateYunOrder(yunOrder);
//            System.out.println("支付, 验签成功...");
//            return "success";
//        }else {
//            System.out.println("支付, 验签失败...");
//            return "fail";
//        }
//    }
//
//    @Override
//    public void notify(HttpServletRequest request, HttpServletResponse response) {
//        //接收参数进行校验
//        Map<String, String> parameters = new HashMap<>();
//        Map<String, String[]> requestParams = request.getParameterMap();
//        for (Map.Entry<String, String[]> entry : requestParams.entrySet()) {
//            String key = entry.getKey();
//            String[] values = entry.getValue();
//            String valueStr = "";
//            for (int i = 0; i < values.length; i++) {
//                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
//            }
//            parameters.put(key, valueStr);
//        }
//        System.out.println("parameters is [parameters={}]"
//                +parameters);
//        String appId = request.getParameter("app_id");//appid
//        String merchantOrderNo = request.getParameter("out_trade_no");//商户订单号
//
//        String orderId = request.getParameter("order_id");//orderId
//        if (orderId == null) {
//            System.out.println("orderId is null");
//        }
//        System.out.println("orderId: {}"+ orderId);
//        String payState = request.getParameter("trade_status");//交易状态
//        String encodeOrderNum = null;
////        cashLogMapper.add(request.getParameter("out_trade_no"), "NOTIFY", JSON.toJSONString(parameters), new Date());
//        try {
////            encodeOrderNum = URLDecoder.decode(request.getParameter("passback_params"), "UTF-8");
////            log.info("encodeOrderNum is [encodeOrderNum={}]", encodeOrderNum);
//            YunOrder yunOrder= yunOrderMapper.selectYunOrderById(Long.valueOf(orderId));
//            YunStore yunStore= yunStoreMapper.selectYunStoreById(yunOrder.getStoreId());
//            YunAlipayConfig yunAlipayConfig=new YunAlipayConfig();
//            yunAlipayConfig.setCreateById(yunStore.getCreateById());
//            List<YunAlipayConfig> yunAlipayConfigs= yunAlipayConfigMapper.selectYunAlipayConfigList(yunAlipayConfig);
//            if(yunAlipayConfigs!=null){
//                yunAlipayConfig=yunAlipayConfigs.get(0);
//            }
//            boolean signVerified;
//            signVerified = AlipaySignature.rsaCheckV1(parameters, yunAlipayConfig.getPublicKey(), yunAlipayConfig.getCharset(), yunAlipayConfig.getSignType());//验证签名
//            System.out.println("signVerified is [signVerified={}]"+signVerified);
//            if (signVerified) { //通过验证
//                System.out.println("payState: {}"+ payState);
//                if (payState.equals(TRADE_SUCCESS)) {
//                    //判断订单号与插入的订单号是否一样
//                    if (merchantOrderNo.equals(encodeOrderNum) == false || yunAlipayConfig.getAppId().equals(appId) == false) {
//                        System.out.println("vali failure");
////                        cashMapper.update(merchantOrderNo, 4);
////                        response.getOutputStream().print("failure");
//                        return;
//                    }
////                    cashMapper.update(merchantOrderNo, 3);
////                    orderMapper.afterPay(orderId);
////                    response.getOutputStream().print("success");
//                    return;
//                } else if (payState.equals(TRADE_CLOSED)) { //交易关闭
////                    cashMapper.update(merchantOrderNo, 7);
//                } else {
////                    cashMapper.update(merchantOrderNo, 4);
////                    response.getOutputStream().print("failure");
//                    return;
//                }
//            } else {
//                //签名校验失败更状态
////                cashMapper.update(merchantOrderNo, 4);
////                response.getOutputStream().print("failure");
//                return;
//            }
//            return;
//        } catch (AlipayApiException e) {
////            log.error(e.getErrMsg());
////            log.error(e.getErrMsg());
//            throw new RuntimeException("调用支付宝接口发生异常");
//        }
//
//    }
}
