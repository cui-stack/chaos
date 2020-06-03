package com.cui.tech.chaos.model.db;

import com.cui.tech.chaos.model.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
