package com.education.spring.dao;

import com.education.spring.domain.Author;
import com.education.spring.domain.Book;

import java.util.List;

public interface AuthorsDao {

    Author getById(long id);
    Author getByName(String name);
    List<Author> getAll();
    int insert(Author author);
    int deleteById(int id);
    int deleteByName(String name);
}
