package com.firepongo.chaos.ws.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.firepongo.chaos.ws.data.PushMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author G.G
 * @date 2021/1/19 11:10
 */
@Service(value = "socketIOService")
public class SocketIOService {
    @Autowired
    private WsService wsService;

    private static Map<String, SocketIOClient> clientMap = new ConcurrentHashMap<>();

    @Autowired
    private SocketIOServer socketIOServer;

    @PostConstruct
    private void autoStartup() throws Exception {
        start();
    }

    @PreDestroy
    private void autoStop() throws Exception {
        stop();
    }

    public void start() throws Exception {
        socketIOServer.addConnectListener(client -> {
            if (client != null) {
                System.out.println("SessionId:  " + client.getSessionId());
                System.out.println("RemoteAddress:  " + client.getRemoteAddress());
                System.out.println("Transport:  " + client.getTransport());
                clientMap.put(client.getSessionId().toString(), client);
            }
        });

        socketIOServer.addDisconnectListener(client -> {
            if (client != null) {
                clientMap.remove(client.getSessionId().toString());
                System.out.println("断开连接： " + client.getSessionId());
                client.disconnect();
            }
        });

        // 处理自定义的事件，与连接监听类似
        socketIOServer.addEventListener("toServer", String.class, (client, data, ackSender) -> {
            wsService.toServer(data);
        });

        socketIOServer.start();
    }

    public void stop() {
        if (socketIOServer != null) {
            socketIOServer.stop();
            socketIOServer = null;
        }
    }

    public void pushMessageToUser(String uuid, PushMessage pushMessage) {
        if (StringUtils.isNotBlank(uuid)) {
            SocketIOClient client = clientMap.get(uuid);
            if (client != null) {
                client.sendEvent(pushMessage.getType(), pushMessage.getData());
            }
        }
    }

    public void pushMessageToAll(PushMessage pushMessage) {
        Map<String, SocketIOClient> map = getClientMap();
        map.keySet().stream().forEach((uuid) -> {
            SocketIOClient client = clientMap.get(uuid);
            if (client != null) {
                client.sendEvent(pushMessage.getType(), pushMessage.getData());
            }
        });

    }

    public static Map<String, SocketIOClient> getClientMap() {
        return clientMap;
    }

    public static void setClientMap(Map<String, SocketIOClient> clientMap) {
        SocketIOService.clientMap = clientMap;
    }
}
