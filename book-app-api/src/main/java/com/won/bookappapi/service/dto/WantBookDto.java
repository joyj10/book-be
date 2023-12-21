package com.won.bookappapi.service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WantBookDto {
    private Long id;
    private BookDto book;
}
