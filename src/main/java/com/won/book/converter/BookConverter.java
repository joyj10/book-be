package com.won.book.converter;

import com.won.book.core.utils.LocalDateTimeUtil;
import com.won.book.domain.Book;
import com.won.book.dto.BookVo;
import com.won.book.dto.request.BookCreateRequest;
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
    BookVo convert(Book book);
    List<BookVo> convert(List<Book> books);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "publishAt", ignore = true)
    @Mapping(target = "isbn", ignore = true)
    @Mapping(target = "link", ignore = true)
    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    Book convert(BookCreateRequest createRequest);
}