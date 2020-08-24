package com.education.spring.repository;

import com.education.spring.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class AuthorRepositoryCustomImpl implements AuthorRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    @Transactional
    public Author update(Author author) {
        return em.merge(author);
    }
}
