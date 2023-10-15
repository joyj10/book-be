package com.won.book.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "want_book")
public class WantBook {
    @Id
    @GeneratedValue
    @Column(name = "want_book_id")
    private Long id;

    @ColumnDefault("N")
    @Column(length = 1, nullable = false)
    private String deleteYn;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @OneToMany(mappedBy = "wantBook")
    private List<WantBookReason> wantBookReasons = new ArrayList<>();
}
