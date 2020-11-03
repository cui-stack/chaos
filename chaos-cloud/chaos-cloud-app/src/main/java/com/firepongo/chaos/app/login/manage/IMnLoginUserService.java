package com.firepongo.chaos.app.login.manage;

/**
 * @author G.G
 * @date 2019/11/15 15:10
 */
public interface IMnLoginUserService {
    ManageLoginUser selectByUsernameAndPassword(ManageLoginDto loginDto);

}
