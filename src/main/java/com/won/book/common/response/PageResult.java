package com.won.book.common.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PageResult<T> {
    private int size;
    private int page;
    private int totalCount;

    private List<T> result;
}
