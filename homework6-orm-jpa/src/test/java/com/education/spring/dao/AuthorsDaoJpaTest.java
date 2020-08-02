package com.education.spring.dao;

import com.education.spring.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(AuthorsDaoJpaImpl.class)
class AuthorsDaoJpaTest {

    @Autowired
    private AuthorsDaoJpaImpl authorsDaoJpa;

    @Autowired
    private TestEntityManager em;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void findByIdTest() {
        Author actualAuthor = new Author(0, "Антуан", "Сент-Экзюпери", null);
        em.persist(actualAuthor);
        Optional<Author> optionalAuthor = authorsDaoJpa.findById(1);
        Author expectedAuthor = optionalAuthor.get();
        assertThat(actualAuthor).isEqualToComparingFieldByField(expectedAuthor);
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void findByNameTest() {
        Author actualAuthor = new Author(0, "Антуан", "Сент-Экзюпери", null);
        List<Author> actualAuthorList = Arrays.asList(actualAuthor);
        em.persist(actualAuthor);
        List<Author> expectedAuthorList = authorsDaoJpa.findByName("Сент-Экзюпери");
        assertThat(actualAuthorList).isEqualTo(expectedAuthorList);
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void findAllTest() {
        Author author1 = new Author(0, "Антуан", "Сент-Экзюпери", null);
        Author author2 = new Author(0, "Льюис", "Керролл", null);
        Author author3 = new Author(0, "Джоан", "Роулинг", null);
        em.persist(author1);
        em.persist(author2);
        em.persist(author3);
        List<Author> actualAuthorsList = Arrays.asList(author1, author2, author3);
        List<Author> expectedAuthorsList = authorsDaoJpa.findAll();
        assertThat(actualAuthorsList).isEqualTo(expectedAuthorsList);
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void nameUpdateTest() {
        Author mistackesAuthor = new Author(0, "Рене", "Керролл", "");
        Author actualAuthor = new Author(1, "Льюис", "Керролл", "");
        em.persist(mistackesAuthor);
        authorsDaoJpa.updateById(1, actualAuthor);
        em.clear();
        Optional<Author> optionalAuthor = authorsDaoJpa.findById(1);
        Author expectedAuthor = optionalAuthor.get();
        assertThat(actualAuthor).isEqualTo(expectedAuthor);
    }


    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void deleteByIdTest() {
        Author author1 = new Author(0, "Льюис", "Керролл", null);
        Author author2 = new Author(0, "Джоан", "Роулинг", null);
        List<Author> actualAuthorsList = Arrays.asList(author2);
        em.persist(author1);
        em.persist(author2);
        authorsDaoJpa.deleteById(1);
        List<Author> expectedAuthorsList = authorsDaoJpa.findAll();
        assertThat(actualAuthorsList).isEqualTo(expectedAuthorsList);
    }
}