package com.firepongo.chaos.app.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author G.G
 * @date 2020/9/1 18:29
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class XyData<X,Y> {
    private X x;
    private Y y;
    private String type;

}
