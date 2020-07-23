package com.cui.tech.boot.service;

import com.cui.tech.chaos.lite.service.WxminiLoginServiceImpl;
import com.cui.tech.chaos.model.login.LoginUser;
import com.cui.tech.chaos.model.login.WxMiniLoginDto;
import com.cui.tech.chaos.model.login.WxMiniLoginUser;

/**
 * @author G.G
 * @date 2019/11/14 15:45
 */
//@Component("wxLoginService")
public class WxminiLoginService extends WxminiLoginServiceImpl {

    @Override
    public WxMiniLoginUser getUserInfo(WxMiniLoginDto loginDto) {
        return new WxMiniLoginUser();
    }

    @Override
    public void afterLogin(LoginUser user) {

    }

    @Override
    protected WxMiniLoginUser initUser(WxMiniLoginDto loginDto) {
        return getUserInfo(loginDto);
    }

}
