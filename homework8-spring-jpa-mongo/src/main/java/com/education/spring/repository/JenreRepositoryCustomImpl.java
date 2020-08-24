package com.education.spring.repository;

import com.education.spring.domain.Jenre;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class JenreRepositoryCustomImpl implements JenreRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Jenre update(Jenre newJenre) {
        return em.merge(newJenre);
    }
}
