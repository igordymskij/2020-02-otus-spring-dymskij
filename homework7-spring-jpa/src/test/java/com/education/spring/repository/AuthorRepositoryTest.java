package com.education.spring.repository;

import com.education.spring.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private TestEntityManager em;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void findByIdTest() {
        Author actualAuthor = new Author(0, "Антуан", "Сент-Экзюпери", null);
        em.persist(actualAuthor);
        Optional<Author> optionalAuthor = authorRepository.findById(1);
        Author expectedAuthor = optionalAuthor.get();
        assertThat(actualAuthor).isEqualToComparingFieldByField(expectedAuthor);
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void findByNameTest() {
        Author actualAuthor = new Author(0, "Антуан", "Сент-Экзюпери", null);
        List<Author> actualAuthorList = Arrays.asList(actualAuthor);
        em.persist(actualAuthor);
        List<Author> expectedAuthorList = authorRepository.findByLastName("Сент-Экзюпери");
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
        List<Author> expectedAuthorsList = authorRepository.findAll();
        assertThat(actualAuthorsList).isEqualTo(expectedAuthorsList);
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void nameUpdateTest() {
        Author actualAuthor = new Author(0, "Рене", "Керролл", "");
        em.persist(actualAuthor);
        actualAuthor.setName("Льюис");
        authorRepository.update(actualAuthor);
        em.refresh(actualAuthor);
        Optional<Author> optionalAuthor = authorRepository.findById(1);
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
        authorRepository.deleteById(1);
        List<Author> expectedAuthorsList = authorRepository.findAll();
        assertThat(actualAuthorsList).isEqualTo(expectedAuthorsList);
    }
}