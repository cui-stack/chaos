package com.cui.tech.chaos.manage.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cui.tech.chaos.manage.api.data.ChaosEnvData;
import com.cui.tech.chaos.manage.api.entity.ChaosEnv;
import com.cui.tech.chaos.model.db.MU;
import com.cui.tech.chaos.model.db.UpdateData;
import com.cui.tech.chaos.model.page.PageList;
import com.cui.tech.chaos.model.page.PageQueryDto;

import java.util.List;

/**
 *
 * @author G.G
 * @since 2020-08-18
*/
public interface IChaosEnvService extends IService<ChaosEnv> {

    MU insertModel(ChaosEnvData data);

    boolean deleteModel(MU data);

    boolean updateModelByMU(UpdateData<ChaosEnvData> data);

    ChaosEnvData selectByMU(MU data);

    List<ChaosEnvData> selectByData(ChaosEnvData data);

    PageList<ChaosEnvData> selectByPage(PageQueryDto<ChaosEnvData> pageData);
}
