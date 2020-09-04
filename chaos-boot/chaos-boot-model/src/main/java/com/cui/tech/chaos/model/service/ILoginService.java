package com.cui.tech.chaos.model.service;


import com.cui.tech.chaos.model.login.LoginDto;
import com.cui.tech.chaos.model.login.LoginUser;

import java.util.List;

public interface ILoginService {

    LoginUser doLogin(LoginDto loginDto);

    boolean doLogout(String msg);

    LoginUser getLoginUser(String msg);

    default String refreshToken(String msg) {
        return "";
    }

    List<LoginUser> loginUsers();

    void afterLogin(LoginUser user);

    default boolean doLogoutAll() {
        return false;
    }

    String key(String msg);

    String getRedisLoginUser();

}
