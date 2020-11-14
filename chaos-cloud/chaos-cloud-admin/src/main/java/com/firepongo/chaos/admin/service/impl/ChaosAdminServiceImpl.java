package com.firepongo.chaos.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.firepongo.chaos.admin.api.data.ChaosAdminData;
import com.firepongo.chaos.admin.api.data.ChaosRoleData;
import com.firepongo.chaos.admin.api.entity.ChaosAdmin;
import com.firepongo.chaos.admin.api.service.IChaosAdminService;
import com.firepongo.chaos.admin.service.mapper.ChaosAdminMapper;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.db.Table;
import com.firepongo.chaos.app.db.UpdateData;
import com.firepongo.chaos.app.login.manage.IMnLoginUserService;
import com.firepongo.chaos.app.login.manage.ManageLoginDto;
import com.firepongo.chaos.app.login.manage.ManageLoginUser;
import com.firepongo.chaos.app.page.PageList;
import com.firepongo.chaos.app.page.PageQueryDto;
import com.firepongo.chaos.app.service.ConvertService;
import org.apache.commons.codec.digest.DigestUtils;
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
 * @since 2020-04-14
 */

@Service(interfaceClass = IChaosAdminService.class)
@Component
public class ChaosAdminServiceImpl extends ServiceImpl<ChaosAdminMapper, ChaosAdmin> implements IChaosAdminService, IMnLoginUserService {
    @Autowired
    private ConvertService convertService;

    @Override
    public MU insertModel(ChaosAdminData data) {
        ChaosAdmin entity = (ChaosAdmin) convertService.convertToMuModel(data, ChaosAdmin.class);
        entity.setVersion(1);
        return save(entity) ? MU.of(entity.getMu()) : null;
    }

    @Override
    public boolean deleteModel(MU data) {
        return removeById(data.getMu());
    }

    @Override
    public boolean updateModelByMU(UpdateData<ChaosAdminData> data) {
        ChaosAdmin entity = (ChaosAdmin) convertService.convertToMuModel(data.getData(), ChaosAdmin.class);
        return update(entity, new UpdateWrapper<ChaosAdmin>().eq(Table.MU, data.getMu()));
    }

    @Override
    public boolean updateLoginLog(UpdateData<ChaosAdminData> data) {
        return baseMapper.updateLoginLog(data.getMu(), data.getData().getIp());
    }

    @Override
    public ChaosAdminData selectByMU(MU data) {
        return (ChaosAdminData) convertService.convertToDTO(getOne(new QueryWrapper<ChaosAdmin>()
                .eq(Table.MU, data.getMu())), ChaosAdminData.class);
    }

    @Override
    public List<ChaosAdminData> selectByData(ChaosAdminData data) {
        QueryWrapper<ChaosAdmin> query = new QueryWrapper<ChaosAdmin>();
        query.lambda().eq(!StringUtils.isEmpty(data.getUsername()), ChaosAdmin::getUsername, data.getUsername());
        query.orderByDesc(Table.ID);
        return convertService.convertToDTO(list(query), ChaosAdminData.class);
    }

    @Override
    public PageList<ChaosAdminData> selectByPage(PageQueryDto<ChaosAdminData> pageData) {
        QueryWrapper<ChaosAdmin> query = new QueryWrapper();
        query.lambda()
                .eq(!StringUtils.isEmpty(pageData.getData().getPlatformMu()), ChaosAdmin::getPlatformMu, pageData.getData().getPlatformMu())
                .eq(!StringUtils.isEmpty(pageData.getData().getName()), ChaosAdmin::getName, pageData.getData().getName())
                .eq(!StringUtils.isEmpty(pageData.getData().getUsername()), ChaosAdmin::getUsername, pageData.getData().getUsername());
        query.orderByDesc(Table.ID);
        return new PageList(page(new Page(pageData.getPageNum(), pageData.getPageSize()), query));
    }


    @Override
    public ManageLoginUser selectByUsernameAndPassword(ManageLoginDto loginDto) {
        ChaosAdmin user = getOne(new QueryWrapper<ChaosAdmin>()
                .eq("username", loginDto.getUsername())
                .eq("password", DigestUtils.md5Hex(loginDto.getPassword()))
                .eq("platform_mu", loginDto.getPlatformMu()));
        if (user == null) {
            return null;
        }
        return new ManageLoginUser(user.getMu(), user.getUsername(), user.getPhone(), user.getStatus());

    }

    @Override
    public ManageLoginUser selectByPhone(ManageLoginDto loginDto) {
        ChaosAdmin user = getOne(new QueryWrapper<ChaosAdmin>()
                .eq("phone", loginDto.getPhone())
                .eq("platform_mu", loginDto.getPlatformMu()));
        if (user == null) {
            return null;
        }
        return new ManageLoginUser(user.getMu(), user.getUsername(), user.getPhone(), user.getStatus());
    }

    @Override
    public ChaosRoleData selectIndexLink(String mu) {
        return baseMapper.selectIndexLink(mu);
    }

    @Override
    public ChaosAdminData selectAdminRoleByMU(MU data) {
        return baseMapper.selectAdminRoleByMU(data.getMu());
    }

    @Override
    public boolean updateModelByPassword(UpdateData<ChaosAdminData> data) {
        ChaosAdmin entity = new ChaosAdmin();
        UpdateWrapper<ChaosAdmin> update = new UpdateWrapper<ChaosAdmin>();
        update.lambda()
                .eq(ChaosAdmin::getUsername, data.getData().getUsername())
                .eq(ChaosAdmin::getPassword, DigestUtils.md5Hex(data.getData().getOldPassword()));
        entity.setPassword(DigestUtils.md5Hex(data.getData().getPassword()));
        return update(entity, update);
    }


}
