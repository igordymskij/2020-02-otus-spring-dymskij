package com.education.spring.dao;

import com.education.spring.domain.Author;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorsDaoJpaImpl implements AuthorsDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public Author save(Author author) {
        if (author.getId() <= 0) {
            em.persist(author);
            return author;
        } else {
            return em.merge(author);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Author> findById(int id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Author> findAll() {
        TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Author> findByName(String lastName) {
        TypedQuery<Author> query = em.createQuery("select a from Author a where a.lastName = :lastName", Author.class);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Author update(Author author) {
        return em.merge(author);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        Author author = findById(id).get();
        em.remove(author);
        em.flush();
    }
}
