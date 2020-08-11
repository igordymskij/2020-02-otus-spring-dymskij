package com.education.spring.dao;

import com.education.spring.domain.Author;
import com.education.spring.domain.Book;
import com.education.spring.domain.Comment;
import com.education.spring.domain.Jenre;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;


@Repository
public class CommentsDaoJpaImpl implements CommentsDao{

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public Comment save(Comment comment) {
        if (comment.getId() <= 0) {
            return em.merge(comment);
        } else {
            em.persist(comment);
            return comment;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Comment> findById(int id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> findAll() {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c", Comment.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Comment update(Comment comment) {
        return em.merge(comment);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        Comment comment = findById(id).get();
        em.remove(comment);
        em.flush();
    }
}
