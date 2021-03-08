package com.firepongo.chaos.admin.service.mapper;

import com.firepongo.chaos.admin.api.entity.ChaosRoleResource;
import com.firepongo.chaos.admin.api.data.ChaosRoleResourceData;
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
public interface ChaosRoleResourceMapper extends BaseMapper<ChaosRoleResource> {

    List<ChaosRoleResourceData> selectRoleResourceByData(@Param("roleMu") String roleMu);

    List<String> selectResourceMusByRoleMu(@Param("roleMu") String roleMu);

}
