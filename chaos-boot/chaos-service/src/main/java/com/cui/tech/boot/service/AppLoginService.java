package com.cui.tech.boot.service;

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
        AppLoginUser app = new AppLoginUser();
        app.setPhone("18866668888");
        app.setUsername("Golden");
        app.setToken("12345678");
        System.out.println(app);
        return app;
    }

    @Override
    public void afterLogin(LoginUser user) {

    }

    @Override
    protected AppLoginUser initUser(AppLoginDto loginDto) {
        return getUserInfo(loginDto);
    }

}
