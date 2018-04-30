package com.kilo.result;

import com.kilo.param.PageParam;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 分页封装数据
 * Created by kilo on 2018/4/30.
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

    /**
     * @param beginLine   起始行数
     * @param totalNumber 总记录数
     * @param pageSize    页大小
     * @param list        页数据
     */
    public Page(int beginLine, long totalNumber, int pageSize, List<E> list) {
        this.currentPage = beginLine / pageSize + 1;
        this.totalNumber = totalNumber;
        this.list = list;
        this.totalPage = totalNumber % pageSize == 0 ? 1 : totalNumber % pageSize + 1;
    }

    public Page(PageParam pageParam, long totalNumber, List<E> list) {
        this.currentPage = pageParam.getCurrentPage()+1;
        this.totalNumber = totalNumber;
        this.list = list;
        this.totalPage = totalNumber % pageParam.getPageSize() == 0 ? 1 : totalNumber % pageParam.getPageSize() + 1;
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
