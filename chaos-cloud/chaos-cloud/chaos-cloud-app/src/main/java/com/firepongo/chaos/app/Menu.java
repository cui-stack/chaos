package com.firepongo.chaos.app;

import lombok.Data;

import java.util.List;

/**
 * @author G
 * @date 2020/4/5 13:16
 */
@Data
public class Menu extends DTO {
    private String title;
    private String link;
    private String icon;
    private List<Menu> submenus;

    public Menu() {
    }

    public Menu(String title) {
        this.title = title;
    }

    public Menu(String title, String link) {
        this.title = title;
        this.link = link;
    }

    public Menu(String title, String icon, List<Menu> submenus) {
        this.title = title;
        this.icon = icon;
        this.submenus = submenus;
    }
}
