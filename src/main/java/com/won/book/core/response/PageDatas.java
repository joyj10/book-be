package com.won.book.core.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * PageDatas
 * <pre>
 * Describe here
 * </pre>
 *
 * @version 1.0,
 */

@Setter
@Getter
public class PageDatas<T> {
    private int size;
    private int page;
    private int totalCount;

    private List<T> pageResult;
}
