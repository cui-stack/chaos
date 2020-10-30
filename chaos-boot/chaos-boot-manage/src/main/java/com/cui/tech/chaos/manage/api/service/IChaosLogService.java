package com.cui.tech.chaos.manage.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cui.tech.chaos.manage.api.data.ChaosLogData;
import com.cui.tech.chaos.manage.api.entity.ChaosLog;
import com.cui.tech.chaos.model.db.MU;
import com.cui.tech.chaos.model.db.UpdateData;
import com.cui.tech.chaos.model.page.PageList;
import com.cui.tech.chaos.model.page.PageQueryDto;

import java.util.List;

/**
 *
 * @author G.G
 * @since 2020-04-28
*/
public interface IChaosLogService extends IService<ChaosLog> {

    MU insertModel(ChaosLogData data);

    boolean deleteModel(MU data);

    boolean updateModelByMU(UpdateData<ChaosLogData> data);

    ChaosLog selectByMU(MU data);

    List<ChaosLog> selectByData(ChaosLogData data);

    PageList<ChaosLog> selectByPage(PageQueryDto<ChaosLogData> pageData);
}
