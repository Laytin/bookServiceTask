package com.ifortex.bookservice.service.impl;

import com.ifortex.bookservice.repository.BookRepository;
import com.ifortex.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Map<String, Long> getBooks() {
        return bookRepository.countBooksByGenre().stream()
                .collect(Collectors.toMap(
                        obj -> (String) obj[1],
                        obj -> (Long) obj[0],
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                ));
    }
}
