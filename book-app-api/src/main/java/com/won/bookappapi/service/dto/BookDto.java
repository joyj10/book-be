package com.won.bookappapi.service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {
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
