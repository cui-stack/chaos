package com.firepongo.chaos.manage.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.firepongo.chaos.manage.api.entity.ChaosRole;
import com.firepongo.chaos.manage.api.data.ChaosRoleData;
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

    ChaosRole selectByMU(MU data);

    List<ChaosRole> selectByData(ChaosRoleData data);

    PageList<ChaosRole> selectByPage(PageQueryDto<ChaosRoleData> pageData);
}
