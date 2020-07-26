package com.cui.tech.manage.manager.controller;

import com.cui.tech.chaos.base.BaseController;
import com.cui.tech.manage.api.entity.ChaosRole;
import com.cui.tech.manage.api.data.ChaosRoleData;
import com.cui.tech.manage.api.service.IChaosRoleService;
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
*
* @author G.G
* @since 2020-04-14
*/
@Api(tags = "ChaosRoleMnController")
@RestController
@RequestMapping("/manage/chaos_role")
public class ChaosRoleMnController extends BaseController {

    @Autowired
    private IChaosRoleService iChaosRoleService;

    @PostMapping("/add")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<MU> add(@RequestBody @Validated ChaosRoleData data, BindingResult bindingResult) throws Exception {
        validate(bindingResult);
        return getResult(iChaosRoleService.insertModel(data));
    }

    @PostMapping("/one")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<ChaosRole> one( @RequestBody MU data) throws Exception {
        return getResult(iChaosRoleService.selectByMU(data));
    }

    @PostMapping("/update")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<Boolean> update( @ApiParam(value = "") @RequestBody @Validated UpdateData<ChaosRoleData> data, BindingResult bindingResult) throws Exception {
        validate(bindingResult);
        return getResult(iChaosRoleService.updateModelByMU(data));
    }

    @PostMapping("/list")
    @ManageLoginToken
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    public DataResult<List<ChaosRole>> list( @RequestBody ChaosRoleData data) throws Exception {
        return getResult(iChaosRoleService.selectByData(data));
    }

    @PostMapping("/page")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public PageResult<ChaosRole> page( @RequestBody PageQueryDto<ChaosRoleData> data) throws Exception {
        return getResult(iChaosRoleService.selectByPage(data));
    }

    @PostMapping("/delete")
    @ManageLoginToken
    @ApiOperation(value = "删除", notes = "", httpMethod = "POST")
    public DataResult<Boolean> delete( @RequestBody MU data) throws Exception {
        return getResult(iChaosRoleService.deleteModel(data));
    }

}
