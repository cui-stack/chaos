package com.firepongo.chaos.gateway.filter;

import cn.hutool.core.util.StrUtil;
import com.firepongo.chaos.gateway.component.PathService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    private static Logger LOGGER = LoggerFactory.getLogger(AuthGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst("token");
        String path=exchange.getRequest().getPath().toString();
        if (StrUtil.isEmpty(token)) {
            return chain.filter(exchange);
        }
        //处理角色路径
        if(PathService.isManagePath(path)){

        }
        //处理登录类路径
        if(PathService.isApiPath(path)){

        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
