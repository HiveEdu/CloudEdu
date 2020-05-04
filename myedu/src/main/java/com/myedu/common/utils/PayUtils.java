package com.myedu.common.utils;

import com.alibaba.druid.support.spring.stat.annotation.Stat;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.project.account.domain.YunAccountChange;
import com.myedu.project.account.enums.AccountChangeType;
import com.myedu.project.account.mapper.YunAccountChangeMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: ${梁少鹏}
 * Date: 2020/4/27
 * Time: 21:05
 * Description:
 */
public class PayUtils {
    /*
     * @Description :查询支付状态公用接口
     * @Author : 梁少鹏
     * @Date : 2020/4/27 21:11
     */
    public static Integer checkAlipay(AlipayClient alipayClient, String outTradeNo) {
        try {
            //实例化客户端（参数：网关地址、商户appid、商户私钥、格式、编码、支付宝公钥、加密类型）
            AlipayTradeQueryRequest alipayTradeQueryRequest = new AlipayTradeQueryRequest();
            alipayTradeQueryRequest.setBizContent("{" +
                    "\"out_trade_no\":\""+outTradeNo+"\"" +
                    "}");
            AlipayTradeQueryResponse alipayTradeQueryResponse = alipayClient.execute(alipayTradeQueryRequest);
            if(alipayTradeQueryResponse.isSuccess()){

                Integer status=1;
                switch (alipayTradeQueryResponse.getTradeStatus()) // 判断交易结果
                {
                    case "TRADE_FINISHED": // 交易结束并不可退款
                        status=5;
                        break;
                    case "TRADE_SUCCESS": // 交易支付成功
                        status=4;
                        break;
                    case "TRADE_CLOSED": // 未付款交易超时关闭或支付完成后全额退款
                        status=3;
                        break;
                    case "WAIT_BUYER_PAY": // 交易创建并等待买家付款
                        status=1;
                        break;
                    default:
                        break;
                }

                return status;
            } else {
                System.out.println("调用失败");
            }
        } catch (AlipayApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    public synchronized static AjaxResult alipayRefundRequest(AlipayClient alipayClient, String out_trade_no, String trade_no, double refund_amount) {
          String returnStr = null;
          String out_request_no=out_trade_no;//随机数  不是全额退款，部分退款使用该参数
          try {
            AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
            String batch_no=getRandomBatchNum();
            request.setBizContent("{" +
                                "\"out_trade_no\":\"" + out_trade_no + "\"," +
                                "\"trade_no\":\"" + trade_no + "\"," +
                               "\"refund_amount\":\"" + refund_amount + "\"," +
                                "\"out_request_no\":\"" + out_request_no+ "\"," +
                               "\"refund_reason\":\"正常退款\"" +
                                " }");
            AlipayTradeRefundResponse response;
            response = alipayClient.execute(request);
            if (response.isSuccess()) {
                System.out.println("支付宝退款成功");
                return AjaxResult.success(batch_no);
            } else {
                returnStr = response.getSubMsg();//失败会返回错误信息
                return AjaxResult.error(returnStr);
            }
          } catch (Exception e) {
            e.printStackTrace();
         }
            return AjaxResult.error();
         }


    /**
     * 生成支付宝退款批次号
     *
     * @return
     */
    public static String getRandomBatchNum() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String format = dateFormat.format(new Date());
        int max = 24;
        int min = 3;
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < s; i++) {
            Integer val = (int) (Math.random() * 9 + 1);
            buffer.append(val.toString());
        }
        return format + buffer.toString();
    }

  }
