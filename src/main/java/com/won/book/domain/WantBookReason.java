package com.won.book.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;


@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "want_book_reason")
public class WantBookReason extends BaseDateEntity {
    @Id
    @GeneratedValue
    @Column(name = "want_book_reason_id")
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String reason;

    @ManyToOne
    @JoinColumn(name = "want_book_id")
    private WantBook wantBook;
}
