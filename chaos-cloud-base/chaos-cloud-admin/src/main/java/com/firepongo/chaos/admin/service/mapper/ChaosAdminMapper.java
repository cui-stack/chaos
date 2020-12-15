package com.firepongo.chaos.admin.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.firepongo.chaos.app.admin.ChaosRoleData;
import com.firepongo.chaos.admin.api.entity.ChaosAdmin;
import com.firepongo.chaos.app.admin.ChaosAdminData;
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

    ChaosRoleData selectIndexLink(@Param("mu") String adminmu);

    ChaosAdminData selectAdminRoleByMU(@Param("mu") String mu);

    boolean updateLoginLog(@Param("mu") String mu,@Param("ip") String ip);

}
