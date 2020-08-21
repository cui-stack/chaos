package com.cui.tech.chaos.web.service;

import com.cui.tech.chaos.model.login.LoginDto;
import com.cui.tech.chaos.model.login.LoginUser;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author G.G
 * @date 2020/8/11 14:25
 */
@Component
public class AppWxLoginServiceImpl extends BaseLoginServiceImpl {
    @Override
    public LoginUser doLogin(LoginDto loginDto) {
        return null;
    }

    @Override
    public void afterLogin(LoginUser user) {

    }

    @Override
    public String key(String msg) {
        return null;
    }

    @Override
    public String getRedisLoginUser() {
        return null;
    }

}
