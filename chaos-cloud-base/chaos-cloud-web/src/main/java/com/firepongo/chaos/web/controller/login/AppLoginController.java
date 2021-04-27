package com.firepongo.chaos.web.controller.login;

import com.firepongo.chaos.app.login.app.AppLoginUser;
import com.firepongo.chaos.app.login.app.AppPhoneLoginDto;
import com.firepongo.chaos.app.result.data.DataResult;
import com.firepongo.chaos.web.annotation.AppLoginToken;
import com.firepongo.chaos.web.service.login.ILoginService;
import com.firepongo.chaos.web.base.BaseController;
import com.firepongo.chaos.web.service.login.app.AppLoginKeyService;
import com.firepongo.chaos.web.service.login.manage.ManageLoginKeyService;
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
    private ILoginService appLoginService;

    @Autowired
    private AppLoginKeyService appLoginKeyService;

    @PostMapping("/phone/login")
    @ApiOperation(value = "登录", notes = "", httpMethod = "POST")
    public DataResult<AppLoginUser> phoneLogin(@RequestBody AppPhoneLoginDto user, BindingResult bindingResult, HttpServletRequest request) {
        if (appLoginService == null) {
            return dataResult(null);
        }
        validate(bindingResult);
        AppLoginUser su = (AppLoginUser) appLoginService.doLogin(user);
        return dataResult(su);
    }

    @PostMapping("/logout")
    @ApiOperation(value = "登出", notes = "", httpMethod = "POST")
    @AppLoginToken
    public DataResult<Boolean> logout(HttpServletRequest request) {
        if (appLoginService == null) {
            return dataResult(false);
        }
        return dataResult(appLoginService.doLogout(appLoginKeyService.key(getAppLoginUserByToken(getToken(request)).getMu())));
    }

}
