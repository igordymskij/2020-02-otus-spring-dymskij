package com.education.spring.service;

import com.education.spring.domain.Author;

import java.util.List;

public interface AuthorService {

    String insertAuthorInfo(Author author);
    Author checkDublicateAuthor(Author author);
    List<Author> findAuthorByName(String authorName);
    Author findAuthor(Author author);
    String updateAuthorLastName(Author authorInfo, String newLastName);
    String updateAuthorName(Author authorInfo, String newName);
    String updateAuthorSurname(Author authorInfo, String newSurname);
    String deleteAuthorByName(Author authorInfo);
    List<Author> getAllAuthors();
}
