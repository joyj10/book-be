package com.won.bookdomain.repository;

import com.won.bookdomain.domain.User;
import com.won.bookdomain.domain.ReadBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReadBookRepository extends JpaRepository<ReadBook, Long>, ReadBookRepositoryCustom {
    List<ReadBook> findAllByUser(User user);
    Optional<ReadBook> findByIdAndUser(Long readBookId, User user);
    List<ReadBook> findAllByUserAndLastReadAtBetween(User user, LocalDate startDate, LocalDate endDate);
}
