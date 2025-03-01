package com.fai.study.demoappchat.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageableData {
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private long totalRecords;

    public PageableData setPageNumber(final int pageNumber) {
        this.pageNumber = pageNumber + 1;
        return this;
    }

    public PageableData setPageSize(final int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public PageableData setTotalPages(final int totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public PageableData setTotalRecords(final long totalRecords) {
        this.totalRecords = totalRecords;
        return this;
    }
}
