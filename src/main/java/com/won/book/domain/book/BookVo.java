package com.won.book.domain.book;

import lombok.*;

/**
 * BookVo
 * <pre>
 * Describe here
 * </pre>
 *
 * @version 1.0,
 */

@Getter @Setter
public class BookVo {
    private Long bookId;
    private String title;
    private String author;
    private int price;
    private String publisher;
    private String publishAt;
    private String sort;
    private String image;
    private String link;
}
