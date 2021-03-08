package com.firepongo.chaos.admin.service.mapper;

import com.firepongo.chaos.admin.api.data.ChaosResourceData;
import com.firepongo.chaos.admin.api.entity.ChaosResource;
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
public interface ChaosResourceMapper extends BaseMapper<ChaosResource> {

    List<ChaosResourceData> selectResourceByAdmin(@Param("mu") String adminMu);
}
