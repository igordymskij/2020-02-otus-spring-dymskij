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

@Transactional
@Repository
public class CommentsDaoJpaImpl implements CommentsDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() <= 0) {
            return em.merge(comment);
        } else {
            em.persist(comment);
            return comment;
        }
    }

    @Override
    public Optional<Comment> findById(int id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public List<Comment> findByBookId(int bookid) {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c where c.id = :id", Comment.class);
        query.setParameter("id", bookid);
        return query.getResultList();
    }

    @Override
    public List<Comment> findAll() {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c", Comment.class);
        return query.getResultList();
    }

    @Override
    public int updateById(int id, Comment comment) {
        Query query = em.createQuery("update Comment c set c.comment = :comment where c.id = :id");
        query.setParameter("id", id);
        query.setParameter("comment", comment.getComment());
        return query.executeUpdate();
    }

    @Override
    public int deleteById(int id) {
        Query query = em.createQuery("delete from Comment c where c.id = :id");
        query.setParameter("id", id);
        return query.executeUpdate();
    }

    @Override
    public int deleteByBookId(int bookid) {
        Query query = em.createQuery("delete from Comment c where c.id = :id");
        query.setParameter("id", bookid);
        return query.executeUpdate();
    }
}
