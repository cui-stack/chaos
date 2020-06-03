package com.cui.tech.chaos.lite.service;

import com.cui.tech.chaos.lite.exception.AuthenticationException;
import com.cui.tech.chaos.lite.helper.RedisHelper;
import com.cui.tech.chaos.model.result.LimitDto;
import com.cui.tech.chaos.model.result.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author G.G
 * @date 2020/5/28 15:58
 */
@Component
public class AccessLimitService {
    @Autowired
    private RedisHelper redisHelper;
    private String ACCESS_LIMIT = "access_limit_";
    private String ACCESS_TOTAL_LIMIT = "access_total_limit_";

    public void handleAccessLimit(String userMU, int seconds, int maxCount, HttpServletRequest httpServletRequest) {
        handle(userMU, seconds, maxCount, httpServletRequest, ACCESS_LIMIT);
    }

    public void handleAccessTotalLimit(String userMU, int seconds, int maxCount, HttpServletRequest httpServletRequest) {
        handle(userMU, seconds, maxCount, httpServletRequest, ACCESS_TOTAL_LIMIT);
    }

    private void handle(String userMU, int seconds, int maxCount, HttpServletRequest httpServletRequest, String access_total_limit) {
        Integer count = (Integer) redisHelper.get(access_total_limit + userMU + "_" + httpServletRequest.getRequestURI());
        if (count == null) {
            redisHelper.set(access_total_limit + userMU + "_" + httpServletRequest.getRequestURI(), 1, seconds);
        } else if (count < maxCount) {
            //加1
            redisHelper.incr(access_total_limit + userMU + "_" + httpServletRequest.getRequestURI(), 1);
        } else {
            //超出访问次数
            throw new AuthenticationException(ResultEnum.ACCESS_LIMIT, "您浏览得太快了", httpServletRequest);
        }
    }

    public ArrayList limit() {
        Set keys = redisHelper.keys(ACCESS_TOTAL_LIMIT + "*");
        ArrayList list = (ArrayList) keys.stream().map(key -> {
            String keyStr = (String) key;
            String[] data = keyStr.substring(19).split("_");
            return new LimitDto(data[0], data[1], (Integer) redisHelper.get(keyStr));
        }).collect(Collectors.toList());
        return list;
    }

    public boolean release(LimitDto limit) {
        redisHelper.del(ACCESS_TOTAL_LIMIT + limit.getMu() + "_" + limit.getUri());
        return true;
    }
}
