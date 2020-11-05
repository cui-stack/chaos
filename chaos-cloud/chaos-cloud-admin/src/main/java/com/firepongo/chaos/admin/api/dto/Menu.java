package com.firepongo.chaos.admin.api.dto;

import com.firepongo.chaos.app.DTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author G
 * @date 2020/4/5 13:16
 */
@Data
@RequiredArgsConstructor(staticName = "of")
@Accessors(chain = true)
public class Menu extends DTO {
    private String title;
    private String link;
    private String icon;
    private List<Menu> submenus;
}
