package com.cui.tech.chaos.lite.service;

import com.cui.tech.chaos.model.login.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public abstract class AppLoginServiceImpl extends BaseLoginServiceImpl {
    @Autowired
    private AppLoginKeyService loginKeyService;

    public abstract AppLoginUser getUserInfo(AppLoginDto loginDto);

    /**
     * 登录处理
     *
     * @param loginDto
     * @return
     */
    @Override
    public AppLoginUser doLogin(LoginDto loginDto) {
        AppLoginDto appLoginDto = (AppLoginDto) loginDto;
        AppLoginUser user = getUserInfo(appLoginDto);
        if (user == null) {
            user = initUser(appLoginDto);
        } else {
            afterLogin(user);
        }
        user.setIp(loginDto.getIp());
        user.setLoginTime(new Date());
        redisHelper.set(loginKeyService.key(user.getToken()), user, 8 * 60 * 60);
        return user;
    }

    protected abstract AppLoginUser initUser(AppLoginDto loginDto);

    @Override
    public String key(String token) {
        return loginKeyService.key(token);
    }

    @Override
    public String getRedisLoginUser() {
        return loginKeyService.REDIS_LOGIN_USER;
    }
}
