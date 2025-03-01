package com.fai.study.demoappchat.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PagingResponse<T>{
    private List<T> contents;
    private PageableData paging;

    public PagingResponse<T> setContents(final List<T> contents) {
        this.contents = contents;
        return this;
    }

    public PagingResponse<T> setPaging(final PageableData paging) {
        this.paging = paging;
        return this;
    }
}
