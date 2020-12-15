package com.firepongo.chaos.app.db;

import com.firepongo.chaos.app.DTO;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author G.G
 * @date 2020/2/26 15:57
 */
@Data
@NoArgsConstructor
public class OrderByData extends DTO {
    private String orderBy = Table.ID;
}
