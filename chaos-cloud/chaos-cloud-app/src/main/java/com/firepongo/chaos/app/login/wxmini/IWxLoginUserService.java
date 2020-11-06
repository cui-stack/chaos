package com.firepongo.chaos.app.login.wxmini;


import com.firepongo.chaos.app.login.wxmini.WxMiniLoginDto;
import com.firepongo.chaos.app.login.wxmini.WxMiniLoginUser;

/**
 * @author G.G
 * @date 2019/11/15 15:10
 */
public interface IWxLoginUserService {

    WxMiniLoginUser selectByWXminiOpenid(WxMiniLoginDto loginDto);
}
