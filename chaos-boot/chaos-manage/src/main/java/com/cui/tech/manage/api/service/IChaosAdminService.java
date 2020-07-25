package com.cui.tech.manage.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cui.tech.manage.api.entity.ChaosAdmin;
import com.cui.tech.manage.api.data.ChaosAdminData;
import com.cui.tech.manage.api.entity.ChaosRole;
import com.cui.tech.chaos.service.IMnLoginUserService;
import com.cui.tech.chaos.model.db.MU;
import com.cui.tech.chaos.model.db.UpdateData;
import com.cui.tech.chaos.model.page.PageList;
import com.cui.tech.chaos.model.page.PageQueryDto;

import java.util.List;

/**
 * @author G.G
 * @since 2020-04-14
 */
public interface IChaosAdminService extends IService<ChaosAdmin>, IMnLoginUserService {

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
