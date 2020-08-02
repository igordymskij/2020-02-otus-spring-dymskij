package com.education.spring.dao;

import com.education.spring.domain.Author;
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
public class AuthorsDaoJpaImpl implements AuthorsDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Author save(Author author) {
        if (author.getId() <= 0) {
            em.persist(author);
            return author;
        } else {
            return em.merge(author);
        }
    }

    @Override
    public Optional<Author> findById(int id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    public List<Author> findAll() {
        TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }

    @Override
    public List<Author> findByName(String lastName) {
        TypedQuery<Author> query = em.createQuery("select a from Author a where a.lastName = :lastName", Author.class);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }

    @Override
    public int updateById(int id, Author author) {
        Query query = em.createQuery("update Author a set a.name = :name, a.lastName = :lastName, a.surname = :surname " +
                "where a.id = :id");
        query.setParameter("name", author.getName());
        query.setParameter("lastName", author.getLastName());
        query.setParameter("surname", author.getSurname());
        query.setParameter("id", id);
        return query.executeUpdate();
    }

    @Override
    public int deleteById(int id) {
        Query query = em.createQuery("delete from Author a where a.id = :authorid");
        query.setParameter("authorid", id);
        return query.executeUpdate();
    }
}
