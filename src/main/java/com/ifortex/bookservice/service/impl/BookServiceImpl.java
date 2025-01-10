package com.ifortex.bookservice.service.impl;

import com.ifortex.bookservice.dao.BookDAO;

import com.ifortex.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDAO bookDAO;
    @Override
    public Map<String, Long> getBooks() {
        return bookDAO.countBooksByGenre();
    }
}
