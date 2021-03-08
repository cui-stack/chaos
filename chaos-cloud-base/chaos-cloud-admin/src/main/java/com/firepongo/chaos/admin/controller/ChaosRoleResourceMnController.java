package com.firepongo.chaos.admin.controller;

import com.firepongo.chaos.admin.api.data.ChaosRoleResourceListData;
import com.firepongo.chaos.admin.api.entity.ChaosRoleResource;
import com.firepongo.chaos.admin.api.data.ChaosRoleResourceData;
import com.firepongo.chaos.admin.api.service.IChaosRoleResourceService;
import com.firepongo.chaos.admin.service.tran.AdminTranService;
import com.firepongo.chaos.app.result.data.DataResult;
import com.firepongo.chaos.web.annotation.ManageLoginToken;
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
@Api(tags = "ChaosRoleResourceMnController")
@RestController
@RequestMapping("/manage/chaos_role_resource")
public class ChaosRoleResourceMnController extends BaseController {

    @Autowired
    private IChaosRoleResourceService iChaosRoleResourceService;
    @Autowired
    private AdminTranService adminTranService;

    @ManageLoginToken
    @PostMapping("/listTitle")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    public DataResult<List<ChaosRoleResource>> listTitle(@RequestBody ChaosRoleResourceData data) throws Exception {
        return dataResult(iChaosRoleResourceService.selectRoleResourceByData(data));
    }

    @ManageLoginToken
    @PostMapping("/listResourceMus")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    public DataResult<List<String>> listResourceMus(@RequestBody ChaosRoleResourceData data) throws Exception {
        return dataResult(iChaosRoleResourceService.selectResourceMusByRoleMu(data));
    }

    @ManageLoginToken
    @PostMapping("/grant")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<Boolean> grant(@ApiParam(value = "") @RequestBody @Validated ChaosRoleResourceListData data, BindingResult bindingResult) throws Exception {
        validate(bindingResult);
        return dataResult(adminTranService.grant(data));
    }

}
