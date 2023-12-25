package com.won.bookdomain.domain;

import com.won.bookdomain.domain.base.BaseDateEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "read_book")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE read_book SET is_deleted = true WHERE id = ?")
public class ReadBook extends BaseDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "read_book_id")
    private Long id;

    @Column(nullable = false)
    private int readCount;

    @Column(nullable = false)
    private float totalRating;

    @Column(nullable = false)
    private LocalDateTime lastReadAt;

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

    public void addReadBookContent(ReadBookContent readBookContent) {
        readBookContents.add(readBookContent);
        readBookContent.setReadBook(this);
    }

    public void addReadBookReview(ReadBookReview readBookReview) {
        readBookReviews.add(readBookReview);
        readBookReview.setReadBook(this);
    }

    public void deleted() {
        this.isDeleted = true;
    }

}
