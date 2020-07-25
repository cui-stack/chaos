package com.cui.tech.mh.model.dto;

/**
 * @author G.G
 * @date 2020/6/28 23:08
 */
public class LoginDto {
    private String username;
    private String password;

    public LoginDto() {
    }

    public LoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }


}
