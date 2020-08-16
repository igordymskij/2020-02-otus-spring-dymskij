package com.education.spring.dao;

import com.education.spring.domain.Book;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class BooksDaoJpaImpl implements BooksDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public Book save(Book book) {
        if (book.getId() <= 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Transactional
    @Override
    public Optional<Book> findById(int id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Transactional
    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public List<Book> findByName(String name) {
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.name = :name", Book.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Transactional
    @Override
    public Book update(Book book) {
        return em.merge(book);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        Book book = findById(id).get();
        em.remove(book);
        em.flush();
    }
}
