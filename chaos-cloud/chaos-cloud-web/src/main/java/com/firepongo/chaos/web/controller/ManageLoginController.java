package com.firepongo.chaos.web.controller;


import com.firepongo.chaos.app.login.manage.ManageLoginDto;
import com.firepongo.chaos.app.login.manage.ManageLoginUser;
import com.firepongo.chaos.web.base.BaseController;
import com.firepongo.chaos.web.helper.IpUtil;
import com.firepongo.chaos.web.service.AccessLimitService;
import com.firepongo.chaos.web.service.login.ILoginService;
import com.firepongo.chaos.app.login.LoginUser;
import com.firepongo.chaos.app.result.data.DataResult;
import com.firepongo.chaos.app.limit.LimitDto;
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
        validate(bindingResult);
        if (mnLoginService == null) {
            return dataResult(false);
        }
        user.setIp(IpUtil.getIpAddr(request));
        return (DataResult<ManageLoginUser>) dataResult(mnLoginService.doLogin(user)).setMsg("账号密码错误,登录失败!");
    }
//
//    @PostMapping("/logout")
//    @ApiOperation(value = "登出", notes = "", httpMethod = "POST")
//    public DataResult<Boolean> logout(HttpServletRequest request) {
//        log.info("用户[{}]登出");
//        if (mnLoginService == null) {
//            return dataResult(false);
//        }
//        return dataResult(mnLoginService.doLogout(getMnLoginMU(request)));
//    }
//
//    @PostMapping("/loginAdmins")
//    @ApiOperation(value = "", notes = "", httpMethod = "POST")
//    public DataResult<List<LoginUser>> loginAdmins() {
//        if (mnLoginService == null) {
//            return dataResult(false);
//        }
//        return dataResult(mnLoginService.loginUsers());
//    }
//
//    @PostMapping("/loginUsers")
//    @ApiOperation(value = "", notes = "", httpMethod = "POST")
//    public DataResult<List<LoginUser>> loginUsers() {
//        if (wxLoginService == null) {
//            return dataResult(false);
//        }
//        return dataResult(wxLoginService.loginUsers());
//    }
//
//    @PostMapping("/logoutAll")
//    @ApiOperation(value = "登出", notes = "", httpMethod = "POST")
//    public DataResult<Boolean> logoutAll() {
//        log.info("用户强制登出");
//        if (mnLoginService == null) {
//            return dataResult(false);
//        }
//        return dataResult(mnLoginService.doLogoutAll());
//    }

    @PostMapping("/limit")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<ArrayList> limit() {
        return dataResult(accessLimitService.limit());
    }

    @PostMapping("/release")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<Boolean> release(@RequestBody LimitDto limit) {
        return dataResult(accessLimitService.release(limit));
    }

    @PostMapping("/lock")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<Boolean> lock(@RequestBody LimitDto limit) {
        return dataResult(accessLimitService.lock(limit));
    }

}
