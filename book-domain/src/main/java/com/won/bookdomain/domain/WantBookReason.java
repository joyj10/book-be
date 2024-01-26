package com.won.bookdomain.domain;

import com.won.bookdomain.domain.base.BaseDateEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.*;


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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "want_book_id")
    private WantBook wantBook;

    public static WantBookReason create(String reason) {
        return WantBookReason.builder()
                .reason(reason)
                .build();
    }

    public void setReadBook(WantBook wantBook) {
        this.wantBook = wantBook;
    }
}
