package com.won.book.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "book")
public class Book extends BaseDateEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", updatable = false)
    private Long id;

    @Column(name = "title", length = 200, nullable = false)
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "price")
    private int price;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "publish_at")
    private LocalDate publishAt;

    @Column(name = "isbn", nullable = false)
    private String isbn;

    @Column(name = "sort", nullable = false)
    private String sort;

    @Column(name = "image", columnDefinition = "TEXT")
    private String image;

    @Column(name = "link", columnDefinition = "TEXT")
    private String link;
}

