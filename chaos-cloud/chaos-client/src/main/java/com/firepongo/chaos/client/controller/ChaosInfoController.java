package com.firepongo.chaos.client.controller;

import com.firepongo.chaos.api.data.ChaosInfoData;
import com.firepongo.chaos.api.service.IChaosInfoService;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.page.PageQueryDto;
import com.firepongo.chaos.app.result.data.DataResult;
import com.firepongo.chaos.app.result.page.PageResult;
import com.firepongo.chaos.web.annotation.WxminiLoginToken;
import com.firepongo.chaos.web.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
*
* @author G.G
* @since 2020-12-18
*/
@Slf4j
@Api(tags = "ChaosInfoController")
@RestController
@RequestMapping("/api/chaosInfo")
public class ChaosInfoController extends BaseController {
    @Reference
    private IChaosInfoService iChaosInfoService;

    @PostMapping("/one")
    //@WxminiLoginToken
    @ApiOperation(value = "单条", notes = "", httpMethod = "POST")
    public DataResult<ChaosInfoData> one(
        @RequestBody MU data) throws Exception {
    return dataResult(iChaosInfoService.selectByMU(data));
    }

    @PostMapping("/list")
    //@WxminiLoginToken
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    public DataResult<List<ChaosInfoData>> list( @RequestBody ChaosInfoData data) throws Exception {
         return dataResult(iChaosInfoService.selectByData(data));
    }

    @PostMapping("/page")
    //@WxminiLoginToken
    @ApiOperation(value = "分页", notes = "", httpMethod = "POST")
    public PageResult<ChaosInfoData> page( @RequestBody PageQueryDto<ChaosInfoData> data) throws Exception {
        return pageResult(iChaosInfoService.selectByPage(data));
    }

}
