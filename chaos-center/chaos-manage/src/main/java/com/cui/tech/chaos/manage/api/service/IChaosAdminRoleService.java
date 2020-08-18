package com.cui.tech.chaos.manage.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cui.tech.chaos.manage.api.entity.ChaosAdminRole;
import com.cui.tech.chaos.manage.api.data.ChaosAdminRoleData;
import com.cui.tech.chaos.model.db.MU;
import com.cui.tech.chaos.model.db.UpdateData;
import com.cui.tech.chaos.model.page.PageList;
import com.cui.tech.chaos.model.page.PageQueryDto;

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
