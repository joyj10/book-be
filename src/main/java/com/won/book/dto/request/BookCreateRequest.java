package com.won.book.dto.request;

import lombok.Getter;
import lombok.Setter;

/**
 * BookCreateRequest
 * <pre>
 * Describe here
 * </pre>
 *
 * @version 1.0,
 */

@Getter @Setter
public class BookCreateRequest {
    private String title;
    private String author;
    private int price;
    private String publisher;
    private String sort;
    private String image;
}
