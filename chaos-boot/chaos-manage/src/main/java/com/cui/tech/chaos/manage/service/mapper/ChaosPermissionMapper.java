package com.cui.tech.chaos.manage.service.mapper;

import com.cui.tech.chaos.manage.api.data.ChaosPermissionData;
import com.cui.tech.chaos.manage.api.entity.ChaosPermission;
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
public interface ChaosPermissionMapper extends BaseMapper<ChaosPermission> {

    List<ChaosPermissionData> selectPermissionByAdmin(@Param("mu") String adminmu);
}
