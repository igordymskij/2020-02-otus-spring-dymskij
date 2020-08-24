package com.education.spring.dao;

import com.education.spring.domain.Book;
import com.education.spring.domain.Jenre;

import java.util.List;
import java.util.Optional;

public interface JenresDao {

    Optional<Jenre> findById(int id);
    Jenre findByName(String name);
    List<Jenre> findAll();
    Jenre save(Jenre jenre);
    Jenre update(Jenre newJenre);
    void deleteById(int jenreId);

}
