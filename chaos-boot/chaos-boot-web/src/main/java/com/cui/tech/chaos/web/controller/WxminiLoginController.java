package com.cui.tech.chaos.web.controller;


import com.alibaba.fastjson.JSONObject;
import com.cui.tech.chaos.web.base.BaseController;
import com.cui.tech.chaos.model.service.ILoginService;
import com.cui.tech.chaos.model.login.WxMiniLoginDto;
import com.cui.tech.chaos.model.login.WxMiniLoginUser;
import com.cui.tech.chaos.model.result.DataResult;
import com.cui.tech.chaos.web.util.addr.IpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * @author G.G
 */
@Slf4j
@RestController
@RequestMapping("/wxmini")
@Api(tags = "WxminiLoginController", description = "登录模块")
public class WxminiLoginController extends BaseController {

    @Qualifier("wxLoginService")
    @Autowired(required = false)
    ILoginService wxLoginService;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${app.wxmini.appid:}")
    private String appid;
    @Value("${app.wxmini.secret:}")
    private String secret;

    /**
     * @param user
     * @param bindingResult
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "登录", notes = "", httpMethod = "POST")
    public DataResult<WxMiniLoginUser> login(@RequestBody WxMiniLoginDto user, BindingResult bindingResult, HttpServletRequest request) {
        log.info("用户[{}]登录", user.getCode());
        String ip = IpUtil.getIpAddr(request);
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + secret + "&js_code=" + user.getCode() + "&grant_type=authorization_code";
        String resp = restTemplate.getForObject(url, String.class);
        JSONObject json = JSONObject.parseObject(resp);
        String session_key = (String) json.get("session_key");
        String openid = (String) json.get("openid");
        String unionid = (String) json.get("unionid");
        user.setOpenid(openid);
        user.setIp(ip);
        user.setToken(session_key);
        user.setUnionid(unionid);
        WxMiniLoginUser su = (WxMiniLoginUser) wxLoginService.doLogin(user);
        return dataResult(su);
    }

}
