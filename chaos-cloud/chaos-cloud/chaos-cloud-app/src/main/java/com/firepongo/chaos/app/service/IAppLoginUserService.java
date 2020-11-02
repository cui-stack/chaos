package com.firepongo.chaos.app.service;


import com.firepongo.chaos.app.login.AppLoginDto;
import com.firepongo.chaos.app.login.AppLoginUser;

/**
 * @author G.G
 * @date 2019/11/15 15:10
 */
public interface IAppLoginUserService {
    AppLoginUser selectByUsernameAndPassword(AppLoginDto loginDto);

}
