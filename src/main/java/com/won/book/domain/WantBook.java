package com.won.book.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "want_book")
public class WantBook extends BaseDateEntity {
    @Id
    @GeneratedValue
    @Column(name = "want_book_id")
    private Long id;

    @ColumnDefault("N")
    @Column(length = 1, nullable = false)
    private String deleteYn;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @OneToMany(mappedBy = "wantBook", fetch = FetchType.LAZY)
    private List<WantBookReason> wantBookReasons = new ArrayList<>();
}
