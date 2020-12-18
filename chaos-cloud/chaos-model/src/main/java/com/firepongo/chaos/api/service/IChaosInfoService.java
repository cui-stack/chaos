package com.firepongo.chaos.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.firepongo.chaos.api.entity.ChaosInfo;
import com.firepongo.chaos.api.data.ChaosInfoData;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.db.UpdateData;
import com.firepongo.chaos.app.page.PageList;
import com.firepongo.chaos.app.page.PageQueryDto;

import java.util.List;

/**
 *
 * @author G.G
 * @since 2020-12-18
*/
public interface IChaosInfoService extends IService<ChaosInfo> {

    MU insertModel(ChaosInfoData data);

    boolean deleteModel(MU data);

    boolean updateModelByMU(UpdateData<ChaosInfoData> data);

    ChaosInfoData selectByMU(MU data);

    List<ChaosInfoData> selectByData(ChaosInfoData data);

    PageList<ChaosInfoData> selectByPage(PageQueryDto<ChaosInfoData> pageData);
}
