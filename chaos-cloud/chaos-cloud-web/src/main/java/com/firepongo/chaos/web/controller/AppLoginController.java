package com.firepongo.chaos.web.controller;

import com.firepongo.chaos.app.login.app.AppLoginUser;
import com.firepongo.chaos.app.login.app.AppPhoneLoginDto;
import com.firepongo.chaos.app.result.data.DataResult;
import com.firepongo.chaos.web.service.login.ILoginService;
import com.firepongo.chaos.web.base.BaseController;
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
@Api(tags = "AppLoginController")
public class AppLoginController extends BaseController {

    @Qualifier("appLoginService")
    @Autowired(required = false)
    ILoginService appLoginService;

    @PostMapping("/phone/login")
    @ApiOperation(value = "登录", notes = "", httpMethod = "POST")
    public DataResult<AppLoginUser> phoneLogin(@RequestBody AppPhoneLoginDto user, BindingResult bindingResult, HttpServletRequest request) {
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
        //  return dataResult(appLoginService.doLogout(getMnLoginMU(request)));
        return null;
    }

}
