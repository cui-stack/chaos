package com.firepongo.chaos.web.service.login;


import com.firepongo.chaos.app.login.LoginDto;
import com.firepongo.chaos.app.login.LoginUser;

public interface ILoginService {

    LoginUser doLogin(LoginDto loginDto);
//
//    boolean doLogout(String msg);
//
//    LoginUser getLoginUser(String msg);
//
//    default String refreshToken(String msg) {
//        return "";
//    }
//
//    List<LoginUser> loginUsers();
//
//    void afterLogin(LoginUser user);
//
//    default boolean doLogoutAll() {
//        return false;
//    }
//
//    String key(String msg);
//
//    String getRedisLoginUser();

}
