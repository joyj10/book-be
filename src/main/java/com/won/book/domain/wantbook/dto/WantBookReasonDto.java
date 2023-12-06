package com.won.book.domain.wantbook.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WantBookReasonDto {
    private Long id;
    private String reason;
    private WantBookDto wantBook;
}
