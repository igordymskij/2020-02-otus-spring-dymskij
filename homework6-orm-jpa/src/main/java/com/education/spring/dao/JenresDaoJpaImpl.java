package com.education.spring.dao;

import com.education.spring.domain.Jenre;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class JenresDaoJpaImpl implements JenresDao{

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public Jenre save(Jenre jenre) {
        if (jenre.getId() <= 0) {
            em.persist(jenre);
            return jenre;
        } else {
            return em.merge(jenre);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Jenre> findById(int id) {
        return Optional.ofNullable(em.find(Jenre.class, id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Jenre> findAll() {
        TypedQuery<Jenre> query = em.createQuery("select j from Jenre j", Jenre.class);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public Jenre findByName(String jenre) {
        TypedQuery<Jenre> query = em.createQuery("select j from Jenre j where j.jenre = :jenre", Jenre.class);
        query.setParameter("jenre", jenre);
        return query.getSingleResult();
    }

    @Transactional
    @Override
    public Jenre update(Jenre jenre) {
        return em.merge(jenre);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        Jenre jenre = findById(id).get();
        em.remove(jenre);
        em.flush();
    }
}
