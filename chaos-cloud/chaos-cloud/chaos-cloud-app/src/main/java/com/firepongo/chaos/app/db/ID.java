package com.firepongo.chaos.app.db;

import com.firepongo.chaos.app.DTO;
import lombok.Data;

/**
 * @author G.G
 * @date 2019/11/7 17:30
 */
@Data
public class ID extends DTO {
    private Integer id;

    public ID() {
    }

    public ID(Integer id) {
        this.id = id;
    }
}
