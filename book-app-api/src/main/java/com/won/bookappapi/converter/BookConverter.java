package com.won.bookappapi.converter;

import com.won.bookappapi.service.dto.BookDto;
import com.won.bookcommon.util.LocalDateTimeUtil;
import com.won.bookdomain.domain.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * BookConverter
 * <pre>
 * Describe here
 * </pre>
 *
 * @version 1.0,
 */

@Mapper(componentModel="spring")
public interface BookConverter {

    @Mapping(target = "publishAt", source = "publishAt", dateFormat = LocalDateTimeUtil.DATE_FULL_DASH)
    @Mapping(target = "bookId", source = "id")
    BookDto convert(Book book);
    List<BookDto> convert(List<Book> books);
}
