package com.education.spring.repository;

import com.education.spring.domain.Jenre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface  JenreRepository extends JpaRepository<Jenre, Integer>, JenreRepositoryCustom {

    Optional<Jenre> findById(int id);
    Jenre findByJenre(String jenre);
    List<Jenre> findAll();
    Jenre save(Jenre jenre);
    void deleteById(int jenreId);

}
