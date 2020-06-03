package com.cui.tech.chaos.model.role;

/**
 * @author G.G
 * @date 2020/5/14 12:12
 */
public enum Role {
    GUEST(RoleConstant.GUEST, "游客"),
    ADMIN(RoleConstant.ADMIN, "超级管理员"),
    OP(RoleConstant.OP, "运营"),
    TM(RoleConstant.TM, "流量主");
    public String name;
    public String info;

    Role(String name, String info) {
        this.name = name;
        this.info = info;
    }
}
