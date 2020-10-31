package com.cui.tech.chaos.model.data;

import lombok.Data;

import java.util.List;

/**
 * @author G.G
 * @date 2020/9/1 18:29
 */
@Data
public class DataDatas<T> {
    private List<T> datas;
}
