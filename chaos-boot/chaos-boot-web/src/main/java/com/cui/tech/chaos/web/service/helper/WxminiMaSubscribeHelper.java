package com.cui.tech.chaos.web.service.helper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cui.tech.chaos.model.wx.WxminiMessage;
import com.cui.tech.chaos.model.wx.WxminiTemplateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信小程序长期订阅
 * @author G.G
 * @date 2020/2/25 19:32
 */
@Component
public class WxminiMaSubscribeHelper {
    @Value("${app.wxmini.appid:}")
    private String appid;
    @Value("${app.wxmini.secret:}")
    private String secret;
    @Autowired
    private RestTemplate restTemplate;

    public String push(String openid, String templateId, Map<String, WxminiTemplateData> m) {
        String url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + getAccessToken();
        //拼接推送的模版
        WxminiMessage wxminiMessage = new WxminiMessage();
        wxminiMessage.setTouser(openid);//用户的openid（要发送给那个用户，通常这里应该动态传进来的）
        wxminiMessage.setTemplate_id(templateId);//订阅消息模板id
        wxminiMessage.setPage("pages/index/index");
        wxminiMessage.setData(m);
        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(url, wxminiMessage, String.class);
        return responseEntity.getBody();
    }

    public String getAccessToken() {
        Map<String, String> params = new HashMap<>();
        params.put("APPID", appid);  //
        params.put("APPSECRET", secret);  //
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
