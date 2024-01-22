package com.won.bookappapi.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReadBookContentDto {
    private Long id;
    private String content;
    private ReadBookDto readBookDto;
}
