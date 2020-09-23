package com.cui.tech.chaos.model.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cui.tech.chaos.model.page.PageQueryDto;
import org.springframework.data.domain.PageRequest;
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

    public PageRequest pageRequest(PageQueryDto query){
        return PageRequest.of(query.getPageNum()-1, query.getPageSize());
    }
}
