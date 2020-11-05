package com.firepongo.chaos.web.service.login.wxmini;

import com.firepongo.chaos.app.login.LoginUser;
import com.firepongo.chaos.app.login.wxmini.WxMiniLoginUser;
import com.firepongo.chaos.web.service.RedisService;
import com.firepongo.chaos.web.service.login.BaseLoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class WxminiLoginServiceImpl extends BaseLoginServiceImpl {

    @Autowired
    private WxminiLoginKeyService loginKeyService;
    @Autowired
    protected RedisService redisService;

    @Override
    protected void dealRedisToken(LoginUser loginUser) {
        WxMiniLoginUser user =(WxMiniLoginUser)loginUser;
        redisService.set(loginKeyService.key(user.getToken()), user, 8 * 60 * 60);
    }


}
