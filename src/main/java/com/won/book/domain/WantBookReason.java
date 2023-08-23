package com.won.book.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Setter
@Getter
@Table(name = "want_book_reason")
public class WantBookReason {
    @Id
    @GeneratedValue
    @Column(name = "want_book_reason_id")
    private Long id;

    private String reason;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name = "want_book_id")
    private WantBook wantBook;
}
