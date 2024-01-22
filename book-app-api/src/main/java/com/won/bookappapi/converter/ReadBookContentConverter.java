package com.won.bookappapi.converter;

import com.won.bookappapi.service.dto.ReadBookContentDto;
import com.won.bookdomain.domain.ReadBookContent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel="spring")
public interface ReadBookContentConverter {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "content", source = "content")
    @Mapping(target = "readBookDto", ignore = true)
    ReadBookContentDto convert(ReadBookContent content);
    List<ReadBookContentDto> convert(List<ReadBookContent> contents);
}
