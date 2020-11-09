package com.firepongo.chaos.admin.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.firepongo.chaos.admin.api.data.ChaosAdminData;
import com.firepongo.chaos.admin.api.data.ChaosRoleData;
import com.firepongo.chaos.admin.api.entity.ChaosAdmin;
import com.firepongo.chaos.admin.api.entity.ChaosRole;
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

    ChaosAdminData selectByMU(MU data);

    List<ChaosAdminData> selectByData(ChaosAdminData data);

    PageList<ChaosAdminData> selectByPage(PageQueryDto<ChaosAdminData> pageData);

    boolean updateModelByPassword(UpdateData<ChaosAdminData> data);

    ChaosRoleData selectIndexLink(String mu);

    ChaosAdminData selectAdminRoleByMU(MU data);

    boolean updateLoginLog(UpdateData<ChaosAdminData> data);

}
