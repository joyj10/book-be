package com.won.book.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "read_book")
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

    @ColumnDefault("N")
    @Column(length = 1, nullable = false)
    private String deleteYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @OneToMany(mappedBy = "readBook", fetch = FetchType.LAZY)
    private List<ReadBookRating> readBookRatings = new ArrayList<>();

    @OneToMany(mappedBy = "readBook", fetch = FetchType.LAZY)
    private List<ReadBookContent> readBookContents = new ArrayList<>();

    @OneToMany(mappedBy = "readBook", fetch = FetchType.LAZY)
    private List<ReadBookReview> readBookReviews = new ArrayList<>();
}
