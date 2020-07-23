package com.cui.tech.service.service;

import com.cui.tech.chaos.lite.service.AppLoginServiceImpl;
import com.cui.tech.chaos.model.login.AppLoginDto;
import com.cui.tech.chaos.model.login.AppLoginUser;
import com.cui.tech.chaos.model.login.LoginUser;
import org.springframework.stereotype.Component;

/**
 * @author G.G
 * @date 2019/11/14 15:45
 */
@Component("appLoginService")
public class AppLoginService extends AppLoginServiceImpl {

    @Override
    public AppLoginUser getUserInfo(AppLoginDto loginDto) {
        return new AppLoginUser();
    }

    @Override
    public void afterLogin(LoginUser user) {

    }

    @Override
    protected AppLoginUser initUser(AppLoginDto loginDto) {
        return getUserInfo(loginDto);
    }

}
