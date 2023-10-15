package com.won.book.domain;

import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * ReadBook
 * <pre>
 * Describe here
 * </pre>
 *
 * @version 1.0,
 */

@Entity
@Getter
@Table(name = "read_book")
public class ReadBook {
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

    private LocalDateTime createAt;
    private LocalDateTime updateAt;

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

    @Override
    public String toString() {
        return "ReadBook{" +
                "id=" + id +
                ", readCount=" + readCount +
                ", totalRating=" + totalRating +
                ", lastReadAt=" + lastReadAt +
                ", deleteYn='" + deleteYn + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", book=" + book.getTitle() +
                '}';
    }
}
