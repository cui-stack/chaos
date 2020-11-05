package com.firepongo.chaos.app.db;


import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author G.G
 * @date 2020/2/26 15:56
 */
@Data
@Accessors(chain = true)
public class UpdateData<T> extends MU {
    private T data;

}
