package com.firepongo.chaos.manage.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.firepongo.chaos.manage.api.data.ChaosLogData;
import com.firepongo.chaos.manage.api.entity.ChaosLog;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.db.UpdateData;
import com.firepongo.chaos.app.page.PageList;
import com.firepongo.chaos.app.page.PageQueryDto;

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
