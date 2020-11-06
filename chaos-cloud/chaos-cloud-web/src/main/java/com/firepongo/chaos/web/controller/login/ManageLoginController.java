package com.firepongo.chaos.web.controller.login;


import com.firepongo.chaos.app.login.manage.ManageLoginDto;
import com.firepongo.chaos.app.login.manage.ManageLoginUser;
import com.firepongo.chaos.app.result.ResultMsg;
import com.firepongo.chaos.web.annotation.ManageLoginToken;
import com.firepongo.chaos.web.base.BaseController;
import com.firepongo.chaos.web.helper.IpUtil;
import com.firepongo.chaos.web.service.AccessLimitService;
import com.firepongo.chaos.web.service.login.ILoginService;
import com.firepongo.chaos.app.login.LoginUser;
import com.firepongo.chaos.app.result.data.DataResult;
import com.firepongo.chaos.app.limit.LimitDto;
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
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/manage")
@Api(tags = "ManageLoginController")
public class ManageLoginController extends BaseController {

    @Qualifier("mnLoginService")
    @Autowired(required = false)
    private ILoginService mnLoginService;

    @Autowired
    private ManageLoginKeyService manageLoginKeyService;

    @PostMapping("/login")
    @ApiOperation(value = "登录", notes = "", httpMethod = "POST")
    public DataResult<ManageLoginUser> login(@RequestBody ManageLoginDto user, BindingResult bindingResult, HttpServletRequest request) {
        validate(bindingResult);
        if (mnLoginService == null) {
            return dataResult(null);
        }
        user.setIp(IpUtil.getIpAddr(request));
        return (DataResult<ManageLoginUser>) dataResult(mnLoginService.doLogin(user), new ResultMsg().setFailure("账号密码错误,登录失败!"));
    }

    @PostMapping("/logout")
    @ManageLoginToken
    @ApiOperation(value = "登出", notes = "", httpMethod = "POST")
    public DataResult<Boolean> logout(HttpServletRequest request) {
        if (mnLoginService == null) {
            return dataResult(false);
        }
        return dataResult(mnLoginService.doLogout(manageLoginKeyService.key(getMnLoginUserByToken(getToken(request)).getMu())));
    }

}
