package com.cui.tech.code.service.ws;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.cui.tech.code.api.data.ws.WsMessage;
import com.cui.tech.code.api.data.ws.WsRoom;
import com.cui.tech.code.api.data.ws.WsUser;
import com.cui.tech.chaos.lite.service.SocketIOService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author C.J
 */
//@Service(value = "socketIOService")
@Slf4j
public class SocketIOServiceImpl implements SocketIOService {

    private static ConcurrentHashMap<String, WsRoom> allClients = new ConcurrentHashMap<>();
    @Autowired
    private SocketIOServer ioServer;

    @PostConstruct
    private void autoStartup() throws Exception {
        start();
    }

    @PreDestroy
    private void autoStop() throws Exception {
        stop();
    }

    @Override
    public void stop() {
        if (ioServer != null) {
            ioServer.stop();
            ioServer = null;
        }
    }

    @Override
    public void start() {
        ioServer.addConnectListener(client -> {
            doConnect(client);
            doStart(client);
        });

        // 监听客户端断开连接
        ioServer.addDisconnectListener(client -> {
            doDisconnect(client);
        });
        ioServer.addEventListener("user", String.class, (client, data, ackSender) -> {
            doUserlistEvent(client, data);
        });
        ioServer.addEventListener("update", WsMessage.class, (client, data, ackSender) -> {
            doUpdateEvent(client, data);
        });
        ioServer.addEventListener("notice", WsMessage.class, (client, data, ackSender) -> {
            doNoticeEvent(client, data);
        });
        ioServer.addEventListener("global", String.class, (client, data, ackSender) -> {
            doGlobalEvent(client, data);
        });

        ioServer.start();
    }

    private void doStart(SocketIOClient client) {
        WsUser user = client.get("user");
        client.joinRoom(user.getRoomid());
        WsRoom room = allClients.get(user.getRoomid());
        if (room == null) {
            room = new WsRoom();
            allClients.put(user.getRoomid(), room);
        }
        room.addUser(client);
        ConcurrentHashMap<String, SocketIOClient> users = room.getUsers();
        for (String userid : users.keySet()) {
            users.get(userid).sendEvent("user", room.getUsersDatas());
        }

        client.sendEvent("global", room.getGlobalData());
        log.info("有新窗口开始监听:" + "用户" + user.getUserid() + "房间" + user.getRoomid() + ",当前在线人数为" + room.getSize() + ",当前在线房间为" + allClients.keySet().size());
    }

    private void doDisconnect(SocketIOClient client) {
        WsUser user = client.get("user");
        user.setStatus("0");
        WsRoom room = allClients.get(user.getRoomid());
        room.delUser(client);
        ConcurrentHashMap<String, SocketIOClient> users = room.getUsers();
        for (String userid : users.keySet()) {
            users.get(userid).sendEvent("user", room.getUsersDatas());
        }
        client.disconnect();
        log.info("有一连接关闭！当前在线人数为" + room.getSize() + "当前在线房间为" + allClients.keySet().size());
        log.info("-----------------------------------------");
    }


    private void doGlobalEvent(SocketIOClient client, String data) {
        WsUser user = client.get("user");
        WsRoom room = allClients.get(user.getRoomid());
        room.setGlobalData(data);
        ConcurrentHashMap<String, SocketIOClient> users = room.getUsers();
        log.info("房间 :[{}], 用户 ：[{}], msg :[{}]", user.getRoomid(), user.getUserid(), data);

        for (String userid : users.keySet()) {
            if (!userid.equals(user.getUserid())) {
                users.get(userid).sendEvent("global", data);
            }
        }
    }

    //通知变动
    private void doNoticeEvent(SocketIOClient client, WsMessage data) {
        WsUser user = client.get("user");
        WsRoom room = allClients.get(user.getRoomid());
        ConcurrentHashMap<String, SocketIOClient> users = room.getUsers();
        log.info("房间 :[{}], 用户 ：[{}], msg :[{}]", user.getRoomid(), user.getUserid(), data);
        for (String touser : data.getTouid()) {
            room.changeUserData(touser, data.getData());
            users.get(touser).sendEvent("notice", data.getData());
        }
    }

    //同步变动
    private void doUpdateEvent(SocketIOClient client, WsMessage data) {
        WsUser user = client.get("user");
        WsRoom room = allClients.get(user.getRoomid());
        ConcurrentHashMap<String, SocketIOClient> users = room.getUsers();
        log.info("房间 :[{}], 用户 ：[{}], msg :[{}]", user.getUserid(), user.getRoomid(), data);
        room.changeUserData(data.getFromuid(), data.getData());
        for (String touser : data.getTouid()) {
            users.get(touser).sendEvent("update", data.getData());
        }
    }

    //用户事件:请求房间内用户列表
    private void doUserlistEvent(SocketIOClient client, String data) {
        WsUser user = client.get("user");
        WsRoom room = allClients.get(user.getRoomid());
        log.info("房间 :[{}], 用户 ：[{}]", user.getRoomid(), user.getUserid());
        client.sendEvent("user", room.getUsersDatas());
    }


    private void doConnect(SocketIOClient client) {
        Map<String, List<String>> params = client.getHandshakeData().getUrlParams();
        WsUser user = new WsUser();
        List<String> roomid = params.get("roomid");
        if (roomid != null && roomid.size() > 0) {
            user.setRoomid(roomid.get(0));
        }
        List<String> userid = params.get("userid");
        if (userid != null && userid.size() > 0) {
            user.setUserid(userid.get(0));
        }
        List<String> username = params.get("username");
        if (username != null && username.size() > 0) {
            user.setUsername(username.get(0));
        }
        List<String> role = params.get("role");
        if (role != null && role.size() > 0) {
            user.setRole(role.get(0));
        }
        user.setStatus("1");
        user.setData("{}");
        client.set("user", user);

    }


}
