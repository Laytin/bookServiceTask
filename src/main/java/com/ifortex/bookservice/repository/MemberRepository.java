package com.ifortex.bookservice.repository;

import com.ifortex.bookservice.model.Book;
import com.ifortex.bookservice.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    List<Member> findByBorrowedBooksEmptyAndMembershipDateBetween(LocalDateTime membershipDate, LocalDateTime membershipDate2);
    Optional<Member> findTopByBorrowedBooksContainingOrderByMembershipDateAsc(Book book);
}
