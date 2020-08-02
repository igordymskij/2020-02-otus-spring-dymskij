package com.education.spring.dao;

import com.education.spring.domain.Book;
import com.education.spring.domain.Jenre;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Transactional
@Repository
public class JenresDaoJpaImpl implements JenresDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Jenre save(Jenre jenre) {
        if (jenre.getId() <= 0) {
            em.persist(jenre);
            return jenre;
        } else {
            return em.merge(jenre);
        }
    }

    @Override
    public Optional<Jenre> findById(int id) {
        return Optional.ofNullable(em.find(Jenre.class, id));
    }

    @Override
    public List<Jenre> findAll() {
        TypedQuery<Jenre> query = em.createQuery("select j from Jenre j", Jenre.class);
        return query.getResultList();
    }

    @Override
    public Jenre findByName(String jenre) {
        TypedQuery<Jenre> query = em.createQuery("select j from Jenre j where j.jenre = :jenre", Jenre.class);
        query.setParameter("jenre", jenre);
        return query.getSingleResult();
    }

    @Override
    public int updateById(int id, Jenre jenre) {
        Query query = em.createQuery("update Jenre j set j.jenre = :jenre where j.id = :id");
        query.setParameter("id", id);
        query.setParameter("jenre", jenre.getJenre());
        return query.executeUpdate();
    }

    @Override
    public int deleteById(int id) {
        Query query = em.createQuery("delete from Jenre j where j.id = :id");
        query.setParameter("id", id);
        return query.executeUpdate();
    }
}
