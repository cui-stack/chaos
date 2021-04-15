package com.firepongo.chaos.app.service;

import com.firepongo.chaos.app.db.DATA;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author G.G
 * @date 2020/7/2 21:33
 */
@Component
public class MuModelService<T extends DATA> {
    public List<String> getMus(List<T> list) {
        return list.stream().map(DATA::getMu).collect(Collectors.toList());
    }

}
