package com.education.spring.dao;

import com.education.spring.domain.Book;

import java.util.List;

public interface BooksDao {

    Book getById(long id);
    Book getByName(String name);
    List<Book> getAll();
    int insert(Book book);
    int nameUpdate(String name, String newName);
    int authorUpdate(String name, String newName);
    int jenreUpdate(String name, String newJenreId);
    int yearUpdate(String name, String newYear);
    int deleteById(int id);
    int deleteByName(String name);

}
