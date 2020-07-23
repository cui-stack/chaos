package com.cui.tech.service.service.facade;

import com.alibaba.dubbo.config.annotation.Service;
import com.cui.tech.service.api.facade.IFacade;

/**
 * @author G.G
 * @date 2020/1/10 11:58
 */
@Service
public class Facade implements IFacade {
    @Override
    public void ii() {
        System.out.println("ii");
    }
}
