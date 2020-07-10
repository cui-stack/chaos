package com.cui.tech.code.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.cui.tech.code.api.data.ChaosAdminData;
import com.cui.tech.code.api.entity.ChaosAdmin;
import com.cui.tech.code.api.service.IChaosAdminService;
import com.cui.tech.chaos.lite.service.ILoginService;
import com.cui.tech.chaos.lite.service.IMnLoginUserService;
import com.cui.tech.chaos.lite.service.ManageLoginServiceImpl;
import com.cui.tech.chaos.model.db.UpdateData;
import com.cui.tech.chaos.model.login.LoginUser;
import com.cui.tech.chaos.model.login.ManageLoginDto;
import com.cui.tech.chaos.model.login.ManageLoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author G.G
 * @date 2019/11/14 15:45
 */
@Component("mnLoginService")
@Service(interfaceClass = ILoginService.class, group = "mn")
public class ManageLoginService extends ManageLoginServiceImpl {
    @Autowired
    private IMnLoginUserService iMnLoginUserService;
    @Autowired
    private IChaosAdminService iChaosAdminService;

    @Override
    public ManageLoginUser getUserInfo(ManageLoginDto loginDto) {
        return iMnLoginUserService.selectByUsernameAndPassword(loginDto);
    }

    @Override
    public void afterLogin(LoginUser loginUser) {
        iChaosAdminService.updateLoginLog(new UpdateData<ChaosAdminData>(loginUser.getMu(), new ChaosAdminData(loginUser.getIp())));
    }
}
