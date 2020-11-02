package com.firepongo.chaos.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.firepongo.chaos.manage.api.entity.ChaosRole;
import com.firepongo.chaos.manage.api.data.ChaosRoleData;
import com.firepongo.chaos.manage.api.service.IChaosRoleService;
import com.firepongo.chaos.manage.service.mapper.ChaosRoleMapper;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.db.Table;
import com.firepongo.chaos.app.db.UpdateData;
import com.firepongo.chaos.app.page.PageList;
import com.firepongo.chaos.app.page.PageQueryDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author G.G
 * @since 2020-04-14
 */
@Service
@Component
public class ChaosRoleServiceImpl extends ServiceImpl<ChaosRoleMapper, ChaosRole> implements IChaosRoleService {

    @Override
    public MU insertModel(ChaosRoleData data) {
        ChaosRole entity = new ChaosRole();
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
    public boolean updateModelByMU(UpdateData<ChaosRoleData> data) {
        ChaosRole entity = new ChaosRole();
        BeanUtils.copyProperties(data.getData(), entity);
        return update(entity,
                new UpdateWrapper<ChaosRole>()
                        .eq(Table.MU, data.getMu())
        );
    }

    @Override
    public ChaosRole selectByMU(MU data) {
        return getOne(new QueryWrapper<ChaosRole>()
                .eq(Table.MU, data.getMu()));
    }

    @Override
    public List<ChaosRole> selectByData(ChaosRoleData data) {
        QueryWrapper<ChaosRole> query = new QueryWrapper<ChaosRole>();
        query.eq(!StringUtils.isEmpty(data.getPlatformMu()), "platform_mu", data.getPlatformMu());
        query.orderByDesc(Table.ID);
        return list(query);
    }

    @Override
    public PageList<ChaosRole> selectByPage(PageQueryDto<ChaosRoleData> pageData) {
        QueryWrapper query = new QueryWrapper();
        query.eq(!StringUtils.isEmpty(pageData.getData().getInfo()), "info", pageData.getData().getInfo());
        query.eq(!StringUtils.isEmpty(pageData.getData().getPlatformMu()), "platform_mu", pageData.getData().getPlatformMu());
        query.orderByDesc(Table.ID);
        return new PageList(page(new Page(pageData.getPageNum(), pageData.getPageSize()), query));
    }

}
