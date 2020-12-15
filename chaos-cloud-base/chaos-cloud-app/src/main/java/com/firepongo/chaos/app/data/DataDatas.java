package com.firepongo.chaos.app.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author G.G
 * @date 2020/9/1 18:29
 */
@Data
@NoArgsConstructor
public class DataDatas<T> {
    private List<T> datas;
}
