package com.education.spring.repository;

import com.education.spring.domain.Comment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Comment update(Comment comment) {
        return em.merge(comment);
    }
}
