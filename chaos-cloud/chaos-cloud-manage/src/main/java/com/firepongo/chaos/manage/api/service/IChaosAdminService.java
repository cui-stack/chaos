package com.firepongo.chaos.manage.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.firepongo.chaos.manage.api.data.ChaosAdminData;
import com.firepongo.chaos.manage.api.entity.ChaosAdmin;
import com.firepongo.chaos.manage.api.entity.ChaosRole;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.db.UpdateData;
import com.firepongo.chaos.app.page.PageList;
import com.firepongo.chaos.app.page.PageQueryDto;

import java.util.List;

/**
 * @author G.G
 * @since 2020-04-14
 */
public interface IChaosAdminService extends IService<ChaosAdmin> {

    MU insertModel(ChaosAdminData data);

    boolean deleteModel(MU data);

    boolean updateModelByMU(UpdateData<ChaosAdminData> data);

    ChaosAdmin selectByMU(MU data);

    List<ChaosAdmin> selectByData(ChaosAdminData data);

    PageList<ChaosAdmin> selectByPage(PageQueryDto<ChaosAdminData> pageData);

    boolean updateModelByPassword(UpdateData<ChaosAdminData> data);

    ChaosRole selectIndexLink(String mu);

    ChaosAdminData selectAdminRoleByMU(MU data);

    boolean updateLoginLog(UpdateData<ChaosAdminData> data);

}
