package com.won.bookdomain.domain;

import com.won.bookcommon.exception.BusinessException;
import com.won.bookcommon.exception.ExceptionCode;
import com.won.bookcommon.util.LocalDateTimeUtil;
import com.won.bookdomain.domain.base.BaseDateEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.won.bookcommon.exception.ExceptionCode.INVALID_PARAMETER;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "read_book")
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE read_book SET deleted = true WHERE read_book_id = ?")
public class ReadBook extends BaseDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "read_book_id")
    private Long id;

    @Column(nullable = false)
    private int readCount;

    @Column(nullable = false)
    private double totalRating;

    @Column(nullable = false)
    private LocalDate lastReadAt;

    @ColumnDefault("false")
    @Column(nullable = false)
    private boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @OneToMany(mappedBy = "readBook", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ReadBookRating> readBookRatings = new ArrayList<>();

    @OneToMany(mappedBy = "readBook", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ReadBookContent> readBookContents = new ArrayList<>();

    @OneToMany(mappedBy = "readBook", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ReadBookReview> readBookReviews = new ArrayList<>();

    @Builder
    private ReadBook(int readCount, double totalRating, @NonNull LocalDate lastReadAt, boolean deleted) {
        this.readCount = readCount;
        this.totalRating = totalRating;
        this.lastReadAt = lastReadAt;
        this.deleted = deleted;
    }

    public static ReadBook create(int readCount, double totalRating, @NonNull LocalDate lastReadAt) {
        if (readCount <= 0 || totalRating > 5 || totalRating < 0) {
            throw new BusinessException(INVALID_PARAMETER);
        }

        return ReadBook.builder()
                .readCount(1)
                .totalRating(totalRating)
                .lastReadAt(lastReadAt)
                .deleted(false)
                .build();
    }


    //== 연관 관계 편의 메서드 ==
    public void addReadBookRating(ReadBookRating readBookRating) {
        readBookRatings.add(readBookRating);
        readBookRating.setReadBook(this);
    }

    public void addReadBookContent(List<ReadBookContent> readBookContents) {
        this.readBookContents.addAll(readBookContents);
        for (ReadBookContent readBookContent : readBookContents) {
            readBookContent.setReadBook(this);
        }
    }

    public void addReadBookReview(List<ReadBookReview> readBookReviews) {
        this.readBookReviews.addAll(readBookReviews);
        for (ReadBookReview readBookReview : readBookReviews) {
            readBookReview.setReadBook(this);
        }
    }

    public void setMemberAndBook(User user, Book book) {
        this.user = user;
        this.book = book;
    }

    // == 비즈니스 로직 ==
    public void deleted() {
        this.deleted = true;
    }

    public void update(LocalDate readAt, int rating) {
        this.lastReadAt = readAt;
        this.totalRating = rating; // TODO 이후 수정 필요(전체 평균으로 변경 필요)
    }
}
