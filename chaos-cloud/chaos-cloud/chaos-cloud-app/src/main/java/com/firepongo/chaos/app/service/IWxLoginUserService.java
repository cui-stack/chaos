package com.firepongo.chaos.app.service;


import com.firepongo.chaos.app.login.WxMiniLoginDto;
import com.firepongo.chaos.app.login.WxMiniLoginUser;

/**
 * @author G.G
 * @date 2019/11/15 15:10
 */
public interface IWxLoginUserService {

    WxMiniLoginUser selectByWXminiOpenid(WxMiniLoginDto loginDto);
}
