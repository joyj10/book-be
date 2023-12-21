package com.won.bookdomain.domain;

import com.won.bookdomain.domain.base.BaseDateEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.*;
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
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "read_book_rating")
public class ReadBookRating extends BaseDateEntity {
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "read_book_id")
    private ReadBook readBook;

    public void setReadBook(ReadBook readBook) {
        this.readBook = readBook;
    }
}