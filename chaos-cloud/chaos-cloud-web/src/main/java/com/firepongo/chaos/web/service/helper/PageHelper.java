package com.firepongo.chaos.web.service.helper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.firepongo.chaos.app.page.PageQueryDto;
import org.springframework.stereotype.Component;

/**
 * @author G.G
 * @date 2020/5/9 11:46
 */
@Component
public class PageHelper {

    public Page page(PageQueryDto page) {
        return new Page(page.getPageNum(), page.getPageSize());
    }
}
