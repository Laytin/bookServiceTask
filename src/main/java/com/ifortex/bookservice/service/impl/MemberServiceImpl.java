package com.ifortex.bookservice.service.impl;

import com.ifortex.bookservice.dao.MemberDAO;
import com.ifortex.bookservice.model.Member;
import com.ifortex.bookservice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDAO memberDAO;
    @Override
    public Member findMember() {
        return memberDAO.findByOldestGenreBookOrderByMembershipDate("Romance");
    }
    @Override
    public List<Member> findMembers() {
        LocalDateTime ldt = LocalDateTime.of(2023, 1, 1, 0, 0);
        return memberDAO.findByBorrowedBooksEmptyAndMembershipDateYear(ldt);
    }
}
