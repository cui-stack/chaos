package com.cui.tech.chaos.model.db;

import com.cui.tech.chaos.model.DTO;
import lombok.Data;

/**
 * @author G.G
 * @date 2020/2/26 15:57
 */
@Data
public class OrderByData extends DTO {
    private String orderBy = Table.ID;
}
