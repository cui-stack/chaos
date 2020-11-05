package com.firepongo.chaos.web.service.login.app;


import com.firepongo.chaos.app.login.app.AppLoginDto;
import com.firepongo.chaos.app.login.app.AppLoginUser;

/**
 * @author G.G
 * @date 2019/11/15 15:10
 */
public interface IAppLoginUserService {
    AppLoginUser selectByUsernameAndPassword(AppLoginDto loginDto);

}
