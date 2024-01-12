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
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "want_book")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE want_book SET is_deleted = true WHERE want_book_id = ?")
public class WantBook extends BaseDateEntity {
    @Id
    @GeneratedValue
    @Column(name = "want_book_id")
    private Long id;

    @ColumnDefault("false")
    @Column(nullable = false)
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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
