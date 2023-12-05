package com.won.book.domain.wantbook;

import com.won.book.domain.BaseDateEntity;
import com.won.book.domain.book.Book;
import com.won.book.domain.member.Member;
import com.won.book.domain.readbook.ReadBookRating;
import lombok.AccessLevel;
import lombok.Builder;
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

    @Builder.Default
    @OneToMany(mappedBy = "wantBook", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<WantBookReason> wantBookReasons = new ArrayList<>();

    //== 연관 관계 편의 메서드 ==
    public void addWantBookReason(WantBookReason wantBookReason) {
        wantBookReasons.add(wantBookReason);
        wantBookReason.setReadBook(this);
    }
}
