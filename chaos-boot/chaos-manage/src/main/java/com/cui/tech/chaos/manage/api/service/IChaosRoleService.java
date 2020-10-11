package com.cui.tech.chaos.manage.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cui.tech.chaos.manage.api.entity.ChaosRole;
import com.cui.tech.chaos.manage.api.data.ChaosRoleData;
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
public interface IChaosRoleService extends IService<ChaosRole> {

    MU insertModel(ChaosRoleData data);

    boolean deleteModel(MU data);

    boolean updateModelByMU(UpdateData<ChaosRoleData> data);

    ChaosRole selectByMU(MU data);

    List<ChaosRole> selectByData(ChaosRoleData data);

    PageList<ChaosRole> selectByPage(PageQueryDto<ChaosRoleData> pageData);
}
