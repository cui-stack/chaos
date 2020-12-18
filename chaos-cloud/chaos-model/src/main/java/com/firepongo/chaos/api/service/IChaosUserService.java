package com.firepongo.chaos.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.firepongo.chaos.api.data.ChaosUserData;
import com.firepongo.chaos.api.entity.ChaosUser;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.db.UpdateData;
import com.firepongo.chaos.app.login.wxmini.IWxLoginUserService;
import com.firepongo.chaos.app.page.PageList;
import com.firepongo.chaos.app.page.PageQueryDto;

import java.util.List;

/**
 * @author G.G
 * @since 2020-11-17
 */
public interface IChaosUserService extends IService<ChaosUser>, IWxLoginUserService {

    MU insertModel(ChaosUserData data);

    boolean deleteModel(MU data);

    boolean updateModelByMU(UpdateData<ChaosUserData> data);

    ChaosUserData selectByMU(MU data);

    List<ChaosUserData> selectByData(ChaosUserData data);

    PageList<ChaosUserData> selectByPage(PageQueryDto<ChaosUserData> pageData);
}
