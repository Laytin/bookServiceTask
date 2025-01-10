package com.ifortex.bookservice.service.impl;

import com.ifortex.bookservice.model.Book;
import com.ifortex.bookservice.model.Member;
import com.ifortex.bookservice.repository.BookRepository;
import com.ifortex.bookservice.repository.MemberRepository;
import com.ifortex.bookservice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BookRepository bookRepository;
    @Override
    public Member findMember() {
        Book b = bookRepository.findTopBookByGenre("Romance");
        // maybe no one reads romance books
        Optional<Member> mb = memberRepository.findTopByBorrowedBooksContainingOrderByMembershipDateAsc(b);
        return mb.isPresent()? mb.get() : new Member();
    }

    @Override
    public List<Member> findMembers() {
        LocalDateTime ldt = LocalDateTime.of(2023, 1, 1, 0, 0);
        return memberRepository.findByBorrowedBooksEmptyAndMembershipDateBetween(ldt,ldt.plusYears(1));
    }
}
