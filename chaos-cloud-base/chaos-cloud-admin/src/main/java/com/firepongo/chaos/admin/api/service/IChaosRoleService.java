package com.firepongo.chaos.admin.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.firepongo.chaos.admin.api.entity.ChaosRole;
import com.firepongo.chaos.app.admin.ChaosRoleData;
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
public interface IChaosRoleService extends IService<ChaosRole> {

    MU insertModel(ChaosRoleData data);

    boolean deleteModel(MU data);

    boolean updateModelByMU(UpdateData<ChaosRoleData> data);

    ChaosRoleData selectByMU(MU data);

    List<ChaosRoleData> selectByData(ChaosRoleData data);

    PageList<ChaosRoleData> selectByPage(PageQueryDto<ChaosRoleData> pageData);
}
