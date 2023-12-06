package com.won.book.domain.wantbook.dto;

import com.won.book.domain.book.BookDto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WantBookDto {
    private Long id;
    private BookDto book;
}
