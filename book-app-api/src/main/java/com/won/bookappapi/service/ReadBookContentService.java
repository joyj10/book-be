package com.won.bookappapi.service;

import com.won.bookappapi.api.request.ReadBookContentCreateRequest;
import com.won.bookappapi.converter.ReadBookContentConverter;
import com.won.bookappapi.service.dto.ReadBookContentDto;
import com.won.bookdomain.domain.ReadBook;
import com.won.bookdomain.domain.ReadBookContent;
import com.won.bookdomain.repository.ReadBookContentRepository;
import com.won.bookdomain.repository.ReadBookRepository;
import jakarta.persistence.EntityNotFoundException;
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
    public List<ReadBookContentDto> getContents(final Long readBookId) {
        List<ReadBookContent> contents = contentRepository.findAllByReadBook(
                readBookRepository.getReferenceById(readBookId));
        return contentConverter.convert(contents);
    }

    @Transactional
    public void save(final ReadBookContentCreateRequest createRequest) {
        ReadBook readBook = readBookRepository.findById(createRequest.getReadBookId())
                .orElseThrow(EntityNotFoundException::new);

        List<ReadBookContent> contents = createRequest.getContents().stream()
                .map(ReadBookContent::create)
                .toList();

        readBook.addReadBookContent(contents);
    }

    @Transactional
    public void update(Long contentId, String content) {
        ReadBookContent readBookContent = contentRepository.findById(contentId)
                .orElseThrow(EntityNotFoundException::new);

        readBookContent.updateContent(content);
    }

    @Transactional
    public void delete(Long contentId) {
        contentRepository.deleteById(contentId);
    }
}
