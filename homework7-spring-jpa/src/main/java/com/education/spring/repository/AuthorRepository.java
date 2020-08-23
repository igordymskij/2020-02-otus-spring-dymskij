package com.education.spring.repository;

import com.education.spring.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface AuthorRepository extends JpaRepository<Author, Integer>, AuthorRepositoryCustom {

    Optional<Author> findById(int id);
    List<Author> findByLastName(String lastName);
    List<Author> findAll();
    Author save(Author author);
    void deleteById(int id);

}

