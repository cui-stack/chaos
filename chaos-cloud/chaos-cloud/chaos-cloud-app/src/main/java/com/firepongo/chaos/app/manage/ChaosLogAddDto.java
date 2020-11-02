package com.firepongo.chaos.app.manage;

import com.firepongo.chaos.app.login.ManageToken;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChaosLogAddDto extends ManageToken {

    private String userMuToken;

    private String ip;

    private String uri;

    private Long time;

    private String request;

    private String response;

    public ChaosLogAddDto(String token, String userMuToken, String ip, String uri, Long time, String request, String response) {
        super(token);
        this.userMuToken = userMuToken;
        this.ip = ip;
        this.uri = uri;
        this.time = time;
        this.request = request;
        this.response = response;
    }

}
