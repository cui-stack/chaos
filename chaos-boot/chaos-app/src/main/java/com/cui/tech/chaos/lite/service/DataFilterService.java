package com.cui.tech.chaos.lite.service;

import com.cui.tech.chaos.model.page.PageList;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author G.G
 * @date 2020/5/14 15:21
 */
@Component
public class DataFilterService<T> {
    public void filter(Function<String, Boolean> f, String rolename, List list, Consumer c) {
        if (f.apply(rolename)) {
            list.stream().
                    forEach(entity -> c.accept(entity));
        }
    }

    public void filter(Function<T, Boolean> f, T o, List list, Consumer c) {
        if (f.apply(o)) {
            list.stream().
                    forEach(entity -> c.accept(entity));
        }
    }

    public void filter(List list, Consumer c) {
        list.stream().
                forEach(entity -> c.accept(entity));
    }
}
