package com.cui.tech.chaos.web.controller;

import com.cui.tech.chaos.model.login.*;
import com.cui.tech.chaos.model.result.ResultMsg;
import com.cui.tech.chaos.web.base.BaseController;
import com.cui.tech.chaos.model.service.ILoginService;
import com.cui.tech.chaos.model.result.DataResult;
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
        validate(bindingResult);
        AppLoginUser su = (AppLoginUser) appLoginService.doLogin(user);
        return dataResult(su,new ResultMsg().setFailure("用户不存在"));
    }

    @PostMapping("/phone/login")
    @ApiOperation(value = "登录", notes = "", httpMethod = "POST")
    public DataResult<AppLoginUser> phoneLogin(@RequestBody AppPhoneLoginDto user, BindingResult bindingResult, HttpServletRequest request) {
        validate(bindingResult);
        AppLoginUser su = (AppLoginUser) appLoginService.doLogin(user);
        return dataResult(su);
    }

    @PostMapping("/wx/login")
    @ApiOperation(value = "登录", notes = "", httpMethod = "POST")
    public DataResult<AppLoginUser> wxLogin(@RequestBody AppCodeLoginDto user, BindingResult bindingResult, HttpServletRequest request) {
        validate(bindingResult);
        AppLoginUser su = (AppLoginUser) appLoginService.doLogin(user);
        return dataResult(su);
    }

    @PostMapping("/hw/login")
    @ApiOperation(value = "登录", notes = "", httpMethod = "POST")
    public DataResult<AppLoginUser> hwLogin(@RequestBody AppCodeLoginDto user, BindingResult bindingResult, HttpServletRequest request) {
        validate(bindingResult);
        AppLoginUser su = (AppLoginUser) appLoginService.doLogin(user);
        return dataResult(su);
    }

    @PostMapping("/logout")
    @ApiOperation(value = "登出", notes = "", httpMethod = "POST")
    public DataResult<Boolean> logout(HttpServletRequest request) {
        log.info("用户[{}]登出");
        if (appLoginService == null) {
            return dataResult(false);
        }
        return dataResult(appLoginService.doLogout(getMnLoginMU(request)));
    }

}
