package com.won.book.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Setter
@Getter
@Table(name = "read_book_review")
public class ReadBookReview {
    @Id
    @GeneratedValue
    @Column(name = "read_book_review_id")
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String review;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name = "read_book_id")
    private ReadBook readBook;
}
