package com.cui.tech.code.api.data.ws;

import lombok.Data;

@Data
public class WsMessage {
    private String fromuid;
    private String[] touid ;
    private String data;
}
