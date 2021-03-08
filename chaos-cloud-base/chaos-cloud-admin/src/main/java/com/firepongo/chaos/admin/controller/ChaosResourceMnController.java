package com.firepongo.chaos.admin.controller;

import com.firepongo.chaos.admin.api.data.ChaosResourceData;
import com.firepongo.chaos.admin.api.entity.ChaosResource;
import com.firepongo.chaos.admin.api.service.IChaosResourceService;
import com.firepongo.chaos.admin.service.tran.AdminTranService;
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
@Api(tags = "ChaosResourceMnController")
@RestController
@RequestMapping("/manage/chaos_resource")
public class ChaosResourceMnController extends BaseController {

    @Autowired
    private IChaosResourceService iChaosResourceService;

    @Autowired
    private AdminTranService adminTranService;

    @ManageLoginToken
    @PostMapping("/add")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<MU> add(@RequestBody @Validated ChaosResourceData data, BindingResult bindingResult) throws Exception {
        validate(bindingResult);
        return dataResult(iChaosResourceService.insertModel(data));
    }

    @ManageLoginToken
    @PostMapping("/one")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<ChaosResource> one(@RequestBody MU data) throws Exception {
        return dataResult(iChaosResourceService.selectByMU(data));
    }

    @ManageLoginToken
    @PostMapping("/update")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<Boolean> update(@ApiParam(value = "") @RequestBody @Validated UpdateData<ChaosResourceData> data, BindingResult bindingResult) throws Exception {
        validate(bindingResult);
        return dataResult(iChaosResourceService.updateModelByMU(data));
    }

    @ManageLoginToken
    @PostMapping("/list")
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    public DataResult<List<ChaosResource>> list(@RequestBody ChaosResourceData data) throws Exception {
        return dataResult(iChaosResourceService.selectByData(data));
    }

    @ManageLoginToken
    @PostMapping("/page")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public PageResult<ChaosResource> page(@RequestBody PageQueryDto<ChaosResourceData> data) throws Exception {
        return pageResult(iChaosResourceService.selectByPage(data));
    }

    @ManageLoginToken
    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "", httpMethod = "POST")
    public DataResult<Boolean> delete(@RequestBody MU data) throws Exception {
        return dataResult(adminTranService.deleteResource(data));
    }

    @ManageLoginToken
    @PostMapping("/sortpage")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public PageResult<ChaosResource> sortpage(@RequestBody PageQueryDto<ChaosResourceData> data) throws Exception {
        return pageResult(iChaosResourceService.selectBySortPage(data));
    }


}
