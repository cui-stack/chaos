package com.cui.tech.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cui.tech.chaos.lite.service.IMnLoginUserService;
import com.cui.tech.manage.api.data.ChaosAdminData;
import com.cui.tech.manage.api.entity.ChaosAdmin;
import com.cui.tech.manage.api.entity.ChaosRole;
import com.cui.tech.manage.api.service.IChaosAdminService;
import com.cui.tech.manage.service.mapper.ChaosAdminMapper;
import com.cui.tech.chaos.model.db.MU;
import com.cui.tech.chaos.model.db.Table;
import com.cui.tech.chaos.model.db.UpdateData;
import com.cui.tech.chaos.model.login.ManageLoginDto;
import com.cui.tech.chaos.model.login.ManageLoginUser;
import com.cui.tech.chaos.model.page.PageList;
import com.cui.tech.chaos.model.page.PageQueryDto;
import org.apache.commons.codec.digest.DigestUtils;
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
public class ChaosAdminServiceImpl extends ServiceImpl<ChaosAdminMapper, ChaosAdmin> implements IChaosAdminService, IMnLoginUserService {

    @Override
    public MU insertModel(ChaosAdminData data) {
        ChaosAdmin entity = new ChaosAdmin();
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
    public boolean updateModelByMU(UpdateData<ChaosAdminData> data) {
        ChaosAdmin entity = new ChaosAdmin();
        BeanUtils.copyProperties(data.getData(), entity);
        return update(entity,
                new UpdateWrapper<ChaosAdmin>()
                        .eq(Table.MU, data.getMu())
        );
    }

    @Override
    public boolean updateLoginLog(UpdateData<ChaosAdminData> data) {
        return baseMapper.updateLoginLog(data.getMu(), data.getData().getIp());
    }

    @Override
    public ChaosAdmin selectByMU(MU data) {
        return getOne(new QueryWrapper<ChaosAdmin>()
                .eq(Table.MU, data.getMu()));
    }

    @Override
    public List<ChaosAdmin> selectByData(ChaosAdminData data) {
        QueryWrapper<ChaosAdmin> query = new QueryWrapper<ChaosAdmin>();
        //query.eq(!data.getPassword().isEmpty(),"password", data.getPassword());
        query.orderByDesc(Table.ID);
        return list(query);
    }

    @Override
    public PageList<ChaosAdmin> selectByPage(PageQueryDto<ChaosAdminData> pageData) {
        QueryWrapper query = new QueryWrapper();
        query.eq(!StringUtils.isEmpty(pageData.getData().getPlatformMu()), "platform_mu", pageData.getData().getPlatformMu());
        query.eq(!StringUtils.isEmpty(pageData.getData().getName()), "name", pageData.getData().getName());
        query.eq(!StringUtils.isEmpty(pageData.getData().getUsername()), "username", pageData.getData().getUsername());
        query.orderByDesc(Table.ID);
        return new PageList(page(new Page(pageData.getPageNum(), pageData.getPageSize()), query));
    }


    @Override
    public ManageLoginUser selectByUsernameAndPassword(ManageLoginDto loginDto) {
        ChaosAdmin user = getOne(new QueryWrapper<ChaosAdmin>()
                .eq("username", loginDto.getUsername())
                .eq("password", DigestUtils.md5Hex(loginDto.getPassword())).eq("status", 0)
                .eq("platform_mu", loginDto.getPlatformMu()));
        if (user == null) {
            return null;
        }
        return new ManageLoginUser(user.getId(), user.getMu(), user.getUsername(), user.getPhone());

    }

    @Override
    public ChaosRole selectIndexLink(String mu) {
        return baseMapper.selectIndexLink(mu);
    }

    @Override
    public ChaosAdminData selectAdminRoleByMU(MU data) {
        return baseMapper.selectAdminRoleByMU(data.getMu());
    }

    @Override
    public boolean updateModelByPassword(UpdateData<ChaosAdminData> data) {
        ChaosAdmin entity = new ChaosAdmin();
        UpdateWrapper update = new UpdateWrapper<ChaosAdmin>();
        update.eq("username", data.getData().getUsername());
        update.eq("password", DigestUtils.md5Hex(data.getData().getOldPassword()));
        entity.setPassword(DigestUtils.md5Hex(data.getData().getPassword()));
        return update(entity, update);
    }
}
