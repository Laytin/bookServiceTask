package com.ifortex.bookservice.repository;

import com.ifortex.bookservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    // ! Operand of 'member of' operator must be a plural path !
    //I cant edit and also insert @ElementCollection in any model class, so i had to write some native queries
    @Query(value = "select * from books where :_genre=ANY(genre) order by publication_date ASC LIMIT 1",nativeQuery = true)
    Book findTopBookByGenre(@Param("_genre") String genre);
    //unnest helps us to make a large table, separating every genre tag
    @Query(value = "SELECT COUNT(*) as count, unnest(genre) AS _genre FROM books GROUP BY _genre ORDER BY count DESC",nativeQuery = true)
    List<Object[]> countBooksByGenre();
}
