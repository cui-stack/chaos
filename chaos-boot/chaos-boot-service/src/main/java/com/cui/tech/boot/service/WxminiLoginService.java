package com.cui.tech.boot.service;

import com.cui.tech.chaos.model.login.LoginUser;
import com.cui.tech.chaos.model.login.WxMiniLoginDto;
import com.cui.tech.chaos.model.login.WxMiniLoginUser;
import com.cui.tech.chaos.service.WxminiLoginServiceImpl;
import org.springframework.stereotype.Component;

/**
 * @author G.G
 * @date 2019/11/14 15:45
 */
@Component("wxLoginService")
public class WxminiLoginService extends WxminiLoginServiceImpl {

    @Override
    public WxMiniLoginUser getUserInfo(WxMiniLoginDto loginDto) {
        WxMiniLoginUser user=  new WxMiniLoginUser();
        user.setUsername("golden");
        user.setToken("888");
        user.setPhone("18866668888");
        return user;
    }

    @Override
    public void afterLogin(LoginUser user) {

    }

    @Override
    protected WxMiniLoginUser initUser(WxMiniLoginDto loginDto) {
        return getUserInfo(loginDto);
    }

}
