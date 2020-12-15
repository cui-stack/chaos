package com.firepongo.chaos.web.service.login.app;

import com.firepongo.chaos.web.service.login.BaseLoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AppLoginServiceImpl extends BaseLoginServiceImpl {
    @Autowired
    private AppLoginKeyService loginKeyService;


}
