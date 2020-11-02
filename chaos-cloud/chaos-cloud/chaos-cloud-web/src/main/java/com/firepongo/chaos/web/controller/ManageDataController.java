package com.firepongo.chaos.web.controller;

import com.firepongo.chaos.app.login.ManageLoginUser;
import com.firepongo.chaos.app.manage.ChaosAdminGetDto;
import com.firepongo.chaos.app.manage.ManageRefreshSetDto;
import com.firepongo.chaos.app.manage.RedisGetDto;
import com.firepongo.chaos.web.base.BaseController;
import com.firepongo.chaos.app.manage.RedisSetDto;
import com.firepongo.chaos.app.result.DataResult;
import com.firepongo.chaos.web.service.helper.RedisHelper;
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
            return dataResult(false);
        }
        return dataResult(redisHelper.get(redisGetDto.getKey()));
    }

    @PostMapping("/set")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<Boolean> set(@RequestBody RedisSetDto redisSetDto) {
        if (!redisSetDto.getToken().equals(token)) {
            return dataResult(false);
        }
        if (redisHelper.hasKey(redisSetDto.getKey())) {
            return dataResult(redisHelper.set(redisSetDto.getKey(), redisSetDto.getValue(), redisSetDto.getTime()));
        } else {
            return dataResult(false);
        }
    }
    @PostMapping("/refreshToken")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<Boolean> refreshToken(@RequestBody ManageRefreshSetDto setDto) {
        if (!setDto.getToken().equals(token)) {
            return dataResult(false);
        }
        if (redisHelper.hasKey(setDto.getKey())) {
            return dataResult(redisHelper.set(setDto.getKey(), setDto.getValue(), setDto.getTime()));
        } else {
            return dataResult(false);
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
            return dataResult(false);
        }
        return dataResult(getMnLoginUserByToken(data.getUserMuToken()));
    }
}
