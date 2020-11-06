package com.firepongo.chaos.web.controller;


import com.firepongo.chaos.app.limit.LimitDto;
import com.firepongo.chaos.app.login.LoginUser;
import com.firepongo.chaos.app.login.manage.ManageLoginDto;
import com.firepongo.chaos.app.login.manage.ManageLoginUser;
import com.firepongo.chaos.app.result.data.DataResult;
import com.firepongo.chaos.web.base.BaseController;
import com.firepongo.chaos.web.helper.IpUtil;
import com.firepongo.chaos.web.service.AccessLimitService;
import com.firepongo.chaos.web.service.login.ILoginService;
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
@RequestMapping("/cache")
@Api(tags = "RedisDataController")
public class RedisDataController extends BaseController {

    @Autowired
    AccessLimitService accessLimitService;

    @PostMapping("/loginAdmins")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<List<LoginUser>> loginAdmins() {
        return dataResult(null);
    }

    @PostMapping("/loginUsers")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<List<LoginUser>> loginUsers() {
        return dataResult(null);
    }

    @PostMapping("/logoutAll")
    @ApiOperation(value = "登出", notes = "", httpMethod = "POST")
    public DataResult<Boolean> logoutAll() {
        return dataResult(null);
    }

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
