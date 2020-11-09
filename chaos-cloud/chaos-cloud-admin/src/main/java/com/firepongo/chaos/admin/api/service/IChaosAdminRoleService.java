package com.firepongo.chaos.admin.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.firepongo.chaos.admin.api.entity.ChaosAdminRole;
import com.firepongo.chaos.admin.api.data.ChaosAdminRoleData;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.db.UpdateData;
import com.firepongo.chaos.app.page.PageList;
import com.firepongo.chaos.app.page.PageQueryDto;

import java.util.List;

/**
 *
 * @author G.G
 * @since 2020-04-14
*/
public interface IChaosAdminRoleService extends IService<ChaosAdminRole> {

    MU insertModel(ChaosAdminRoleData data);

    boolean deleteModel(MU data);

    boolean updateModelByMU(UpdateData<ChaosAdminRoleData> data);

    boolean updateRoleByAdmin(String mu, String roleMU);

    ChaosAdminRoleData selectByMU(MU data);

    List<ChaosAdminRoleData> selectByData(ChaosAdminRoleData data);

    PageList<ChaosAdminRoleData> selectByPage(PageQueryDto<ChaosAdminRoleData> pageData);

    ChaosAdminRoleData selectByAdmin(String adminMU);

}
