package com.cui.tech.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cui.tech.chaos.service.helper.PageHelper;
import com.cui.tech.manage.api.entity.ChaosAdminRole;
import com.cui.tech.manage.api.data.ChaosAdminRoleData;
import com.cui.tech.manage.api.service.IChaosAdminRoleService;
import com.cui.tech.manage.service.mapper.ChaosAdminRoleMapper;
import com.cui.tech.chaos.model.db.MU;
import com.cui.tech.chaos.model.db.Table;
import com.cui.tech.chaos.model.db.UpdateData;
import com.cui.tech.chaos.model.page.PageList;
import com.cui.tech.chaos.model.page.PageQueryDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
public class ChaosAdminRoleServiceImpl extends ServiceImpl<ChaosAdminRoleMapper, ChaosAdminRole> implements IChaosAdminRoleService {

    @Autowired
    private PageHelper pageHelper;

    @Override
    public MU insertModel(ChaosAdminRoleData data) {
        ChaosAdminRole entity = new ChaosAdminRole();
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
    public boolean updateModelByMU(UpdateData<ChaosAdminRoleData> data) {
        ChaosAdminRole entity = new ChaosAdminRole();
        BeanUtils.copyProperties(data.getData(), entity);
        return update(entity,
                new UpdateWrapper<ChaosAdminRole>()
                        .eq(Table.MU, data.getMu())
        );
    }

    @Override
    public boolean updateRoleByAdmin(String adminMU, String roleMU) {
        ChaosAdminRole entity = new ChaosAdminRole();
        entity.setRoleMu(roleMU);
        return update(entity,
                new UpdateWrapper<ChaosAdminRole>()
                        .eq("admin_mu", adminMU)
        );
    }

    @Override
    public ChaosAdminRole selectByMU(MU data) {
        return getOne(new QueryWrapper<ChaosAdminRole>()
                .eq(Table.MU, data.getMu()));
    }

    @Override
    public List<ChaosAdminRole> selectByData(ChaosAdminRoleData data) {
        QueryWrapper<ChaosAdminRole> query = new QueryWrapper<ChaosAdminRole>();
        //query.eq(!data.getPassword().isEmpty(),"password", data.getPassword());
        query.orderByDesc(Table.ID);
        return list(query);
    }

    @Override
    public PageList<ChaosAdminRole> selectByPage(PageQueryDto<ChaosAdminRoleData> pageData) {
        QueryWrapper query = new QueryWrapper();
        //query.eq(!data.getPassword().isEmpty(),"phone", pageData.getData().getPhone());
        query.orderByDesc(Table.ID);
        return new PageList(page(pageHelper.page(pageData), query));
    }


    @Override
    public ChaosAdminRole selectByAdmin(String adminMU) {
        return getOne(new QueryWrapper<ChaosAdminRole>()
                .eq("admin_mu", adminMU));
    }

}
