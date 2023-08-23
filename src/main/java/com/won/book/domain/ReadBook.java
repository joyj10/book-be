package com.won.book.domain;

import lombok.Getter;
import lombok.Setter;

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
@Setter
@Table(name = "read_book")
public class ReadBook {
    @Id
    @GeneratedValue
    @Column(name = "read_book_id")
    private Long id;

    private int readCount;
    private float totalRating;
    private LocalDateTime lastReadAt;
    private String deleteYn;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @OneToMany(mappedBy = "readBook")
    private List<ReadBookRating> readBookRatings = new ArrayList<>();

    @OneToMany(mappedBy = "readBook")
    private List<ReadBookContent> readBookContents = new ArrayList<>();

    @OneToMany(mappedBy = "readBook")
    private List<ReadBookReview> readBookReviews = new ArrayList<>();
}
