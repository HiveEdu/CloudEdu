package com.myedu.project.store.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.myedu.common.utils.DateUtils;

import com.myedu.common.utils.StringUtils;
import com.myedu.project.account.domain.YunAccountChange;
import com.myedu.project.account.domain.YunAlipayConfig;
import com.myedu.project.account.enums.AccountChangeType;
import com.myedu.project.account.mapper.YunAccountChangeMapper;
import com.myedu.project.account.mapper.YunAlipayConfigMapper;
import com.myedu.project.dataBasic.domain.SysMemberLevel;
import com.myedu.project.dataBasic.enums.LeaveType;
import com.myedu.project.dataBasic.mapper.SysMemberLevelMapper;

import com.myedu.project.order.domain.vo.YunOrderVo;
import com.myedu.project.order.enums.PaymentType;
import com.myedu.project.store.domain.YunStore;
import com.myedu.project.store.domain.YunStoreLabel;
import com.myedu.project.store.domain.YunStoreType;
import com.myedu.project.store.domain.vo.YunStoreVo;
import com.myedu.project.store.mapper.YunStoreLabelMapper;
import com.myedu.project.store.mapper.YunStoreMapper;
import com.myedu.project.store.mapper.YunStoreTypeMapper;
import com.myedu.project.store.service.IYunStoreService;
import com.myedu.project.store.storeSearch.entityVo.StoreSearchVo;
import com.myedu.project.store.storeSearch.reponsitory.StoreSearchVoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
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
    @Autowired
    private YunAlipayConfigMapper yunAlipayConfigMapper;
    @Autowired
    private YunAccountChangeMapper yunAccountChangeMapper;
    @Autowired
    private SysMemberLevelMapper sysMemberLevelMapper;
    @Autowired
    private StoreSearchVoRepository storeSearchVoRepository;
    public static final String TRADE_SUCCESS = "TRADE_SUCCESS"; //支付成功标识
    public static final String TRADE_CLOSED = "TRADE_CLOSED";//交易关闭
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

        //增加门店到es查询数据库中
        StoreSearchVo storeSearchVo=new StoreSearchVo();
        storeSearchVo.setId(yunStore.getId());
        storeSearchVo.setName(yunStore.getName());
        storeSearchVo.setLon(yunStore.getMapX());
        storeSearchVo.setLat(yunStore.getMapY());
        GeoPoint location=new GeoPoint(storeSearchVo.getLat(),storeSearchVo.getLon());
        storeSearchVo.setLocation(location);
        storeSearchVo.setAddress(yunStore.getAddress());
        storeSearchVo.setArea(yunStore.getArea());
        storeSearchVo.setBeginExperienceTime(yunStore.getBeginExperienceTime());
        storeSearchVo.setEndExperienceTime(yunStore.getEndExperienceTime());
        storeSearchVo.setCity(yunStore.getCity());
        storeSearchVo.setCreateById(yunStore.getCreateById());
        storeSearchVo.setHealths(yunStore.getHealths());
        storeSearchVo.setPhotos(yunStore.getPhotos());
        storeSearchVo.setLogo(yunStore.getLogo());
        storeSearchVo.setStatus(yunStore.getStatus());
        storeSearchVo.setManager(yunStore.getManager());
        storeSearchVo.setProvince(yunStore.getProvince());
        storeSearchVo.setManagerPhone(yunStore.getManagerPhone());
        storeSearchVo.setVideo(yunStore.getVideo());
        storeSearchVo.setRejectResion(yunStore.getRejectResion());
        storeSearchVo.setVipLevelId(yunStore.getVipLevelId());
        List<YunStoreType>  yunStoreTypes=yunStoreTypeMapper.selectYunStoreTypeByStoreId(yunStore.getId());
        List<Long> typeIs=new ArrayList<>();
        for (YunStoreType yun:
                yunStoreTypes) {
            typeIs.add(yun.getTypeId());
        }
        storeSearchVo.setTypeIds(typeIs);
        storeSearchVoRepository.save(storeSearchVo);
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
        //从es中删除门店信息
        storeSearchVoRepository.deleteById(storeId);
        //增加门店到es查询数据库中
        StoreSearchVo storeSearchVo=new StoreSearchVo();
        storeSearchVo.setId(yunStore.getId());
        storeSearchVo.setName(yunStore.getName());
        storeSearchVo.setLon(yunStore.getMapX());
        storeSearchVo.setLat(yunStore.getMapY());
        GeoPoint location=new GeoPoint(storeSearchVo.getLat(),storeSearchVo.getLon());
        storeSearchVo.setLocation(location);
        storeSearchVo.setAddress(yunStore.getAddress());
        storeSearchVo.setArea(yunStore.getArea());
        storeSearchVo.setBeginExperienceTime(yunStore.getBeginExperienceTime());
        storeSearchVo.setEndExperienceTime(yunStore.getEndExperienceTime());
        storeSearchVo.setCity(yunStore.getCity());
        storeSearchVo.setCreateById(yunStore.getCreateById());
        storeSearchVo.setHealths(yunStore.getHealths());
        storeSearchVo.setPhotos(yunStore.getPhotos());
        storeSearchVo.setLogo(yunStore.getLogo());
        storeSearchVo.setStatus(yunStore.getStatus());
        storeSearchVo.setManager(yunStore.getManager());
        storeSearchVo.setProvince(yunStore.getProvince());
        storeSearchVo.setManagerPhone(yunStore.getManagerPhone());
        storeSearchVo.setVideo(yunStore.getVideo());
        storeSearchVo.setRejectResion(yunStore.getRejectResion());
        storeSearchVo.setVipLevelId(yunStore.getVipLevelId());
        storeSearchVoRepository.save(storeSearchVo);
        List<YunStoreType>  yunStoreTypes=yunStoreTypeMapper.selectYunStoreTypeByStoreId(yunStore.getId());
        List<Long> typeIs=new ArrayList<>();
        for (YunStoreType yun:
                yunStoreTypes) {
            typeIs.add(yun.getTypeId());
        }
        storeSearchVo.setTypeIds(typeIs);
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
            //从es中删除门店信息
            storeSearchVoRepository.deleteById(id);
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
        //从es中删除门店信息
        storeSearchVoRepository.deleteById(id);
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

    /**
     * 门店充值
     *
     * @param yunStore 订单
     * @return 结果
     */
    @Override
    public String toPayAsPc(YunStore yunStore, BigDecimal totalmoney) throws Exception{
        yunStore=yunStoreMapper.selectYunStoreById(yunStore.getId());
        YunAlipayConfig yunAlipayConfig=new YunAlipayConfig();
        yunAlipayConfig.setPayMentType(PaymentType.storetopup.getCode());
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
        // 创建API对应的request(电脑网页版)
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        //订单完成后返回的页面和异步通知地址
        request.setReturnUrl(yunAlipayConfig.getReturnUrl());
        request.setNotifyUrl(yunAlipayConfig.getNotifyUrl());
        // 填充订单参数
        String order="Num"+yunStore.getId()+"-"+System.currentTimeMillis();//订单号门店id+时间戳
        String totalAmount=String.valueOf(totalmoney);//支付金额
        String subject=yunStore.getName()+"门店充值";//商品名称
        String body=yunStore.getName()+"门店充值";//商品描述
        String orderId="store-top-up"+yunStore.getId();
        request.setBizContent("{" +
                "    \"order_id\":\""+orderId+"\"," +
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
    public String toPayAsWeb(YunStore yunStore  , BigDecimal totalmoney) throws Exception{
        yunStore=yunStoreMapper.selectYunStoreById(yunStore.getId());
        YunAlipayConfig yunAlipayConfig=new YunAlipayConfig();
        yunAlipayConfig.setPayMentType(PaymentType.storetopup.getCode());
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
        // 创建API对应的request(手机网页版)
        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
        //订单完成后返回的页面和异步通知地址
        request.setReturnUrl(yunAlipayConfig.getReturnUrl());
        request.setNotifyUrl(yunAlipayConfig.getNotifyUrl());
        // 填充订单参数
        String order="Num"+yunStore.getId()+"-"+System.currentTimeMillis();//订单号门店id+时间戳
        String totalAmount=String.valueOf(totalmoney);//支付金额
        String subject=yunStore.getName()+"门店充值";//商品名称
        String body=yunStore.getName()+"门店充值";//商品描述
        String orderId="store-top-up"+yunStore.getId();
        request.setBizContent("{" +
                "    \"order_id\":\""+orderId+"\"," +
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



    public static void main(String[] args) {
        String order="Num23-"+System.currentTimeMillis();//订单号门店id+时间戳
        String ee= order.substring(3,order.indexOf("-"));
        System.out.println(ee);
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
        String order_id = request.getParameter("out_trade_no");//订单号
        System.out.println(order_id.substring(3,order_id.indexOf("-")));
        YunStore yunStore= yunStoreMapper.selectYunStoreById(Long.valueOf(order_id.substring(3,order_id.indexOf("-"))));
        YunAlipayConfig yunAlipayConfig=new YunAlipayConfig();
        yunAlipayConfig.setPayMentType(PaymentType.storetopup.getCode());
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
            //充值成功增加充值记录
            YunAccountChange yunAccountChange=new YunAccountChange();
            yunAccountChange.setUserId(yunStore.getCreateById());
            yunAccountChange.setCashAmount(new BigDecimal(total_amount));
            yunAccountChange.setUncashAmount(new BigDecimal(0));
            yunAccountChange.setChangeType(AccountChangeType.STORERECHARGE.getCode());
            yunAccountChange.setCreateById(yunStore.getCreateById());
            yunAccountChange.setCreateBy(yunStore.getCreateBy());
            yunAccountChange.setCreateTime(DateUtils.getNowDate());
            yunAccountChangeMapper.insertYunAccountChange(yunAccountChange);
            //根据累计充值金额设置门店vip等级
            YunAccountChange yunAccountChange1=new YunAccountChange();
            yunAccountChange1.setUserId(yunAccountChange.getUserId());
            yunAccountChange1.setChangeType(yunAccountChange.getChangeType());
            List<YunAccountChange>  yunAccountChanges=yunAccountChangeMapper.selectYunAccountChangeList(yunAccountChange1);
            BigDecimal sum=new BigDecimal(0);
            for (YunAccountChange yunAccountChange2:yunAccountChanges) {
                sum=sum.add(yunAccountChange2.getCashAmount());
            }
            SysMemberLevel sysMemberLevel=new SysMemberLevel();
            sysMemberLevel.setType(LeaveType.STOREVIP.getCode());
            List<SysMemberLevel> sysMemberLevels=sysMemberLevelMapper.selectSysMemberLevelList(sysMemberLevel);
            for (SysMemberLevel sysMemberLevel1:sysMemberLevels) {
                if(sum.compareTo(sysMemberLevel1.getRule())==1) {
                    yunStore.setVipLevelId(sysMemberLevel1.getId());
                }
            }
            yunStoreMapper.updateYunStore(yunStore);
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
        String payState = request.getParameter("trade_status");//交易状态
        String encodeOrderNum = null;
        try {
            YunAlipayConfig yunAlipayConfig=new YunAlipayConfig();
            yunAlipayConfig.setPayMentType(PaymentType.storetopup.getCode());
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
            return;
        } catch (AlipayApiException e) {
//            log.error(e.getErrMsg());
//            log.error(e.getErrMsg());
            throw new RuntimeException("调用支付宝接口发生异常");
        }

    }
}
