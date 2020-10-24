package me.goldze.mvvmhabit.base;

import java.util.List;

/**
 * @author G.G
 * @date 2020/8/16 13:37
 */
public class PageList<T> {
    private List<T> list;
    private Long total;
    private Long current;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getCurrent() {
        return current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }
}
