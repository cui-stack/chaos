package com.firepongo.chaos.manage.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.firepongo.chaos.manage.api.entity.ChaosAdminRole;
import com.firepongo.chaos.manage.api.data.ChaosAdminRoleData;
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

    ChaosAdminRole selectByMU(MU data);

    List<ChaosAdminRole> selectByData(ChaosAdminRoleData data);

    PageList<ChaosAdminRole> selectByPage(PageQueryDto<ChaosAdminRoleData> pageData);

    ChaosAdminRole selectByAdmin(String adminMU);

}
