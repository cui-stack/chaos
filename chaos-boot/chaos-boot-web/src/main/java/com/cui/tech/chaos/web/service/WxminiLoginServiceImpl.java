package com.cui.tech.chaos.web.service;

import com.cui.tech.chaos.model.login.LoginDto;
import com.cui.tech.chaos.model.login.WxMiniLoginDto;
import com.cui.tech.chaos.model.login.WxMiniLoginUser;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public abstract class WxminiLoginServiceImpl extends BaseLoginServiceImpl {

    @Autowired
    private WxminiLoginKeyService loginKeyService;

    public abstract WxMiniLoginUser getUserInfo(WxMiniLoginDto loginDto);

    /**
     * 登录处理
     *
     * @param loginDto
     * @return
     */
    @Override
    public WxMiniLoginUser doLogin(LoginDto loginDto) {
        WxMiniLoginDto wxMiniLoginDto = (WxMiniLoginDto) loginDto;
        WxMiniLoginUser user = getUserInfo(wxMiniLoginDto);
        if (user == null) {
            user = initUser(wxMiniLoginDto);
        } else {
            afterLogin(user);
        }
        user.setIp(loginDto.getIp());
        user.setLoginTime(new Date());
        user.setToken(wxMiniLoginDto.getToken());
        redisHelper.set(loginKeyService.key(user.getToken()), user, 8 * 60 * 60);
        return user;
    }

    protected abstract WxMiniLoginUser initUser(WxMiniLoginDto loginDto);

    @Override
    public String key(String token) {
        return loginKeyService.key(token);
    }

    @Override
    public String getRedisLoginUser() {
        return loginKeyService.REDIS_LOGIN_USER;
    }
}
