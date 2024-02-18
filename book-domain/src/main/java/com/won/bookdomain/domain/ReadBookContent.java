package com.won.bookdomain.domain;

import com.won.bookcommon.exception.BusinessException;
import com.won.bookcommon.exception.ExceptionCode;
import com.won.bookdomain.domain.base.BaseDateEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.*;
import org.apache.coyote.BadRequestException;

import java.util.List;

import static com.won.bookcommon.exception.ExceptionCode.INVALID_PARAMETER;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "read_book_content")
public class ReadBookContent extends BaseDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "read_book_content_id")
    private Long id;

    @Column(nullable = false, name = "content", columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "read_book_id")
    private ReadBook readBook;

    @Builder
    private ReadBookContent(String content) {
        this.content = content;
    }

    public static ReadBookContent create(String content) {
        if (content.isBlank()) {
            throw new BusinessException(ExceptionCode.INVALID_PARAMETER);
        }

        return ReadBookContent.builder()
                .content(content)
                .build();
    }

    public void setReadBook(ReadBook readBook) {
        this.readBook = readBook;
    }

    public void updateContent(String content) {
        if (content.isBlank()) {
            throw new BusinessException(ExceptionCode.INVALID_PARAMETER);
        }
        this.content = content;
    }
}
