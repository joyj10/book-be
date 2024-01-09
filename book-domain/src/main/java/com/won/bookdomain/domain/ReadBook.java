package com.won.bookdomain.domain;

import com.won.bookdomain.domain.base.BaseDateEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "read_book")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE read_book SET is_deleted = true WHERE read_book_id = ?")
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
    private boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Builder.Default
    @OneToMany(mappedBy = "readBook", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ReadBookRating> readBookRatings = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "readBook", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ReadBookContent> readBookContents = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "readBook", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ReadBookReview> readBookReviews = new ArrayList<>();

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

    public void setMemberAndBook(Member member, Book book) {
        this.member = member;
        this.book = book;
    }

    public void deleted() {
        this.isDeleted = true;
    }

    public void update(LocalDate readAt, int rating) {
        this.lastReadAt = readAt;
        this.totalRating = rating; // TODO 이후 수정 필요(전체 평균으로 변경 필요)
    }
}
