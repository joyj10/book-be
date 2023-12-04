package com.won.book.domain.readbook;

import com.won.book.domain.BaseDateEntity;
import com.won.book.domain.member.Member;
import com.won.book.domain.book.Book;
import lombok.AccessLevel;
import lombok.Builder;
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

    @Builder.Default
    @OneToMany(mappedBy = "readBook", fetch = FetchType.LAZY)
    private List<ReadBookRating> readBookRatings = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "readBook", fetch = FetchType.LAZY)
    private List<ReadBookContent> readBookContents = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "readBook", fetch = FetchType.LAZY)
    private List<ReadBookReview> readBookReviews = new ArrayList<>();
}
