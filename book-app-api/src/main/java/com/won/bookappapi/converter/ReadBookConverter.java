package com.won.bookappapi.converter;

import com.won.bookcommon.util.LocalDateTimeUtil;
import com.won.bookdomain.domain.ReadBook;
import com.won.bookappapi.service.dto.ReadBookDto;
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
