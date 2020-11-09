package com.firepongo.chaos.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.firepongo.chaos.admin.api.data.ChaosPermissionData;
import com.firepongo.chaos.admin.api.entity.ChaosPermission;
import com.firepongo.chaos.admin.service.mapper.ChaosPermissionMapper;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.db.Table;
import com.firepongo.chaos.app.db.UpdateData;
import com.firepongo.chaos.app.login.manage.ManageMenu;
import com.firepongo.chaos.app.page.PageList;
import com.firepongo.chaos.app.page.PageQueryDto;
import com.firepongo.chaos.admin.api.service.IChaosPermissionService;
import com.firepongo.chaos.app.service.ConvertService;
import com.firepongo.chaos.app.utils.PageHelper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author G.G
 * @since 2020-04-14
 */
@Service(interfaceClass = IChaosPermissionService.class)
@Component
public class ChaosPermissionServiceImpl extends ServiceImpl<ChaosPermissionMapper, ChaosPermission> implements IChaosPermissionService {
    @Autowired
    private ConvertService convertService;

    @Override
    public MU insertModel(ChaosPermissionData data) {
        ChaosPermission entity = (ChaosPermission) convertService.convertToMuModel(data, ChaosPermission.class);
        entity.setVersion(1);
        return save(entity) ? MU.of(entity.getMu()) : null;
    }

    @Override
    public boolean deleteModel(MU data) {
        return removeById(data.getMu());
    }

    @Override
    public boolean updateModelByMU(UpdateData<ChaosPermissionData> data) {
        ChaosPermission entity = (ChaosPermission) convertService.convertToMuModel(data.getData(), ChaosPermission.class);
        return update(entity, new UpdateWrapper<ChaosPermission>().eq(Table.MU, data.getMu()));
    }

    @Override
    public ChaosPermissionData selectByMU(MU data) {
        return (ChaosPermissionData) convertService.convertToDTO(getOne(new QueryWrapper<ChaosPermission>()
                .eq(Table.MU, data.getMu())), ChaosPermissionData.class);
    }

    @Override
    public List<ChaosPermissionData> selectByData(ChaosPermissionData data) {
        QueryWrapper<ChaosPermission> query = new QueryWrapper<ChaosPermission>();
        query.lambda()
                .eq(!StringUtils.isEmpty(data.getPlatformMu()), ChaosPermission::getPlatformMu, data.getPlatformMu())
                .eq(!StringUtils.isEmpty(data.getIsroot()), ChaosPermission::getIsroot, data.getIsroot());
        query.orderByDesc(Table.ID);
        return convertService.convertToDTO(list(query), ChaosPermissionData.class);

    }

    @Override
    public PageList<ChaosPermissionData> selectByPage(PageQueryDto<ChaosPermissionData> pageData) {
        QueryWrapper<ChaosPermission> query = new QueryWrapper();
        query.lambda()
                .eq(!StringUtils.isEmpty(pageData.getData().getPlatformMu()), ChaosPermission::getPlatformMu, pageData.getData().getPlatformMu())
                .eq(!StringUtils.isEmpty(pageData.getData().getTitle()), ChaosPermission::getTitle, pageData.getData().getTitle());
        query.orderByDesc(Table.ID);
        return new PageList(page(new Page(pageData.getPageNum(), pageData.getPageSize()), query));
    }

    @Override
    public List<ManageMenu> selectPermissionByAdmin(String adminmu) {
        List<ManageMenu> menus = new ArrayList<>();
        List<ChaosPermissionData> ps = baseMapper.selectPermissionByAdmin(adminmu);
        if (ps == null || ps.isEmpty()) {
            return menus;
        }
        Map<String, List<ChaosPermissionData>> map = ps.stream().collect(Collectors.groupingBy(ChaosPermissionData::getSuptitle));
        for (String title : map.keySet()) {
            String icon = "";
            List<ManageMenu> submenus = new ArrayList<>();
            for (ChaosPermissionData ip : map.get(title)) {
                if (StringUtils.isEmpty(icon)) {
                    icon = ip.getIcon();
                }
                ManageMenu m = ManageMenu.of(ip.getTitle());
                submenus.add(m.setLink(ip.getLink()));
            }
            ManageMenu m = ManageMenu.of(title);
            menus.add(m.setIcon(icon).setSubmenus(submenus));
        }
        return menus;
    }

    @Override
    public PageList<ChaosPermissionData> selectBySortPage(PageQueryDto<ChaosPermissionData> pageData) {
        QueryWrapper query = new QueryWrapper();
        query.orderByDesc(Table.ID);
        return new PageList(page(PageHelper.page(pageData), query));
    }

}
