package com.firepongo.chaos.app.login.manage;

import com.firepongo.chaos.app.DTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author G
 * @date 2020/4/5 13:16
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@Accessors(chain = true)
public class ManageMenu extends DTO {
    @NonNull
    private String title;
    private String link;
    private String icon;
    private List<ManageMenu> submenus;
}
