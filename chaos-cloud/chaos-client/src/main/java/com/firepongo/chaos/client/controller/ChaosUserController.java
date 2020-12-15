package com.firepongo.chaos.client.controller;

import com.firepongo.chaos.api.data.ChaosUserData;
import com.firepongo.chaos.api.service.IChaosUserService;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.page.PageQueryDto;
import com.firepongo.chaos.app.result.data.DataResult;
import com.firepongo.chaos.app.result.page.PageResult;
import com.firepongo.chaos.web.annotation.WxminiLoginToken;
import com.firepongo.chaos.web.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
*
* @author G.G
* @since 2020-11-17
*/
@Slf4j
@Api(tags = "OrderUserController")
@RestController
@RequestMapping("/api/orderUser")
public class ChaosUserController extends BaseController {
    @Reference
    private IChaosUserService iChaosUserService;

    @PostMapping("/one")
    @WxminiLoginToken
    @ApiOperation(value = "单条", notes = "", httpMethod = "POST")
    public DataResult<ChaosUserData> one(
        @RequestBody MU data) throws Exception {
    return dataResult(iChaosUserService.selectByMU(data));
    }

    @PostMapping("/list")
    @WxminiLoginToken
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    public DataResult<List<ChaosUserData>> list(@RequestBody ChaosUserData data) throws Exception {
         return dataResult(iChaosUserService.selectByData(data));
    }

    @PostMapping("/page")
    @WxminiLoginToken
    @ApiOperation(value = "分页", notes = "", httpMethod = "POST")
    public PageResult<ChaosUserData> page(@RequestBody PageQueryDto<ChaosUserData> data) throws Exception {
        return pageResult(iChaosUserService.selectByPage(data));
    }

}
