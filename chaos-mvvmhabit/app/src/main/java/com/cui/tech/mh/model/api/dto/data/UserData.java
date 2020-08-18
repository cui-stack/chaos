package com.cui.tech.mh.model.api.dto.data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author G.G
 * @date 2020/8/16 13:26
 */
public class UserData {
    private Integer id;

    private String userUnique;


    private String phone;


    private String profilePhoto;


    private String name;


    private LocalDate birthday;


    private Integer sex;


    private String role;


    private LocalDateTime registerDate;

    private LocalDateTime lastLoginDate;

    private Integer isVip;

}
