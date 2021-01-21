package com.firepongo.chaos.ws.config;

import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;

/**
 * @author G.G
 * @date 2021/1/19 11:06
 */
@Configuration
public class SocketIOConfig {

    @Value("${socketio.host:localhost}")
    private String host;

    @Value("${socketio.port:9099}")
    private Integer port;

    @Value("${socketio.bossCount:1}")
    private int bossCount;

    @Value("${socketio.workCount:100}")
    private int workCount;

    @Value("${socketio.allowCustomRequests:true}")
    private boolean allowCustomRequests;

    @Value("${socketio.upgradeTimeout:1000000}")
    private int upgradeTimeout;

    @Value("${socketio.pingTimeout:6000000}")
    private int pingTimeout;

    @Value("${socketio.pingInterval:25000}")
    private int pingInterval;

    @Bean
    public SocketIOServer socketIOServer() {
        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setTcpNoDelay(true);
        socketConfig.setSoLinger(0);
        socketConfig.setReuseAddress(true);
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setSocketConfig(socketConfig);
        config.setHostname(host);
        config.setPort(port);
        //允许最大帧长度
        config.setMaxFramePayloadLength(1024 * 1024);
        //允许下最大内容
        config.setMaxHttpContentLength(1024 * 1024);
        config.setBossThreads(bossCount);
        config.setWorkerThreads(workCount);
        config.setAllowCustomRequests(allowCustomRequests);
        config.setUpgradeTimeout(upgradeTimeout);
        config.setPingTimeout(pingTimeout);
        config.setPingInterval(pingInterval);
        return new SocketIOServer(config);
    }

}
