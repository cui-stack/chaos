package com.cui.tech.manage.service.mapper;

import com.cui.tech.manage.api.data.ChaosRolePermissionData;
import com.cui.tech.manage.api.entity.ChaosRolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author G.G
 * @since 2020-04-14
 */
public interface ChaosRolePermissionMapper extends BaseMapper<ChaosRolePermission> {

    List<ChaosRolePermissionData> selectRolePermissionByData(@Param("roleMu") String roleMu);

    List<String> selectPermissionMusByRoleMu(@Param("roleMu") String roleMu);

}
