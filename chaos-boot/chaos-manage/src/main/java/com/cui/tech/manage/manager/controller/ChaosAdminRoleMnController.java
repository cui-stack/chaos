package com.cui.tech.manage.manager.controller;

import com.cui.tech.manage.api.entity.ChaosAdminRole;
import com.cui.tech.manage.api.data.ChaosAdminRoleData;
import com.cui.tech.manage.api.service.IChaosAdminRoleService;
import com.cui.tech.chaos.model.db.MU;
import com.cui.tech.chaos.model.db.UpdateData;
import com.cui.tech.chaos.model.page.PageQueryDto;
import com.cui.tech.chaos.model.result.DataResult;
import com.cui.tech.chaos.model.result.PageResult;
import com.cui.tech.chaos.web.BaseController;
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
*
* @author G.G
* @since 2020-04-14
*/
@Api(tags = "ChaosAdminRoleMnController")
@RestController
@RequestMapping("/manage/chaos_admin_role")
public class ChaosAdminRoleMnController extends BaseController {

    @Autowired
    private IChaosAdminRoleService iChaosAdminRoleService;

    @PostMapping("/add")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<MU> add(@RequestBody @Validated ChaosAdminRoleData data, BindingResult bindingResult) throws Exception {
        validate(bindingResult);
        return getResult(iChaosAdminRoleService.insertModel(data));
    }

    @PostMapping("/one")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<ChaosAdminRole> one( @RequestBody MU data) throws Exception {
        return getResult(iChaosAdminRoleService.selectByMU(data));
    }

    @PostMapping("/update")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<Boolean> update( @ApiParam(value = "") @RequestBody @Validated UpdateData<ChaosAdminRoleData> data, BindingResult bindingResult) throws Exception {
        validate(bindingResult);
        return getResult(iChaosAdminRoleService.updateModelByMU(data));
    }

    @PostMapping("/list")
    @ManageLoginToken
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    public DataResult<List<ChaosAdminRole>> list( @RequestBody ChaosAdminRoleData data) throws Exception {
        return getResult(iChaosAdminRoleService.selectByData(data));
    }

    @PostMapping("/page")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public PageResult<ChaosAdminRole> page( @RequestBody PageQueryDto<ChaosAdminRoleData> data) throws Exception {
        return getResult(iChaosAdminRoleService.selectByPage(data));
    }

    @PostMapping("/delete")
    @ManageLoginToken
    @ApiOperation(value = "删除", notes = "", httpMethod = "POST")
    public DataResult<Boolean> delete( @RequestBody MU data) throws Exception {
        return getResult(iChaosAdminRoleService.deleteModel(data));
    }

}
