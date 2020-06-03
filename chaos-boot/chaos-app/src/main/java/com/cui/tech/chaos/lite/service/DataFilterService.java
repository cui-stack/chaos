package com.cui.tech.chaos.lite.service;

import com.cui.tech.chaos.model.page.PageList;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author G.G
 * @date 2020/5/14 15:21
 */
@Component
public class DataFilterService<T> {
    public void filter(Function<String, Boolean> f, String rolename, PageList page, Consumer c) {
        if (f.apply(rolename)) {
            page.getList().stream().
                    forEach(entity -> c.accept(entity));
        }
    }

    public void filter(Function<T, Boolean> f, T o, PageList page, Consumer c) {
        if (f.apply(o)) {
            page.getList().stream().
                    forEach(entity -> c.accept(entity));
        }
    }

    public void filter(PageList page, Consumer c) {
        page.getList().stream().
                forEach(entity -> c.accept(entity));
    }
}
