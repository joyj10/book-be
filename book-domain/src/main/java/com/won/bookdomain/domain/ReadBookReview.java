package com.won.bookdomain.domain;

import com.won.bookcommon.exception.BusinessException;
import com.won.bookdomain.domain.base.BaseDateEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.*;

import static com.won.bookcommon.exception.ExceptionCode.INVALID_PARAMETER;


@Entity
@Getter
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

    @Builder
    private ReadBookReview(String review) {
        this.review = review;
    }

    public static ReadBookReview create(String review) {
        if (review.isBlank()) {
            throw new BusinessException(INVALID_PARAMETER);
        }

        return ReadBookReview.builder()
                .review(review)
                .build();
    }

    public void setReadBook(ReadBook readBook) {
        this.readBook = readBook;
    }
}
