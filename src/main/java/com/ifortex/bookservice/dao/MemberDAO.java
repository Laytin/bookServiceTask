package com.ifortex.bookservice.dao;

import com.ifortex.bookservice.model.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class MemberDAO {
    @PersistenceContext
    private EntityManager entityManager;
    @Transactional(readOnly = true)
    public List<Member> findByBorrowedBooksEmptyAndMembershipDateYear(LocalDateTime membershipDate){
        return  entityManager.createQuery("select m from Member m " +
                "WHERE m.borrowedBooks IS EMPTY and year(m.membershipDate)=?1")
                .setParameter(1,membershipDate.getYear())
                .getResultList();
    }
    @Transactional(readOnly = true)
    public Member findByOldestGenreBookOrderByMembershipDate(String genre){
        return (Member) entityManager.createNativeQuery(
                     "SELECT * " +
                        "FROM   members " +
                        "       JOIN member_books mb " +
                        "         ON mb.member_id = members.id " +
                        "WHERE  mb.book_id IN (SELECT books.id " +
                        "                      FROM   books " +
                        "                      WHERE  ?1 = any ( genre ) " +
                        "                      ORDER  BY publication_date ASC " +
                        "                      LIMIT  1) " +
                        "ORDER  BY membership_date ASC " +
                        "LIMIT  1 ", Member.class)
                .setParameter(1, genre)
                .getSingleResult();
    }
}
