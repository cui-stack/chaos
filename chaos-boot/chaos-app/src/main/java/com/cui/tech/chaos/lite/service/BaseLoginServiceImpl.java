package com.cui.tech.chaos.lite.service;

import com.cui.tech.chaos.lite.helper.RedisHelper;
import com.cui.tech.chaos.model.login.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author G.G
 * @date 2020/5/14 10:35
 */
public abstract class BaseLoginServiceImpl implements ILoginService {
    @Autowired
    protected RedisHelper redisHelper;

    @Override
    public boolean doLogout(String msg) {
        redisHelper.del(key(msg));
        return true;
    }

    @Override
    public List loginUsers() {
        Set keys = redisHelper.keys(getRedisLoginUser() + "*");
        ArrayList list = (ArrayList) keys.stream().map(key -> redisHelper.get((String) key)).collect(Collectors.toList());
        return list;
    }

    @Override
    public LoginUser getLoginUser(String msg) {
        if (StringUtils.isEmpty(msg)) {
            return null;
        }
        LoginUser user = (LoginUser) redisHelper.get(key(msg));
        if (user == null) {
            return null;
        }
        return user;
    }
}
