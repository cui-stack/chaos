package com.cui.tech.chaos.lite.controller;


import com.cui.tech.chaos.lite.service.ILoginService;
import com.cui.tech.chaos.model.login.AppLoginDto;
import com.cui.tech.chaos.model.login.AppLoginUser;
import com.cui.tech.chaos.model.login.WxMiniLoginDto;
import com.cui.tech.chaos.model.login.WxMiniLoginUser;
import com.cui.tech.chaos.model.result.DataResult;
import com.cui.tech.chaos.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/app")
@Api(tags = "AppLoginController", description = "登录模块")
public class AppLoginController extends BaseController {

    @Qualifier("appLoginService")
    @Autowired(required = false)
    ILoginService appLoginService;

    /**
     * @param user
     * @param bindingResult
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "登录", notes = "", httpMethod = "POST")
    public DataResult<AppLoginUser> login(@RequestBody AppLoginDto user, BindingResult bindingResult, HttpServletRequest request) {
        AppLoginUser su = (AppLoginUser) appLoginService.doLogin(user);
        return getResult(bindingResult, su);
    }

}
