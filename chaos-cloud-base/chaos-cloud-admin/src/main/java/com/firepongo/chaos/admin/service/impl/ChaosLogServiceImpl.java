package com.firepongo.chaos.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.firepongo.chaos.app.admin.ChaosLogData;
import com.firepongo.chaos.admin.api.entity.ChaosLog;
import com.firepongo.chaos.admin.api.service.IChaosLogService;
import com.firepongo.chaos.admin.service.mapper.ChaosLogMapper;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.db.Table;
import com.firepongo.chaos.app.db.UpdateData;
import com.firepongo.chaos.app.page.PageList;
import com.firepongo.chaos.app.page.PageQueryDto;
import com.firepongo.chaos.app.service.ConvertService;
import com.firepongo.chaos.web.service.ILogService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author G.G
 * @since 2020-04-28
 */
@Service(interfaceClass = IChaosLogService.class)
@Component
public class ChaosLogServiceImpl extends ServiceImpl<ChaosLogMapper, ChaosLog> implements IChaosLogService, ILogService {
    @Autowired
    private ConvertService convertService;

    @Override
    public MU insertModel(ChaosLogData data) {
        ChaosLog entity = (ChaosLog) convertService.convertToMuModel(data, ChaosLog.class);
        entity.setVersion(1);
        return save(entity) ? MU.of(entity.getMu()) : null;
    }

    @Override
    public boolean deleteModel(MU data) {
        return removeById(data.getMu());
    }

    @Override
    public boolean updateModelByMU(UpdateData<ChaosLogData> data) {
        ChaosLog entity = (ChaosLog) convertService.convertToMuModel(data.getData(), ChaosLog.class);
        return update(entity, new UpdateWrapper<ChaosLog>().eq(Table.MU, data.getMu()));
    }

    @Override
    public ChaosLogData selectByMU(MU data) {
        return (ChaosLogData) convertService.convertToDTO(getOne(new QueryWrapper<ChaosLog>()
                .eq(Table.MU, data.getMu())), ChaosLogData.class);
    }

    @Override
    public List<ChaosLogData> selectByData(ChaosLogData data) {
        QueryWrapper<ChaosLog> query = new QueryWrapper<ChaosLog>();
        query.orderByDesc(Table.ID);
        return convertService.convertToDTO(list(query), ChaosLogData.class);
    }

    @Override
    public PageList<ChaosLogData> selectByPage(PageQueryDto<ChaosLogData> pageData) {
        QueryWrapper<ChaosLog> query = new QueryWrapper();
        query.lambda()
                .eq(!StringUtils.isEmpty(pageData.getData().getUri()), ChaosLog::getUri, pageData.getData().getUri())
                .eq(!StringUtils.isEmpty(pageData.getData().getUserMu()), ChaosLog::getUserMu, pageData.getData().getUserMu());
        query.orderByDesc(Table.ID);
        return new PageList(page(new Page(pageData.getPageNum(), pageData.getPageSize()), query));
    }

    @Override
    public void log(String userMu, String ip, String uri, long time, String request, String response, String platform, String env) {
        insertModel(ChaosLogData.of(userMu, ip, uri, time, request, response, platform, env));
    }

}
