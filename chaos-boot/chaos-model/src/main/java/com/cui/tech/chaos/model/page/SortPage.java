package com.cui.tech.chaos.model.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author G.G
 * @date 2020/5/9 11:58
 */
public class SortPage extends Page {

    public SortPage() {
    }

    public SortPage(long pageNum, long pageSize) {
        super(pageNum, pageSize);
    }

    @Override
    public long offset() {
        long start = (getCurrent() - 1) * getSize() - 1;
        long offset = getCurrent() > 0 ? (start > 0 ? start : 0) : 0;
        setSize(getSize() + (getCurrent() == 1 ? 1 : 2));
        return offset;
    }
}
