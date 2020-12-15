package com.firepongo.chaos.web.controller;


import com.firepongo.chaos.app.limit.LimitDto;
import com.firepongo.chaos.app.login.LoginUser;
import com.firepongo.chaos.app.login.manage.ManageLoginDto;
import com.firepongo.chaos.app.login.manage.ManageLoginUser;
import com.firepongo.chaos.app.result.data.DataResult;
import com.firepongo.chaos.web.base.BaseController;
import com.firepongo.chaos.web.helper.IpUtil;
import com.firepongo.chaos.web.service.AccessLimitService;
import com.firepongo.chaos.web.service.RedisService;
import com.firepongo.chaos.web.service.login.ILoginService;
import com.firepongo.chaos.web.service.login.manage.ManageLoginKeyService;
import com.firepongo.chaos.web.service.login.wxmini.WxminiLoginKeyService;
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
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/manage")
@Api(tags = "RedisDataController")
public class RedisDataController extends BaseController {

    @Autowired
    private AccessLimitService accessLimitService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private WxminiLoginKeyService wxminiLoginKeyService;
    @Autowired
    private ManageLoginKeyService manageLoginKeyService;

    @PostMapping("/loginAdmins")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<List<LoginUser>> loginAdmins() {
        Set set = redisService.keys(manageLoginKeyService.key("*"));
        List<ManageLoginUser> list = (List<ManageLoginUser>) set.stream().map((key) ->
                redisService.get((String) key)
        ).collect(Collectors.toList());
        return dataResult(list);
    }

    @PostMapping("/loginUsers")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<List<LoginUser>> loginUsers() {
        Set set = redisService.keys(wxminiLoginKeyService.key("*"));
        List<ManageLoginUser> list = (List<ManageLoginUser>) set.stream().map((key) ->
                redisService.get((String) key)
        ).collect(Collectors.toList());
        return dataResult(list);
    }

    @PostMapping("/logoutAll")
    @ApiOperation(value = "登出", notes = "", httpMethod = "POST")
    public DataResult<Boolean> logoutAll() {
        redisService.del(manageLoginKeyService.key("*"));
        return dataResult(true);
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
