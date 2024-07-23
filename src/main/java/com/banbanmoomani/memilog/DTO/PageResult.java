package com.banbanmoomani.memilog.DTO;

import java.util.List;

public class PageResult<T> {
    private List<T> data;
    private int total;

    public PageResult() {
    }

    public PageResult(List<T> data, int total) {
        this.data = data;
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
