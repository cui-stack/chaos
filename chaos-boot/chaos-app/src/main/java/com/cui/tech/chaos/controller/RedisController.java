package com.cui.tech.chaos.controller;

import com.cui.tech.chaos.base.BaseController;
import com.cui.tech.chaos.model.login.AppLoginDto;
import com.cui.tech.chaos.model.login.AppLoginUser;
import com.cui.tech.chaos.model.redis.RedisSetDto;
import com.cui.tech.chaos.model.result.DataResult;
import com.cui.tech.chaos.service.helper.RedisHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author G.G
 * @date 2020/8/6 17:12
 */
@Slf4j
@RestController
@RequestMapping("/manage/data")
@Api(tags = "RedisController", description = "模块")
public class RedisController extends BaseController {
    @Autowired
    private RedisHelper redisHelper;

    @PostMapping("/get")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<Object> get(@RequestBody String key) {
        return getResult(redisHelper.get(key));
    }

    @PostMapping("/set")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<Boolean> set(@RequestBody RedisSetDto redisSetDto) {
        if (redisHelper.hasKey(redisSetDto.getKey())) {
            return getResult(redisHelper.set(redisSetDto.getKey(), redisSetDto.getValue(), redisSetDto.getTime()));
        } else {
            return getResult(false);
        }
    }
}
