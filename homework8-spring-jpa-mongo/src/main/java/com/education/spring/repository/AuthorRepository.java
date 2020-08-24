package com.education.spring.repository;

import com.education.spring.domain.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


public interface AuthorRepository extends MongoRepository<Author, String>{

    Optional<Author> findById(int id);
    List<Author> findByLastName(String lastName);
    List<Author> findAll();
    Author save(Author author);
    void deleteById(int id);

}

