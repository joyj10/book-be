package com.won.bookdomain.repository;

import com.won.bookdomain.service.ReadBookYearDto;

import java.util.List;

public interface ReadBookRepositoryCustom {
    List<ReadBookYearDto> getReadBookYearDto(Long userId, int year);
}
