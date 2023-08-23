package com.won.book.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Setter
@Getter
@Table(name = "read_book_content")
public class ReadBookContent {
    @Id
    @GeneratedValue
    @Column(name = "read_book_content_id")
    private Long id;

    private String content;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name = "read_book_id")
    private ReadBook readBook;
}
