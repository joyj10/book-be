package com.won.bookappapi.service.dto;

import lombok.*;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WantBookDto {
    private Long id;
    private String addAt;
    private BookDto book;
    private List<WantBookReasonSimpleDto> wantBookReasons;
}
