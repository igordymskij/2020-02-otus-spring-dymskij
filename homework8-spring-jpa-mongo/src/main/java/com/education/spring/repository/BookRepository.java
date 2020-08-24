package com.education.spring.repository;

import com.education.spring.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


public interface BookRepository extends MongoRepository<Book, String> {

    Optional<Book> findById(int id);
    List<Book> findByName(String name);
    List<Book> findAll();
    Book save(Book book);
    void deleteById(int id);

}
