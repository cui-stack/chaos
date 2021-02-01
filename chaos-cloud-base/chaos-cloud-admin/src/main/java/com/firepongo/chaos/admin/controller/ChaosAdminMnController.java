package com.firepongo.chaos.admin.controller;

import com.firepongo.chaos.app.admin.ChaosRoleData;
import com.firepongo.chaos.admin.service.impl.ChaosAdminServiceImpl;
import com.firepongo.chaos.admin.service.tran.AdminTranService;
import com.firepongo.chaos.app.admin.ChaosAdminData;
import com.firepongo.chaos.app.db.MU;
import com.firepongo.chaos.app.db.UpdateData;
import com.firepongo.chaos.app.login.manage.IMnLoginUserService;
import com.firepongo.chaos.app.login.manage.ManageLoginDto;
import com.firepongo.chaos.app.login.manage.ManageLoginUser;
import com.firepongo.chaos.app.page.PageQueryDto;
import com.firepongo.chaos.app.result.ResultMsg;
import com.firepongo.chaos.app.result.data.DataResult;
import com.firepongo.chaos.app.result.page.PageResult;
import com.firepongo.chaos.web.annotation.ManageLoginToken;
import com.firepongo.chaos.web.base.BaseController;
import com.firepongo.chaos.web.helper.IpUtil;
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
@Api(tags = "ChaosAdminMnController")
@RestController
@RequestMapping("/manage/chaos_admin")
public class ChaosAdminMnController extends BaseController {

    @Autowired
    private ChaosAdminServiceImpl iChaosAdminService;

    @Autowired
    private AdminTranService adminTranService;
    @Autowired
    private IMnLoginUserService iMnLoginUserService;

    @PostMapping("/add")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<MU> add(@RequestBody @Validated ChaosAdminData data, BindingResult bindingResult, HttpServletRequest request) throws Exception {
        validate(bindingResult);
        data.setIp(IpUtil.getIpAddr(request));
        return dataResult(adminTranService.addAdmin(data), new ResultMsg().setFailure("新增失败,请检查数据!"));
    }

    @PostMapping("/one")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<ChaosAdminData> one(@RequestBody MU data) throws Exception {
        return dataResult(iChaosAdminService.selectByMU(data));
    }

    @PostMapping("/update")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<Boolean> update(@ApiParam(value = "") @RequestBody @Validated UpdateData<ChaosAdminData> data, BindingResult bindingResult) throws Exception {
        validate(bindingResult);
        return dataResult(iChaosAdminService.updateModelByMU(data));
    }

    @PostMapping("/updateLoginLog")
    @ManageLoginToken
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<Boolean> updateLoginLog(@ApiParam(value = "") @RequestBody @Validated UpdateData<ChaosAdminData> data, BindingResult bindingResult) throws Exception {
        validate(bindingResult);
        return dataResult(iChaosAdminService.updateLoginLog(data));
    }

    @PostMapping("/list")
    @ManageLoginToken
    @ApiOperation(value = "列表", notes = "", httpMethod = "POST")
    public DataResult<List<ChaosAdminData>> list(@RequestBody ChaosAdminData data) throws Exception {
        return dataResult(iChaosAdminService.selectByData(data));
    }

    @ManageLoginToken
    @PostMapping("/page")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public PageResult<ChaosAdminData> page(@RequestBody PageQueryDto<ChaosAdminData> data) throws Exception {
        return pageResult(iChaosAdminService.selectByPage(data));
    }

    @ManageLoginToken
    @PostMapping("/delete")
    @ApiOperation(value = "删除", notes = "", httpMethod = "POST")
    public DataResult<Boolean> delete(@RequestBody MU data) throws Exception {
        return dataResult(adminTranService.deleteAdmin(data));
    }

    @ManageLoginToken
    @PostMapping("/index")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<ChaosRoleData> index(HttpServletRequest request) throws Exception {
        return dataResult(iChaosAdminService.selectIndexLink(getMnLoginUserByToken(getToken(request)).getMu()));
    }

    @ManageLoginToken
    @PostMapping("/adminRole")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<ChaosAdminData> adminRole(@RequestBody MU data) throws Exception {
        return dataResult(iChaosAdminService.selectAdminRoleByMU(data));
    }

    @ManageLoginToken
    @PostMapping("/listAdminRole")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<ChaosAdminData> listAdminRole(@RequestBody ChaosAdminData data) throws Exception {
        return dataResult(iChaosAdminService.listAdminRole(data));
    }

    @ManageLoginToken
    @PostMapping("/updateAdminRole")
    @ApiOperation(value = "", notes = "", httpMethod = "POST")
    public DataResult<Boolean> updateAdminRole(@ApiParam(value = "") @RequestBody @Validated UpdateData<ChaosAdminData> data, BindingResult bindingResult) throws Exception {
        validate(bindingResult);
        return dataResult(adminTranService.updateAdminRole(data));
    }

    @ManageLoginToken
    @PostMapping("/updateByPassword")
    @ApiOperation(value = "更新用户", notes = "", httpMethod = "POST")
    public DataResult updateByPassword(
            @RequestBody @Validated UpdateData<ChaosAdminData> data, BindingResult bindingResult, HttpServletRequest request) throws Exception {
        validate(bindingResult);
        boolean flag = iChaosAdminService.updateModelByPassword(data);
        if (!flag) {
            return dataResult("密码错误,更新失败!");
        }
        return dataResult(true);

    }

    @ManageLoginToken
    @PostMapping("/selectByUp")
    public DataResult<ManageLoginUser> selectByUsernameAndPassword(@RequestBody ManageLoginDto loginDto) {
        return dataResult(adminTranService.getManageLoginUser(loginDto));
    }

    @ManageLoginToken
    @PostMapping("/doPhoneLogin")
    public DataResult<ManageLoginUser> doPhoneLogin(@RequestBody ManageLoginDto loginDto) {
        ManageLoginUser user = iMnLoginUserService.selectByPhone(loginDto);
        if (user == null) {
            user = adminTranService.doInitAdminByPhone(loginDto);
        }
        adminTranService.getManageLoginRole(user);
        return dataResult(user);
    }


}
