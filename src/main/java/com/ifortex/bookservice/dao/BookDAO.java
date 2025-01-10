package com.ifortex.bookservice.dao;

import com.ifortex.bookservice.model.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BookDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public Map<String,Long> countBooksByGenre() {

        List<Object[]> pb = entityManager.createNativeQuery("SELECT COUNT(*) as count, unnest(genre) AS _genre " +
                "FROM books " +
                "GROUP BY _genre " +
                "ORDER BY count DESC")
                .getResultList();
        return pb.stream()
                .collect(Collectors.toMap(
                        obj -> (String) obj[1],
                        obj -> (Long) obj[0],
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));
    }
}
