package com.lizzy.demo.resp;

import java.util.List;

//Resp是我们手写的工具类,一般是架构师写的，不会让敏捷开发人员（码农）去写
public class PageResp<T> {
    private long total;

    private List<T> list;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageResp{" +
                "total=" + total +
                ", list=" + list +
                '}';
    }
}
