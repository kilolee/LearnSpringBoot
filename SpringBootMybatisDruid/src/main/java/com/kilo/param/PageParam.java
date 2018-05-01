package com.kilo.param;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 分页基础类
 * Created by kilo on 2018/5/1.
 */
public class PageParam {
    private int beginLine;
    private Integer pageSize = 3;
    private Integer currentPage = 0;

    public int getBeginLine() {
        return currentPage * pageSize;
    }

    public void setBeginLine(int beginLine) {
        this.beginLine = beginLine;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage - 1;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
