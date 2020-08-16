package me.goldze.mvvmhabit.base;

/**
 * @author G.G
 * @date 2020/8/16 13:31
 */
public class PageQuery<T> {
    private Integer pageNum = 1;
    private Integer pageSize = 10;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    private T data;
}
