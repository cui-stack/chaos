package com.cui.tech.chaos.controller;


import com.cui.tech.chaos.annotation.ManageLoginToken;
import com.cui.tech.chaos.base.BaseController;
import com.cui.tech.chaos.service.AccessLimitService;
import com.cui.tech.chaos.service.ILoginService;
import com.cui.tech.chaos.model.login.LoginUser;
import com.cui.tech.chaos.model.login.ManageLoginDto;
import com.cui.tech.chaos.model.login.ManageLoginUser;
import com.cui.tech.chaos.model.result.DataResult;
import com.cui.tech.chaos.model.result.LimitDto;
import com.cui.tech.chaos.model.role.RoleConstant;
import com.cui.tech.chaos.util.addr.IpUtil;
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
@Api(tags = "ManageLoginController", description = "登录模块")
public class ManageLoginController extends BaseController {

    @Qualifier("mnLoginService")
    @Autowired(required = false)
    ILoginService mnLoginService;

    @Qualifier("wxLoginService")
    @Autowired(required = false)
    ILoginService wxLoginService;

    @Autowired
    private AccessLimitService accessLimitService;

    /**
     * @param user
     * @param bindingResult
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "登录", notes = "", httpMethod = "POST")
    public DataResult<ManageLoginUser> login(@RequestBody ManageLoginDto user, BindingResult bindingResult, HttpServletRequest request) {
        log.info("用户[{}]登录", user.getUsername());
        if (mnLoginService == null) {
            return getResult(false);
        }
        user.setIp(IpUtil.getIpAddr(request));
        return getResult(bindingResult, (ManageLoginUser) mnLoginService.doLogin(user), "账号密码错误,登录失败!");
    }

    @PostMapping("/logout")
    @ManageLoginToken
    @ApiOperation(value = "登出", notes = "", httpMethod = "POST")
    public DataResult<Boolean> logout(HttpServletRequest request) {
        log.info("用户[{}]登出");
        if (mnLoginService == null) {
            return getResult(false);
        }
        return getResult(mnLoginService.doLogout(getMnLoginMU(request)));
    }

    @PostMapping("/loginAdmins")
    @ManageLoginToken(roles = {RoleConstant.ADMIN})
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<List<LoginUser>> loginAdmins() {
        if (mnLoginService == null) {
            return getResult(false);
        }
        return getResult(mnLoginService.loginUsers());
    }

    @PostMapping("/loginUsers")
    @ManageLoginToken(roles = {RoleConstant.ADMIN})
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<List<LoginUser>> loginUsers() {
        if (wxLoginService == null) {
            return getResult(false);
        }
        return getResult(wxLoginService.loginUsers());
    }

    @PostMapping("/logoutAll")
    @ManageLoginToken(roles = {RoleConstant.ADMIN})
    @ApiOperation(value = "登出", notes = "", httpMethod = "POST")
    public DataResult<Boolean> logoutAll() {
        log.info("用户强制登出");
        if (mnLoginService == null) {
            return getResult(false);
        }
        return getResult(mnLoginService.doLogoutAll());
    }

    @PostMapping("/limit")
    @ManageLoginToken(roles = {RoleConstant.ADMIN})
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<ArrayList> limit() {
        return getResult(accessLimitService.limit());
    }

    @PostMapping("/release")
    @ManageLoginToken(roles = {RoleConstant.ADMIN})
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<Boolean> release(@RequestBody LimitDto limit) {
        return getResult(accessLimitService.release(limit));
    }

}
