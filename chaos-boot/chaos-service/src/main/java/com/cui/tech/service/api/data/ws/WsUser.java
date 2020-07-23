package com.cui.tech.service.api.data.ws;

import lombok.Data;

@Data
public class WsUser {
    private String roomid;
    private String userid;
    private String username;
    private String role;
    private String data;
    private String status;//0不在线 1 在线
}
