package com.cui.tech.chaos.web.controller;

import com.cui.tech.chaos.model.login.ManageLoginUser;
import com.cui.tech.chaos.model.manage.ChaosAdminGetDto;
import com.cui.tech.chaos.model.manage.ManageRefreshSetDto;
import com.cui.tech.chaos.model.manage.RedisGetDto;
import com.cui.tech.chaos.web.base.BaseController;
import com.cui.tech.chaos.model.manage.RedisSetDto;
import com.cui.tech.chaos.model.result.DataResult;
import com.cui.tech.chaos.web.service.helper.RedisHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author G.G
 * @date 2020/8/6 17:12
 */
@Slf4j
@RestController
@RequestMapping("/manage/data")
@Api(tags = "RedisController", description = "模块")
public class ManageDataController extends BaseController {
    @Value("${app.manage.key:}")
    private String host;
    @Value("${app.manage.token:}")
    private String token;
    @Autowired
    private RedisHelper redisHelper;

    /**
     * 根据key获取信息
     * @param redisGetDto
     * @return
     */
    @PostMapping("/get")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<Object> get(@RequestBody RedisGetDto redisGetDto) {
        if (!redisGetDto.getToken().equals(token)) {
            return getResult(false);
        }
        return getResult(redisHelper.get(redisGetDto.getKey()));
    }

    @PostMapping("/set")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<Boolean> set(@RequestBody RedisSetDto redisSetDto) {
        if (!redisSetDto.getToken().equals(token)) {
            return getResult(false);
        }
        if (redisHelper.hasKey(redisSetDto.getKey())) {
            return getResult(redisHelper.set(redisSetDto.getKey(), redisSetDto.getValue(), redisSetDto.getTime()));
        } else {
            return getResult(false);
        }
    }
    @PostMapping("/refreshToken")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<Boolean> refreshToken(@RequestBody ManageRefreshSetDto setDto) {
        if (!setDto.getToken().equals(token)) {
            return getResult(false);
        }
        if (redisHelper.hasKey(setDto.getKey())) {
            return getResult(redisHelper.set(setDto.getKey(), setDto.getValue(), setDto.getTime()));
        } else {
            return getResult(false);
        }
    }

    /**
     * 根据token获取登录用户信息
     * @param data
     * @return
     */
    @PostMapping("/loginUser")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<ManageLoginUser> loginUser(@RequestBody ChaosAdminGetDto data) {
        if (!data.getToken().equals(token)) {
            return getResult(false);
        }
        return getResult(getMnLoginUserByToken(data.getUserMuToken()));
    }
}
