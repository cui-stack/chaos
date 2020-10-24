package me.goldze.mvvmhabit.base;

import me.goldze.mvvmhabit.http.BaseResponse;

/**
 * @author G.G
 * @date 2020/8/16 13:35
 */
public class PageResponse<T> extends BaseResponse {
    private PageList<T> page;

    public PageList<T> getPage() {
        return page;
    }

    public void setPage(PageList<T> page) {
        this.page = page;
    }
}
