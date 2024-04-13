package com.won.bookdomain.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.won.bookdomain.service.ReadBookYearDto;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.won.bookdomain.domain.QReadBook.readBook;

public class ReadBookRepositoryImpl implements ReadBookRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ReadBookRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<ReadBookYearDto> getReadBookYearDto(Long userId, int year) {
        return queryFactory
                .select(Projections.fields(ReadBookYearDto.class,
                        readBook.lastReadMonth.as("month"),
                        readBook.lastReadMonth.count().as("totalReadBookCount")
                ))
                .from(readBook)
                .where(
                        readBook.user.id.eq(userId),
                        readBook.lastReadYear.eq(year)
                )
                .groupBy(readBook.lastReadMonth)
                .orderBy(readBook.lastReadMonth.asc())
                .fetch();
    }
}
