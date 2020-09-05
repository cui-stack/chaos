package com.cui.tech.chaos.web.base;


import com.cui.tech.chaos.model.db.DATA;
import com.cui.tech.chaos.model.exception.BusinessException;
import com.cui.tech.chaos.model.login.ManageLoginUser;
import com.cui.tech.chaos.model.login.WxMiniLoginUser;
import com.cui.tech.chaos.model.page.PageList;
import com.cui.tech.chaos.model.result.*;
import com.cui.tech.chaos.web.service.ManageLoginKeyService;
import com.cui.tech.chaos.web.service.WxminiLoginKeyService;
import com.cui.tech.chaos.web.service.helper.JWTHelper;
import com.cui.tech.chaos.web.service.helper.RedisHelper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

/**
 * @author G.G
 * 基础控制类，所有的Controller都规范继承此类，一些公用的代码后续可以在此扩展
 * @version 1.0
 * @date 2018/10/12
 */
public abstract class BaseController<T> {
    @Autowired
    private RedisHelper redisHelper;
    @Autowired
    private JWTHelper jwtHelper;

    @Autowired
    private WxminiLoginKeyService wxminiLoginKeyService;
    @Autowired
    private ManageLoginKeyService manageLoginKeyService;

    public String getToken(HttpServletRequest request) {
        return request.getHeader("token");
    }

    public ManageLoginUser getMnLoginUser(HttpServletRequest request) {
        String token = getToken(request);
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        return getMnLoginUserByToken(token);
    }

    public ManageLoginUser getMnLoginUserByToken(String token) {
        String key = manageLoginKeyService.key(jwtHelper.getUserMuInJwtData(token));
        return (ManageLoginUser) redisHelper.get(key);
    }

    public WxMiniLoginUser getWxLoginUser(HttpServletRequest request) {
        String token = getToken(request);
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        return (WxMiniLoginUser) getWxLoginUserByToken(token);
    }

    public WxMiniLoginUser getWxLoginUserByToken(String token) {
        String key = wxminiLoginKeyService.key(token);
        return (WxMiniLoginUser) redisHelper.get(key);
    }

    public String getMnLoginRole(HttpServletRequest request) {
        ManageLoginUser user = getMnLoginUser(request);
        if (user == null) {
            return "";
        }
        return user.getRole().getName();

    }

    public String getMnLoginMU(HttpServletRequest request) {
        ManageLoginUser user = getMnLoginUser(request);
        if (user == null) {
            return "";
        }
        return user.getMu();
    }

    public String getWxLoginMU(HttpServletRequest request) {
        WxMiniLoginUser user = getWxLoginUser(request);
        if (user == null) {
            return "";
        }
        return user.getMu();
    }

    public Integer getWxLoginID(HttpServletRequest request) {
        WxMiniLoginUser user = getWxLoginUser(request);
        if (user == null) {
            return -1;
        }
        return user.getId();
    }

    public DataResult getResult(BindingResult bindingResult, boolean methodEnd) {
        return getResult(bindingResult, methodEnd, ResultEnum.FAILURE.getDefaultMsg());
    }

    public DataResult getResult(BindingResult bindingResult, boolean methodEnd, String failureMsg) {
        DataResult dataResult = new DataResult();
        if (bindingResult.hasErrors()) {
            dataResult.failure("500", String.join(",", bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList())));
            return dataResult;
        }
        boolean flag = methodEnd;
        if (!flag) {
            dataResult.failure(failureMsg);
        }
        dataResult.setData(methodEnd);
        return dataResult;
    }

    public DataResult getResult(boolean methodEnd) {
        return getResult(methodEnd, ResultEnum.FAILURE.getDefaultMsg());
    }

    public DataResult getResult(boolean methodEnd, String failureMsg) {
        DataResult dataResult = new DataResult();
        dataResult.setData(methodEnd);
        if (!methodEnd) {
            dataResult.failure(failureMsg);
        }
        return dataResult;
    }

    public DataResult getResult(BindingResult bindingResult, T methodReturn) {
        return getResult(bindingResult, methodReturn, ResultEnum.FAILURE.getDefaultMsg());
    }

    public DataResult getResult(BindingResult bindingResult, T methodReturn, String failureMsg) {
        DataResult dataResult = new DataResult();
        if (bindingResult.hasErrors()) {
            dataResult.failure("500", String.join(",", bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList())));
            return dataResult;
        }
        if (methodReturn == null) {
            dataResult.failure(failureMsg);
        }
        dataResult.setData(methodReturn);
        return dataResult;
    }

    public PageResult<T> getResult(PageList<T> pageList) {
        PageResult<T> listResult = new PageResult<T>();
        listResult.setPage(pageList);
        return listResult;
    }

    public MarkPageResult<T> getResult(PageList<T> pageList, String mark) {
        MarkPageResult<T> listResult = new MarkPageResult<T>();
        listResult.setPage(pageList);
        listResult.setMark(mark);
        return listResult;
    }

    public MarkPagesResult<T> getResult(PageList<T> pl1, PageList<T> pl2, String mark) {
        MarkPagesResult<T> mpr = new MarkPagesResult<T>();
        mpr.setPages(Lists.newArrayList(pl1, pl2));
        mpr.setMark(mark);
        return mpr;
    }

    public DataResult<T> getResult(T data) {
        return getResult(data, false);
    }

    public DataResult<T> getResult(T data, boolean isBlank) {
        DataResult dataResult = new DataResult();
        if (isBlank && data == null) {
            dataResult.setData(new DATA());
        } else {
            dataResult.setData(data);
        }
        return dataResult;
    }

    public void validate(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BusinessException(ResultEnum.FAILURE, String.join(",", bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList())));
        }

    }

}
