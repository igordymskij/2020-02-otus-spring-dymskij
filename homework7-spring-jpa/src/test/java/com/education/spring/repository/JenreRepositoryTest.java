package com.education.spring.repository;

import com.education.spring.domain.Jenre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@Import(JenreRepository.class)
class JenreRepositoryTest {

    @Autowired
    private JenreRepository jenreRepository;

    @Autowired
    private TestEntityManager em;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void findByIdTest() {
        Jenre actualJenre = new Jenre(0, "Приключения");
        em.persist(actualJenre);
        Jenre expectedJenre = jenreRepository.findById(1).get();
        assertThat(actualJenre).isEqualTo(expectedJenre);
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void findByNameTest() {
        Jenre actualJenre = new Jenre(0, "Приключения");
        em.persist(actualJenre);
        Jenre expectedJenre = jenreRepository.findByJenre("Приключения");
        assertThat(actualJenre).isEqualTo(expectedJenre);
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void findAllTest() {
        Jenre jenre1 = new Jenre(0, "Приключения");
        Jenre jenre2 = new Jenre(0, "Фантастика");
        List<Jenre> expectedJenres = Arrays.asList(jenre1, jenre2);
        em.persist(jenre1);
        em.persist(jenre2);
        List<Jenre> actualJenres = jenreRepository.findAll();
        assertThat(actualJenres.toString()).isEqualTo(expectedJenres.toString());
    }


    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void updateByIdTest() {
        Jenre expectedJenre = new Jenre(0, "Сказки");
        em.persist(expectedJenre);
        expectedJenre.setJenre("Фантастика");
        jenreRepository.update(expectedJenre);
        Jenre actualJenre = jenreRepository.findById(1).get();
        assertThat(actualJenre).isEqualTo(expectedJenre);
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void deleteByIdTest() {
        Jenre jenre1 = new Jenre(0, "Фантастика");
        Jenre jenre2 = new Jenre(0, "Сказки");
        List<Jenre> expectedJenres = Arrays.asList(jenre2);
        em.persist(jenre1);
        em.persist(jenre2);
        jenreRepository.deleteById(1);
        List<Jenre> actualJenres = jenreRepository.findAll();
        assertThat(actualJenres).isEqualTo(expectedJenres);
    }

}