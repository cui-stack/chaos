package com.firepongo.chaos.web.service.login;


import com.firepongo.chaos.app.login.LoginDto;
import com.firepongo.chaos.app.login.LoginUser;

public interface ILoginService {

    LoginUser doLogin(LoginDto loginDto);

    boolean doLogout(String data);

    String refreshToken(String data);

    LoginUser getLoginUser(String data);
}
