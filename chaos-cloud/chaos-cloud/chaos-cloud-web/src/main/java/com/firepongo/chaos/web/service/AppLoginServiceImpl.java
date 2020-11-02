package com.firepongo.chaos.web.service;

import com.firepongo.chaos.web.service.helper.JWTHelper;
import com.firepongo.chaos.app.login.AppLoginDto;
import com.firepongo.chaos.app.login.AppLoginUser;
import com.firepongo.chaos.app.login.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public abstract class AppLoginServiceImpl extends BaseLoginServiceImpl {
    @Autowired
    private AppLoginKeyService loginKeyService;

    public abstract AppLoginUser getUserInfo(AppLoginDto loginDto);

    @Autowired
    private JWTHelper jwtHelper;

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
            return null;
        } else {
            afterLogin(user);
        }
        user.setIp(loginDto.getIp());
        user.setLoginTime(new Date());
        String token = jwtHelper.createToken(user.getMu());
        user.setToken(token);
        redisHelper.set(loginKeyService.key(user.getToken()), user, 30 * 24 * 60 * 60);
        return user;
    }

    @Override
    public String key(String token) {
        return loginKeyService.key(token);
    }

    @Override
    public String getRedisLoginUser() {
        return loginKeyService.REDIS_LOGIN_USER;
    }
}
