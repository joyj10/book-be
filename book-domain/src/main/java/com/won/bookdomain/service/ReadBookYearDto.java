package com.won.bookdomain.service;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ReadBookYearDto {
    private int month;
    private Long totalReadBookCount;
}
