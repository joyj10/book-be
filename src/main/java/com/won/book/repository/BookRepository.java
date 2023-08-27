package com.won.book.repository;

import com.won.book.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findById(Long bookId);
    List<Book> findAllByTitleContainingOrAuthorContaining(String title, String author);
}
