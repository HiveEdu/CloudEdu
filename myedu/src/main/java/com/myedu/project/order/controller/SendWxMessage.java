package com.myedu.project.order.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.myedu.project.order.domain.TemplateData;
import com.myedu.project.order.domain.WxMssVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SendWxMessage {
    /*
     * 发送订阅消息
     * */
    @GetMapping("/pushOneUser")
    public String pushOneUser() {
        return push("o3DoL0WEdzcJ20AVJg1crP96gbjM");
    }

    public String push(String openid) {
        RestTemplate restTemplate = new RestTemplate();
        //这里简单起见我们每次都获取最新的access_token（时间开发中，应该在access_token快过期时再重新获取）
        String url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + getAccessToken();
        //拼接推送的模版
        WxMssVo wxMssVo = new WxMssVo();
        wxMssVo.setOpenid(openid);//用户的openid（要发送给那个用户，通常这里应该动态传进来的）
        wxMssVo.setTemplate_id("CFeSWarQLMPyPjwmiy6AV4eB-IZcipu48V8bFLkBzTU");//订阅消息模板id
        wxMssVo.setPage("pages/index/index");

        Map<String, TemplateData> m = new HashMap<>(3);
        m.put("thing1", new TemplateData("小程序入门课程"));
        m.put("thing6", new TemplateData("杭州浙江大学"));
        m.put("thing7", new TemplateData("第一章第一节"));
        wxMssVo.setData(m);
        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(url, wxMssVo, String.class);
        return responseEntity.getBody();
    }


    @GetMapping("/getAccessToken")
    public String getAccessToken() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("APPID", "wx7c54942dfc87f4d8");  //
        params.put("APPSECRET", "5873a729c365b65ab42bb5fc82d2ed49");  //
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={APPID}&secret={APPSECRET}", String.class, params);
        String body = responseEntity.getBody();
        JSONObject object = JSON.parseObject(body);
        String Access_Token = object.getString("access_token");
        String expires_in = object.getString("expires_in");
        System.out.println("有效时长expires_in：" + expires_in);
        return Access_Token;
    }
}
