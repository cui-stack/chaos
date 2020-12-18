package com.firepongo.chaos.client.service;

import com.firepongo.chaos.api.data.ChaosUserData;
import com.firepongo.chaos.api.service.IChaosUserService;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.db.UpdateData;
import com.firepongo.chaos.app.login.LoginDto;
import com.firepongo.chaos.app.login.LoginUser;
import com.firepongo.chaos.app.login.wxmini.WxMiniLoginDto;
import com.firepongo.chaos.app.login.wxmini.WxMiniLoginUser;
import com.firepongo.chaos.web.service.login.wxmini.WxminiLoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author G.G
 * @date 2019/11/14 15:45
 */
@Component("wxLoginService")
public class WxminiLoginService extends WxminiLoginServiceImpl {
    @Autowired
    private IChaosUserService iChaosUserService;

    @Override
    protected LoginUser befoLogin(LoginDto loginDto) {
        WxMiniLoginDto wxMiniLoginDto = (WxMiniLoginDto) loginDto;
        WxMiniLoginUser user = iChaosUserService.selectByWXminiOpenid(wxMiniLoginDto);
        if (user == null) {
            ChaosUserData create = new ChaosUserData();
            create.setOpenid(wxMiniLoginDto.getOpenid());
            create.setUnionid(wxMiniLoginDto.getUnionid());
            create.setReferrer(wxMiniLoginDto.getReferrer());
            create.setSource(wxMiniLoginDto.getSource());
            create.setIp(wxMiniLoginDto.getIp());
            create.setLastloginTime(LocalDateTime.now());
            MU mu = iChaosUserService.insertModel(create);
            user = new WxMiniLoginUser();
            user.setMu(mu.getMu());
        }
        user.setToken(((WxMiniLoginDto) loginDto).getToken());
        return user;
    }

    @Override
    public void afterLogin(LoginUser user) {
        UpdateData<ChaosUserData> update = new UpdateData<>(user.getMu(), new ChaosUserData().setLastloginTime(LocalDateTime.now()));
        iChaosUserService.updateModelByMU(update);
    }


}
