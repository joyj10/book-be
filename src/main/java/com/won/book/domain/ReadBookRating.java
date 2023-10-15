package com.won.book.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * ReadBookRating
 * <pre>
 * Describe here
 * </pre>
 *
 * @version 1.0,
 */

@Entity
@Setter
@Getter
@Table(name = "read_book_rating")
public class ReadBookRating {
    @Id
    @GeneratedValue
    @Column(name = "read_book_rating_id")
    private Long id;

    @Column(nullable = false)
    private int repeatOrder;

    @Column(nullable = false)
    private LocalDateTime readAt;

    @Column(nullable = false)
    private float rating;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name = "read_book_id")
    private ReadBook readBook;
}
