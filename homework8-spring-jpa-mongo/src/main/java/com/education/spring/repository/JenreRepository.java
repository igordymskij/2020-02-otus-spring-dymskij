package com.education.spring.repository;

import com.education.spring.domain.Jenre;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


public interface  JenreRepository extends MongoRepository<Jenre, String> {

    Optional<Jenre> findById(int id);
    Jenre findByJenre(String jenre);
    List<Jenre> findAll();
    Jenre save(Jenre jenre);
    void deleteById(int jenreId);

}
