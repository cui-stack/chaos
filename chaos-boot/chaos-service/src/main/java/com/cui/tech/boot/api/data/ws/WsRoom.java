package com.cui.tech.boot.api.data.ws;

import com.corundumstudio.socketio.SocketIOClient;
import lombok.Data;

import java.util.concurrent.ConcurrentHashMap;

@Data
public class WsRoom {
    private ConcurrentHashMap<String, SocketIOClient> users;
    private ConcurrentHashMap<String, WsUser> usersDatas;
    private String globalData;
    private Integer size;
    private String status;//0不在线 1 在线

    public WsRoom() {
        users = new ConcurrentHashMap<String, SocketIOClient>();
        usersDatas = new ConcurrentHashMap<String, WsUser>();
        globalData = "{}";
        size = users.size();
        status = "0";
    }

    public void addUser(SocketIOClient client) {
        WsUser user = client.get("user");
        users.put(user.getUserid(), client);
        usersDatas.put(user.getUserid(), user);
        size = users.size();
    }

    public void delUser(SocketIOClient client) {
        WsUser user = client.get("user");
        users.remove(user.getUserid());
        usersDatas.remove(user.getUserid());
        size = users.size();
    }

    public void changeUserData(String userid, String data) {
        usersDatas.get(userid).setData(data);
    }
}
