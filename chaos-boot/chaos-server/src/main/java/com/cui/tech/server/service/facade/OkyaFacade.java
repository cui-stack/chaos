package com.cui.tech.code.service.facade;

import com.alibaba.dubbo.config.annotation.Service;
import com.cui.tech.code.api.facade.IOkyaFacade;

/**
 * @author G.G
 * @date 2020/1/10 11:58
 */
@Service
public class OkyaFacade implements IOkyaFacade {
    @Override
    public void ii() {
        System.out.println("ii");
    }
}
