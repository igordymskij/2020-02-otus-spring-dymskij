package com.education.spring.service;

import com.education.spring.domain.Author;
import com.education.spring.domain.Book;

import java.util.List;

public interface BookService {

    String insertBookInfo(Book book);
    boolean checkDublicateBook(Book book);
    String updateBookName(Book book, String newName);
    String updateBookAuthorName(Book book, Author author);
    String updateBookJenreName(Book book, String newJenreName);
    List<Book> findBookByName(String lastName);
    String updateBookYear(Book book, String newYear);
    Book findBook(Book book);
    String deleteBookByName(Book bookInfo);
    List<Book> getAllBooks();

}
