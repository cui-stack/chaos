package com.cui.tech.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cui.tech.chaos.service.ILogService;
import com.cui.tech.manage.api.entity.ChaosLog;
import com.cui.tech.manage.api.data.ChaosLogData;
import com.cui.tech.manage.api.service.IChaosLogService;
import com.cui.tech.manage.service.mapper.ChaosLogMapper;
import com.cui.tech.chaos.model.db.MU;
import com.cui.tech.chaos.model.db.Table;
import com.cui.tech.chaos.model.db.UpdateData;
import com.cui.tech.chaos.model.page.PageList;
import com.cui.tech.chaos.model.page.PageQueryDto;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
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
@Service
@Component
public class ChaosLogServiceImpl extends ServiceImpl<ChaosLogMapper, ChaosLog> implements IChaosLogService, ILogService {

    @Override
    public MU insertModel(ChaosLogData data) {
        ChaosLog entity = new ChaosLog();
        BeanUtils.copyProperties(data, entity);
        entity.setVersion(1);
        boolean flag = save(entity);
        if (flag) {
            return new MU(entity.getMu());
        }
        return null;
    }

    @Override
    public boolean deleteModel(MU data) {
        return removeById(data.getMu());
    }

    @Override
    public boolean updateModelByMU(UpdateData<ChaosLogData> data) {
        ChaosLog entity = new ChaosLog();
        BeanUtils.copyProperties(data.getData(), entity);
        return update(entity,
                new UpdateWrapper<ChaosLog>()
                        .eq(Table.MU, data.getMu())
        );
    }

    @Override
    public ChaosLog selectByMU(MU data) {
        return getOne(new QueryWrapper<ChaosLog>()
                .eq(Table.MU, data.getMu()));
    }

    @Override
    public List<ChaosLog> selectByData(ChaosLogData data) {
        QueryWrapper<ChaosLog> query = new QueryWrapper<ChaosLog>();
        //query.eq(!StringUtils.isEmpty(data.getPassword()),"password", data.getPassword());
        query.orderByDesc(Table.ID);
        return list(query);
    }

    @Override
    public PageList<ChaosLog> selectByPage(PageQueryDto<ChaosLogData> pageData) {
        QueryWrapper query = new QueryWrapper();
        query.eq(!StringUtils.isEmpty(pageData.getData().getUri()),"uri", pageData.getData().getUri());
        query.eq(!StringUtils.isEmpty(pageData.getData().getUserMu()),"user_mu", pageData.getData().getUserMu());
        query.orderByDesc(Table.ID);
        return new PageList(page(new Page(pageData.getPageNum(), pageData.getPageSize()), query));
    }

    @Override
    @Async
    public void log(String userMu, String ip, String uri, long time, String request, String response) {
        insertModel(new ChaosLogData(userMu, ip, uri, time, request, response));
    }

}
