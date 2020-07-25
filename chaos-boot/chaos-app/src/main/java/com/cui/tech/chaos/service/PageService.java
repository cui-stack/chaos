package com.cui.tech.chaos.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cui.tech.chaos.model.page.PageQueryDto;
import org.springframework.stereotype.Component;

/**
 * @author G.G
 * @date 2020/7/2 21:28
 */
@Component
public class PageService {
    public Page page(PageQueryDto query) {
        return new Page(query.getPageNum(), query.getPageSize());
    }
}
