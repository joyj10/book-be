package com.won.book.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
@Table(name = "read_book_content")
public class ReadBookContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "read_book_content_id")
    private Long id;

    @Column(nullable = false, name = "content", columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name = "read_book_id")
    private ReadBook readBook;
}
