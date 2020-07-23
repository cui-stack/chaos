package com.cui.tech.boot.api.data.ws;

import lombok.Data;

@Data
public class WsMessage {
    private String fromuid;
    private String[] touid ;
    private String data;
}
