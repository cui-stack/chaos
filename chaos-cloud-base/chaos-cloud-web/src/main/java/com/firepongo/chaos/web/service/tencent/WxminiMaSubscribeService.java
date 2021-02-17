package com.firepongo.chaos.web.service.tencent;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.firepongo.chaos.app.exception.BusinessException;
import com.firepongo.chaos.app.tencent.weapp.WeappMessage;
import com.firepongo.chaos.app.tencent.weapp.WxminiTemplateData;
import com.firepongo.chaos.web.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信小程序长期订阅
 *
 * @author G.G
 * @date 2020/2/25 19:32
 */
@Component
public class WxminiMaSubscribeService {
    @Value("${app.wxmini.appid:}")
    private String appid;
    @Value("${app.wxmini.secret:}")
    private String secret;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RedisService redisService;

    public String push(String openid, String templateId, Map<String, WxminiTemplateData> m) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("token", (String) redisService.get("app_admin_token"));
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        requestHeaders.setContentType(type);
        requestHeaders.add("Accept", MediaType.APPLICATION_JSON.toString());

        String url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + getAccessToken();
        WeappMessage wxminiMessage = new WeappMessage();
        wxminiMessage.setTouser(openid);
        wxminiMessage.setTemplate_id(templateId);
        wxminiMessage.setPage("pages/index/index");
        wxminiMessage.setData(m);
        ObjectMapper om = new ObjectMapper();
        String json = "";
        try {
            json = om.writeValueAsString(wxminiMessage);
        } catch (JsonProcessingException je) {
            throw new BusinessException();
        }
        HttpEntity<String> requestEntity = new HttpEntity<>(json, requestHeaders);

        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(url, requestEntity, String.class);
        return responseEntity.getBody();
    }

    public String getAccessToken() {
        String accessToken = (String) redisService.get("app_wxmini_access_token");
        if (StringUtils.isEmpty(accessToken)) {
            Map<String, String> params = new HashMap<>(2);
            params.put("APPID", appid);
            params.put("APPSECRET", secret);
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                    "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={APPID}&secret={APPSECRET}", String.class, params);
            String body = responseEntity.getBody();
            JSONObject object = JSON.parseObject(body);
            accessToken = object.getString("access_token");
            long expiresIn = object.getLong("expires_in");
            redisService.set("app_wxmini_access_token", accessToken, expiresIn);
        }
        return accessToken;
    }

}
