package com.won.book.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter @Setter
@Table(name = "book")
public class Book {
    @Id @GeneratedValue
    @Column(name = "book_id")
    private Long id;

    private String title;
    private String author;
    private int price;
    private String publisher;
    private LocalDateTime publishAt;
    private String isbn;
    private String image;
    private String link;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}

