package com.won.bookdomain.domain;

import com.won.bookcommon.exception.BusinessException;
import com.won.bookcommon.exception.ExceptionCode;
import com.won.bookdomain.domain.base.BaseDateEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

import static com.won.bookcommon.exception.ExceptionCode.INVALID_PARAMETER;

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
    private LocalDate readAt;

    @Column(nullable = false)
    private double rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "read_book_id")
    private ReadBook readBook;

    @Builder
    private ReadBookRating(int repeatOrder, LocalDate readAt, double rating) {
        this.repeatOrder = repeatOrder;
        this.readAt = readAt;
        this.rating = rating;
    }

    public static ReadBookRating createFirst(LocalDate readAt, double rating) {
        if (readAt == null || rating < 0 || rating > 5) {
            throw new BusinessException(INVALID_PARAMETER);
        }
        return ReadBookRating.builder()
                .repeatOrder(1)
                .readAt(readAt)
                .rating(rating)
                .build();
    }

    public void setReadBook(ReadBook readBook) {
        this.readBook = readBook;
    }
}
