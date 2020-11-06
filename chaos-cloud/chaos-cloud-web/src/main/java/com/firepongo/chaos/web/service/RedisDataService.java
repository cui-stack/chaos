package com.firepongo.chaos.web.service;

import com.firepongo.chaos.app.login.LoginUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author G.G
 * @date 2020/11/5 21:31
 */
@Component
public class RedisDataService {
    @Autowired
    private RedisService redisService;

//    public List loginUsers() {
//        Set keys = redisService.keys(getRedisLoginUser() + "*");
//        ArrayList list = (ArrayList) keys.stream().map(key -> redisService.get((String) key)).collect(Collectors.toList());
//        return list;
//    }
//
//    public LoginUser getLoginUser(String msg) {
//        if (StringUtils.isEmpty(msg)) {
//            return null;
//        }
//        return (LoginUser) redisService.get(key(msg));
//    }
}
