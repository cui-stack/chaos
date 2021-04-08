package com.firepongo.chaos.web.controller.login;


import com.alibaba.fastjson.JSONObject;
import com.firepongo.chaos.web.helper.IpUtil;
import com.firepongo.chaos.web.base.BaseController;
import com.firepongo.chaos.web.service.login.ILoginService;
import com.firepongo.chaos.app.login.wxmini.WxMiniLoginDto;
import com.firepongo.chaos.app.login.wxmini.WxMiniLoginUser;
import com.firepongo.chaos.app.result.data.DataResult;
import com.firepongo.chaos.web.service.login.manage.ManageLoginKeyService;
import com.firepongo.chaos.web.service.login.wxmini.WxminiLoginKeyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
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
@Api(tags = "WxminiLoginController")
public class WxminiLoginController extends BaseController {

    @Qualifier("wxLoginService")
    @Autowired(required = false)
    private ILoginService wxLoginService;
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
        if (wxLoginService == null) {
            return dataResult(null);
        }
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid
                + "&secret=" + secret + "&js_code=" + user.getCode() + "&grant_type=authorization_code";
        String resp = restTemplate.getForObject(url, String.class);
        log.info("微信用户登录code【{}】,返回结果:{}", user.getCode(),resp);
        JSONObject json = JSONObject.parseObject(resp);
        user.setIp(IpUtil.getIpAddr(request));
        user.setOpenid((String) json.get("openid"));
        user.setToken((String) json.get("session_key"));
        user.setUnionid((String) json.get("unionid"));
        WxMiniLoginUser su = (WxMiniLoginUser) wxLoginService.doLogin(user);
        return dataResult(su);
    }

}
