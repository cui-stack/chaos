package com.firepongo.chaos.manage.service.mapper;

import com.firepongo.chaos.manage.api.data.ChaosAdminData;
import com.firepongo.chaos.manage.api.entity.ChaosAdmin;
import com.firepongo.chaos.manage.api.entity.ChaosRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author G.G
 * @since 2020-04-14
 */
public interface ChaosAdminMapper extends BaseMapper<ChaosAdmin> {

    ChaosRole selectIndexLink(@Param("mu") String adminmu);

    ChaosAdminData selectAdminRoleByMU(@Param("mu") String mu);

    boolean updateLoginLog(@Param("mu") String mu,@Param("ip") String ip);

}
