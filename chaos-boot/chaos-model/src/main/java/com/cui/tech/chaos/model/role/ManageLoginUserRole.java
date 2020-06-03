package com.cui.tech.chaos.model.role;

import lombok.Data;

/**
 * @author G.G
 * @date 2020/5/14 12:05
 */
@Data
public class ManageLoginUserRole {
    private String name;

    private String info;

    private String indexLink;

    public ManageLoginUserRole() {
        this.name = Role.GUEST.name;
        this.info = Role.GUEST.info;
        this.indexLink = "/";
    }

    public ManageLoginUserRole(String name, String info, String indexLink) {
        this.name = name;
        this.info = info;
        this.indexLink = indexLink;
    }
}
