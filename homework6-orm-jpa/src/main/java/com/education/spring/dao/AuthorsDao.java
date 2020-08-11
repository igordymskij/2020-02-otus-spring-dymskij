package com.education.spring.dao;

import com.education.spring.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorsDao {

    Optional<Author> findById(int id);
    List<Author> findByName(String lastName);
    List<Author> findAll();
    Author save(Author author);
    Author update(Author author);
    void deleteById(int id);

}

