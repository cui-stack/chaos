package com.cui.tech.manage.manager.controller;

import com.cui.tech.chaos.base.BaseController;
import com.cui.tech.manage.api.data.ChaosRolePermissionListData;
import com.cui.tech.manage.api.entity.ChaosRolePermission;
import com.cui.tech.manage.api.data.ChaosRolePermissionData;
import com.cui.tech.manage.api.service.IChaosRolePermissionService;
import com.cui.tech.chaos.model.db.MU;
import com.cui.tech.chaos.model.db.UpdateData;
import com.cui.tech.chaos.model.page.PageQueryDto;
import com.cui.tech.chaos.model.result.DataResult;
import com.cui.tech.chaos.model.result.PageResult;
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
import com.cui.tech.chaos.annotation.ManageLoginToken;

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

    @PostMapping("/add")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<MU> add(@RequestBody @Validated ChaosRolePermissionData data, BindingResult bindingResult) throws Exception {
        validate(bindingResult);
        return getResult(iChaosRolePermissionService.insertModel(data));
    }

    @PostMapping("/one")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<ChaosRolePermission> one(@RequestBody MU data) throws Exception {
        return getResult(iChaosRolePermissionService.selectByMU(data));
    }

    @PostMapping("/update")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<Boolean> update(@ApiParam(value = "") @RequestBody @Validated UpdateData<ChaosRolePermissionData> data, BindingResult bindingResult) throws Exception {
        validate(bindingResult);
        return getResult(iChaosRolePermissionService.updateModelByMU(data));
    }

    @PostMapping("/list")
    @ManageLoginToken
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    public DataResult<List<ChaosRolePermission>> list(@RequestBody ChaosRolePermissionData data) throws Exception {
        return getResult(iChaosRolePermissionService.selectByData(data));
    }

    @PostMapping("/page")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public PageResult<ChaosRolePermission> page(@RequestBody PageQueryDto<ChaosRolePermissionData> data) throws Exception {
        return getResult(iChaosRolePermissionService.selectByPage(data));
    }

    @PostMapping("/delete")
    @ManageLoginToken
    @ApiOperation(value = "删除", notes = "", httpMethod = "POST")
    public DataResult<Boolean> delete(@RequestBody MU data) throws Exception {
        return getResult(iChaosRolePermissionService.deleteModel(data));
    }

    @PostMapping("/listTitle")
    @ManageLoginToken
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    public DataResult<List<ChaosRolePermission>> listTitle(@RequestBody ChaosRolePermissionData data) throws Exception {
        return getResult(iChaosRolePermissionService.selectRolePermissionByData(data));
    }

    @PostMapping("/listPermissionMus")
    @ManageLoginToken
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    public DataResult<List<String>> listPermissionMus(@RequestBody ChaosRolePermissionData data) throws Exception {
        return getResult(iChaosRolePermissionService.selectPermissionMusByRoleMu(data));
    }

    @PostMapping("/grant")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<Boolean> grant(@ApiParam(value = "") @RequestBody @Validated ChaosRolePermissionListData data, BindingResult bindingResult) throws Exception {
        validate(bindingResult);
        return getResult(iChaosRolePermissionService.grant(data));
    }

}
