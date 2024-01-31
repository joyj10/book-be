package com.won.bookappapi.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReadBookYearDto {
    private int month;
    private int totalReadBookCount;
}
