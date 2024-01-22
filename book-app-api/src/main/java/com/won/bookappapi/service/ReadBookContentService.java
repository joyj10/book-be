package com.won.bookappapi.service;

import com.won.bookappapi.converter.ReadBookContentConverter;
import com.won.bookappapi.service.dto.ReadBookContentDto;
import com.won.bookdomain.domain.ReadBookContent;
import com.won.bookdomain.repository.ReadBookContentRepository;
import com.won.bookdomain.repository.ReadBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReadBookContentService {

    private final ReadBookContentRepository contentRepository;
    private final ReadBookRepository readBookRepository;

    private final ReadBookContentConverter contentConverter;

    @Transactional(readOnly = true)
    public List<ReadBookContentDto> getContents(Long readBookId) {
        List<ReadBookContent> contents = contentRepository.findAllByReadBook(
                readBookRepository.getReferenceById(readBookId));
        return contentConverter.convert(contents);
    }
}
