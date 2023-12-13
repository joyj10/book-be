package com.won.book.converter;

import com.won.book.common.util.LocalDateTimeUtil;
import com.won.book.domain.readbook.entity.ReadBook;
import com.won.book.domain.readbook.dto.ReadBookDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel="spring")
public interface ReadBookConverter {

    @Mapping(target = "title", source = "book.title")
    @Mapping(target = "sort", source = "book.sort")
    @Mapping(target = "image", source = "book.image")
    @Mapping(target = "author", source = "book.author")
    @Mapping(target = "publisher", source = "book.publisher")
    @Mapping(target = "readAt", source = "lastReadAt", dateFormat = LocalDateTimeUtil.DATE_FULL_DASH)
    @Mapping(target = "price", source = "book.price")
    @Mapping(target = "readBookId", source = "id")
    ReadBookDto convert(ReadBook readBook);
    List<ReadBookDto> convert(List<ReadBook> readBooks);
}
