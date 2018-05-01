package com.kilo.result;

import com.kilo.param.PageParam;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 分页封装数据
 * Created by kilo on 2018/5/1.
 */
public class Page<E> implements Serializable {
    //当前页数
    private int currentPage = 1;
    //总页数
    private long totalPage;
    //总记录数
    private long totalNumber;
    //数据集
    private List<E> list;

    public Page() {
    }


    public Page(int beginLine, int pageSize, long totalNumber, List<E> list) {
        this.currentPage = beginLine / pageSize + 1;
        this.totalPage = totalNumber % pageSize == 0 ? totalNumber / pageSize : totalNumber / pageSize + 1;
        this.totalNumber = totalNumber;
        this.list = list;
    }

    public Page(PageParam pageParam, long totalNumber, List<E> list) {
        this.currentPage = pageParam.getCurrentPage() + 1;
        this.totalPage = totalNumber % pageParam.getPageSize() == 0 ? totalNumber / pageParam.getPageSize() : totalNumber / pageParam.getPageSize() + 1;
        this.totalNumber = totalNumber;
        this.list = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public long getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(long totalNumber) {
        this.totalNumber = totalNumber;
    }

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
