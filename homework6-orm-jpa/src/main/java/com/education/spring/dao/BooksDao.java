package com.education.spring.dao;

import com.education.spring.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BooksDao {

    Optional<Book> findById(int id);
    List<Book> findByName(String name);
    List<Book> findAll();
    Book save(Book book);
    Book update(Book book);
    void deleteById(int id);

}
