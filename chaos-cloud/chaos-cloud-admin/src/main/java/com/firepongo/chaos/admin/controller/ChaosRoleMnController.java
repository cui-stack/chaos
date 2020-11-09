package com.firepongo.chaos.admin.controller;

import com.firepongo.chaos.admin.api.entity.ChaosRole;
import com.firepongo.chaos.admin.api.data.ChaosRoleData;
import com.firepongo.chaos.admin.api.service.IChaosRoleService;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.db.UpdateData;
import com.firepongo.chaos.app.page.PageQueryDto;
import com.firepongo.chaos.app.result.data.DataResult;
import com.firepongo.chaos.app.result.page.PageResult;
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
@Api(tags = "ChaosRoleMnController")
@RestController
@RequestMapping("/manage/chaos_role")
public class ChaosRoleMnController extends BaseController {

    @Autowired
    private IChaosRoleService iChaosRoleService;

    @ManageLoginToken
    @PostMapping("/add")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<MU> add(@RequestBody @Validated ChaosRoleData data, BindingResult bindingResult) throws Exception {
        validate(bindingResult);
        return dataResult(iChaosRoleService.insertModel(data));
    }

    @ManageLoginToken
    @PostMapping("/one")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<ChaosRole> one(@RequestBody MU data) throws Exception {
        return dataResult(iChaosRoleService.selectByMU(data));
    }

    @ManageLoginToken
    @PostMapping("/update")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<Boolean> update(@ApiParam(value = "") @RequestBody @Validated UpdateData<ChaosRoleData> data, BindingResult bindingResult) throws Exception {
        validate(bindingResult);
        return dataResult(iChaosRoleService.updateModelByMU(data));
    }

    @ManageLoginToken
    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    public DataResult<List<ChaosRole>> list(@RequestBody ChaosRoleData data) throws Exception {
        return dataResult(iChaosRoleService.selectByData(data));
    }

    @ManageLoginToken
    @PostMapping("/page")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public PageResult<ChaosRole> page(@RequestBody PageQueryDto<ChaosRoleData> data) throws Exception {
        return pageResult(iChaosRoleService.selectByPage(data));
    }

    @ManageLoginToken
    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "", httpMethod = "POST")
    public DataResult<Boolean> delete(@RequestBody MU data) throws Exception {
        return dataResult(iChaosRoleService.deleteModel(data));
    }

}
