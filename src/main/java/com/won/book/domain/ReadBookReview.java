package com.won.book.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;


@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "read_book_review")
public class ReadBookReview extends BaseDateEntity {
    @Id
    @GeneratedValue
    @Column(name = "read_book_review_id")
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "read_book_id")
    private ReadBook readBook;
}
