package com.firepongo.chaos.manage.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.firepongo.chaos.manage.api.data.ChaosEnvData;
import com.firepongo.chaos.manage.api.entity.ChaosEnv;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.db.UpdateData;
import com.firepongo.chaos.app.page.PageList;
import com.firepongo.chaos.app.page.PageQueryDto;

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
