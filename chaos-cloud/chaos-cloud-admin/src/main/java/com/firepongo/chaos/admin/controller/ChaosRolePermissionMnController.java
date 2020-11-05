package com.firepongo.chaos.admin.controller;

import com.firepongo.chaos.admin.api.data.ChaosRolePermissionListData;
import com.firepongo.chaos.admin.api.entity.ChaosRolePermission;
import com.firepongo.chaos.admin.api.data.ChaosRolePermissionData;
import com.firepongo.chaos.admin.api.service.IChaosRolePermissionService;
import com.firepongo.chaos.admin.service.tran.AdminTranService;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.db.UpdateData;
import com.firepongo.chaos.app.page.PageQueryDto;
import com.firepongo.chaos.app.result.data.DataResult;
import com.firepongo.chaos.app.result.page.PageResult;
import com.firepongo.chaos.web.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author G.G
 * @since 2020-04-14
 */
@Api(tags = "ChaosRolePermissionMnController")
@RestController
@RequestMapping("/manage/chaos_role_permission")
public class ChaosRolePermissionMnController extends BaseController {

    @Autowired
    private IChaosRolePermissionService iChaosRolePermissionService;
    @Autowired
    private AdminTranService adminTranService;


    @PostMapping("/listTitle")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    public DataResult<List<ChaosRolePermission>> listTitle(@RequestBody ChaosRolePermissionData data) throws Exception {
        return dataResult(iChaosRolePermissionService.selectRolePermissionByData(data));
    }

    @PostMapping("/listPermissionMus")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    public DataResult<List<String>> listPermissionMus(@RequestBody ChaosRolePermissionData data) throws Exception {
        return dataResult(iChaosRolePermissionService.selectPermissionMusByRoleMu(data));
    }

    @PostMapping("/grant")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<Boolean> grant(@ApiParam(value = "") @RequestBody @Validated ChaosRolePermissionListData data, BindingResult bindingResult) throws Exception {
        validate(bindingResult);
        return dataResult(adminTranService.grant(data));
    }

}
