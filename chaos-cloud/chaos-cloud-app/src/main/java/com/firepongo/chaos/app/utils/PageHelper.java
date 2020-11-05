package com.firepongo.chaos.app.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.firepongo.chaos.app.page.PageQueryDto;
import org.springframework.stereotype.Component;

/**
 * @author G.G
 * @date 2020/7/2 21:28
 */
public class PageHelper {
    public static Page page(PageQueryDto query) {
        return new Page(query.getPageNum(), query.getPageSize());
    }

}
