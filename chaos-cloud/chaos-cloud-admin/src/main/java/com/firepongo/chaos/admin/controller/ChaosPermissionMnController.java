package com.firepongo.chaos.admin.controller;

import com.firepongo.chaos.admin.api.data.ChaosPermissionData;
import com.firepongo.chaos.admin.api.entity.ChaosPermission;
import com.firepongo.chaos.admin.api.service.IChaosPermissionService;
import com.firepongo.chaos.admin.api.dto.Menu;
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
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<MU> add(@RequestBody @Validated ChaosPermissionData data, BindingResult bindingResult) throws Exception {
        validate(bindingResult);
        return dataResult(iChaosPermissionService.insertModel(data));
    }

    @PostMapping("/one")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<ChaosPermission> one(@RequestBody MU data) throws Exception {
        return dataResult(iChaosPermissionService.selectByMU(data));
    }

    @PostMapping("/update")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<Boolean> update(@ApiParam(value = "") @RequestBody @Validated UpdateData<ChaosPermissionData> data, BindingResult bindingResult) throws Exception {
        validate(bindingResult);
        return dataResult(iChaosPermissionService.updateModelByMU(data));
    }

    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    public DataResult<List<ChaosPermission>> list(@RequestBody ChaosPermissionData data) throws Exception {
        return dataResult(iChaosPermissionService.selectByData(data));
    }

    @PostMapping("/page")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public PageResult<ChaosPermission> page(@RequestBody PageQueryDto<ChaosPermissionData> data) throws Exception {
        return pageResult(iChaosPermissionService.selectByPage(data));
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "", httpMethod = "POST")
    public DataResult<Boolean> delete(@RequestBody MU data) throws Exception {
        return dataResult(iChaosPermissionService.deleteModel(data));
    }

    @PostMapping("/adminps")
    @ApiOperation(value = "删除", notes = "", httpMethod = "POST")
    public DataResult<List<Menu>> adminps(HttpServletRequest request) {
        return dataResult(iChaosPermissionService.selectPermissionByAdmin(getMnLoginMU(request)));
    }

    @PostMapping("/sortpage")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public PageResult<ChaosPermission> sortpage(@RequestBody PageQueryDto<ChaosPermissionData> data) throws Exception {
        return pageResult(iChaosPermissionService.selectBySortPage(data));
    }


}
