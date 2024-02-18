package com.won.bookdomain.domain;

import com.won.bookcommon.exception.BusinessException;
import com.won.bookdomain.domain.base.BaseDateEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.*;

import static com.won.bookcommon.exception.ExceptionCode.INVALID_PARAMETER;


@Entity
@Getter
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

    @Builder
    private WantBookReason(String reason) {
        this.reason = reason;
    }

    public static WantBookReason create(String reason) {
        if (reason.isBlank()) {
            throw new BusinessException(INVALID_PARAMETER);
        }

        return WantBookReason.builder()
                .reason(reason)
                .build();
    }

    public void setReadBook(WantBook wantBook) {
        this.wantBook = wantBook;
    }
}
