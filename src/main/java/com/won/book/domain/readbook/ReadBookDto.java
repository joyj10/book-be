package com.won.book.domain.readbook;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReadBookDto {
    private String title;
    private String sort;
    private String image;
    private String author;
    private String publisher;
    private String readAt;
    private int price;

    private Long readBookId;
}
