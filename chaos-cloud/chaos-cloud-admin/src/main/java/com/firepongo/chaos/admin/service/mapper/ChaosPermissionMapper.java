package com.firepongo.chaos.admin.service.mapper;

import com.firepongo.chaos.admin.api.data.ChaosPermissionData;
import com.firepongo.chaos.admin.api.entity.ChaosPermission;
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
