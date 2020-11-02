package com.firepongo.chaos.manage.controller;

import com.firepongo.chaos.manage.api.data.ChaosPlatformData;
import com.firepongo.chaos.manage.api.entity.ChaosPlatform;
import com.firepongo.chaos.manage.api.service.IChaosPlatformService;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.db.UpdateData;
import com.firepongo.chaos.app.page.PageQueryDto;
import com.firepongo.chaos.app.result.DataResult;
import com.firepongo.chaos.app.result.PageResult;
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
        return dataResult(iChaosPlatformService.insertModel(data));
    }

    @PostMapping("/one")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<ChaosPlatform> one(@RequestBody MU data) throws Exception {
        return dataResult(iChaosPlatformService.selectByMU(data));
    }

    @PostMapping("/update")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<Boolean> update( @ApiParam(value = "") @RequestBody @Validated UpdateData<ChaosPlatformData> data, BindingResult bindingResult) throws Exception {
        validate(bindingResult);
        return dataResult(iChaosPlatformService.updateModelByMU(data));
    }

    @PostMapping("/list")
    @ManageLoginToken
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    public DataResult<List<ChaosPlatform>> list( @RequestBody ChaosPlatformData data) throws Exception {
        return dataResult(iChaosPlatformService.selectByData(data));
    }

    @PostMapping("/page")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public PageResult<ChaosPlatform> page( @RequestBody PageQueryDto<ChaosPlatformData> data) throws Exception {
        return pageResult(iChaosPlatformService.selectByPage(data));
    }

    @PostMapping("/delete")
    @ManageLoginToken
    @ApiOperation(value = "删除", notes = "", httpMethod = "POST")
    public DataResult<Boolean> delete( @RequestBody MU data) throws Exception {
        return dataResult(iChaosPlatformService.deleteModel(data));
    }

}
