package com.firepongo.chaos.app.db;

import com.firepongo.chaos.app.DTO;
import lombok.Data;

/**
 * @author G.G
 * @date 2020/2/26 15:57
 */
@Data
public class OrderByData extends DTO {
    private String orderBy = Table.ID;
}
