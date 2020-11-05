package com.firepongo.chaos.admin.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.firepongo.chaos.admin.api.data.ChaosLogData;
import com.firepongo.chaos.admin.api.entity.ChaosLog;
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

    ChaosLogData selectByMU(MU data);

    List<ChaosLogData> selectByData(ChaosLogData data);

    PageList<ChaosLogData> selectByPage(PageQueryDto<ChaosLogData> pageData);
}
