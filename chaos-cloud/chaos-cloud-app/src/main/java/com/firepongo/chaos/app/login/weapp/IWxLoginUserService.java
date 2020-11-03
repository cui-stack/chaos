package com.firepongo.chaos.app.login.weapp;


/**
 * @author G.G
 * @date 2019/11/15 15:10
 */
public interface IWxLoginUserService {

    WxMiniLoginUser selectByWXminiOpenid(WxMiniLoginDto loginDto);
}
