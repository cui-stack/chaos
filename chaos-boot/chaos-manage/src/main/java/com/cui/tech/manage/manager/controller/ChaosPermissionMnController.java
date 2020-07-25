package com.cui.tech.manage.manager.controller;

import com.cui.tech.manage.api.data.ChaosPermissionData;
import com.cui.tech.manage.api.entity.ChaosPermission;
import com.cui.tech.manage.api.service.IChaosPermissionService;
import com.cui.tech.chaos.model.Menu;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author G.G
 * @since 2020-04-14
 */
@Api(tags = "ChaosPermissionMnController")
@RestController
@RequestMapping("/manage/chaos_permission")
public class ChaosPermissionMnController extends BaseController {

    @Autowired
    private IChaosPermissionService iChaosPermissionService;

    @PostMapping("/add")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<MU> add(@RequestBody @Validated ChaosPermissionData data, BindingResult bindingResult) throws Exception {
        validate(bindingResult);
        return getResult(iChaosPermissionService.insertModel(data));
    }

    @PostMapping("/one")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<ChaosPermission> one(@RequestBody MU data) throws Exception {
        return getResult(iChaosPermissionService.selectByMU(data));
    }

    @PostMapping("/update")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<Boolean> update(@ApiParam(value = "") @RequestBody @Validated UpdateData<ChaosPermissionData> data, BindingResult bindingResult) throws Exception {
        validate(bindingResult);
        return getResult(iChaosPermissionService.updateModelByMU(data));
    }

    @PostMapping("/list")
    @ManageLoginToken
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    public DataResult<List<ChaosPermission>> list(@RequestBody ChaosPermissionData data) throws Exception {
        return getResult(iChaosPermissionService.selectByData(data));
    }

    @PostMapping("/page")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public PageResult<ChaosPermission> page(@RequestBody PageQueryDto<ChaosPermissionData> data) throws Exception {
        return getResult(iChaosPermissionService.selectByPage(data));
    }

    @PostMapping("/delete")
    @ManageLoginToken
    @ApiOperation(value = "删除", notes = "", httpMethod = "POST")
    public DataResult<Boolean> delete(@RequestBody MU data) throws Exception {
        return getResult(iChaosPermissionService.deleteModel(data));
    }

    @PostMapping("/adminps")
    @ManageLoginToken
    @ApiOperation(value = "删除", notes = "", httpMethod = "POST")
    public DataResult<List<Menu>> adminps(HttpServletRequest request) {
        return getResult(iChaosPermissionService.selectPermissionByAdmin(getMnLoginMU(request)));
    }

    @PostMapping("/sortpage")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public PageResult<ChaosPermission> sortpage(@RequestBody PageQueryDto<ChaosPermissionData> data) throws Exception {
        return getResult(iChaosPermissionService.selectBySortPage(data));
    }


}
