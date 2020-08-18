package com.cui.tech.chaos.manage.controller;

import com.cui.tech.chaos.manage.api.data.ChaosPlatformData;
import com.cui.tech.chaos.manage.api.entity.ChaosPlatform;
import com.cui.tech.chaos.manage.api.service.IChaosPlatformService;
import com.cui.tech.chaos.model.db.MU;
import com.cui.tech.chaos.model.db.UpdateData;
import com.cui.tech.chaos.model.page.PageQueryDto;
import com.cui.tech.chaos.model.result.DataResult;
import com.cui.tech.chaos.model.result.PageResult;
import com.cui.tech.chaos.web.annotation.ManageLoginToken;
import com.cui.tech.chaos.web.base.BaseController;
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
*
* @author G.G
* @since 2020-04-23
*/
@Api(tags = "ChaosPlatformMnController")
@RestController
@RequestMapping("/manage/chaos_platform")
public class ChaosPlatformMnController extends BaseController {

    @Autowired
    private IChaosPlatformService iChaosPlatformService;

    @PostMapping("/add")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<MU> add(@RequestBody @Validated ChaosPlatformData data, BindingResult bindingResult) throws Exception {
        validate(bindingResult);
        return getResult(iChaosPlatformService.insertModel(data));
    }

    @PostMapping("/one")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<ChaosPlatform> one(@RequestBody MU data) throws Exception {
        return getResult(iChaosPlatformService.selectByMU(data));
    }

    @PostMapping("/update")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<Boolean> update( @ApiParam(value = "") @RequestBody @Validated UpdateData<ChaosPlatformData> data, BindingResult bindingResult) throws Exception {
        validate(bindingResult);
        return getResult(iChaosPlatformService.updateModelByMU(data));
    }

    @PostMapping("/list")
    @ManageLoginToken
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    public DataResult<List<ChaosPlatform>> list( @RequestBody ChaosPlatformData data) throws Exception {
        return getResult(iChaosPlatformService.selectByData(data));
    }

    @PostMapping("/page")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public PageResult<ChaosPlatform> page( @RequestBody PageQueryDto<ChaosPlatformData> data) throws Exception {
        return getResult(iChaosPlatformService.selectByPage(data));
    }

    @PostMapping("/delete")
    @ManageLoginToken
    @ApiOperation(value = "删除", notes = "", httpMethod = "POST")
    public DataResult<Boolean> delete( @RequestBody MU data) throws Exception {
        return getResult(iChaosPlatformService.deleteModel(data));
    }

}
