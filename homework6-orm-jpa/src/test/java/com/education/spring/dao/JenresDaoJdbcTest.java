package com.education.spring.dao;

import com.education.spring.domain.Jenre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@JdbcTest
@Import(JenresDaoJpaImpl.class)
class JenresDaoJdbcTest {

//    @Autowired
//    private JenresDaoJpaImpl jenresDaoJdbc;
//
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
//    @Test
//    void getById() {
//        Jenre expectedJenre = new Jenre("1", "Приключения");
//        Jenre actualJenre = jenresDaoJdbc.getById(1);
//        assertThat(actualJenre).isEqualToComparingFieldByField(expectedJenre);
//    }
//
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
//    @Test
//    void getByName() {
//        Jenre expectedJenre = new Jenre("1", "Приключения");
//        Jenre actualJenre = jenresDaoJdbc.getByName("Приключения");
//        assertThat(actualJenre).isEqualToComparingFieldByField(expectedJenre);
//    }
//
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
//    @Test
//    void getAll() {
//        Jenre jenre1 = new Jenre("1", "Приключения");
//        Jenre jenre2 = new Jenre("2", "Фантастика");
//        List<Jenre> expectedJenres = Arrays.asList(jenre1, jenre2);
//        List<Jenre> actualJenres = jenresDaoJdbc.getAll();
//        assertThat(actualJenres.toString()).isEqualTo(expectedJenres.toString());
//    }
//
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
//    @Test
//    void insert() {
//        Jenre expectedJenre = new Jenre("3", "Детектив");
//        jenresDaoJdbc.insert(expectedJenre);
//        Jenre actualJenre = jenresDaoJdbc.getById(3);
//        assertThat(actualJenre).isEqualToComparingFieldByField(expectedJenre);
//    }
//
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
//    @Test
//    void jenreUpdate() {
//        Jenre expectedJenre = new Jenre("1", "Сказки");
//        jenresDaoJdbc.jenreUpdate("Приключения", expectedJenre.getJenre());
//        Jenre actualJenre = jenresDaoJdbc.getById(1);
//        assertThat(actualJenre).isEqualToComparingFieldByField(expectedJenre);
//    }
//
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
//    @Test
//    void deleteById() {
//        Jenre jenre2 = new Jenre("2", "Фантастика");
//        List<Jenre> expectedJenres = Arrays.asList(jenre2);
//        jenresDaoJdbc.deleteById(1);
//        List<Jenre> actualJenres = jenresDaoJdbc.getAll();
//        assertThat(actualJenres.toString()).isEqualTo(expectedJenres.toString());
//    }
//
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
//    @Test
//    void deleteByName() {
//        Jenre jenre2 = new Jenre("2", "Фантастика");
//        List<Jenre> expectedJenres = Arrays.asList(jenre2);
//        jenresDaoJdbc.deleteByName("Приключения");
//        List<Jenre> actualJenres = jenresDaoJdbc.getAll();
//        assertThat(actualJenres.toString()).isEqualTo(expectedJenres.toString());
//    }
}