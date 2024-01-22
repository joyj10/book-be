package com.won.bookdomain.repository;

import com.won.bookdomain.domain.ReadBook;
import com.won.bookdomain.domain.ReadBookContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadBookContentRepository extends JpaRepository<ReadBookContent, Long> {
    List<ReadBookContent> findAllByReadBook(ReadBook readBook);
}
