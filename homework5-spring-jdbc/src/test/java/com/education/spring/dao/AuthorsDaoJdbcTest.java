package com.education.spring.dao;

import com.education.spring.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(AuthorsDaoJdbc.class)
class AuthorsDaoJdbcTest {

    @Autowired
    private AuthorsDaoJdbc authorsDaoJdbc;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void getById() {
        Author expectedAuthor = new Author("1", "Антуан", "Сент-Экзюпери", null);
        Author actualAuthor = authorsDaoJdbc.getById(1);
        assertThat(actualAuthor).isEqualToComparingFieldByField(expectedAuthor);
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void getByName() {
        Author expectedAuthor = new Author("1", "Антуан", "Сент-Экзюпери", null);
        Author actualAuthor = authorsDaoJdbc.getByName("Сент-Экзюпери");
        assertThat(actualAuthor).isEqualToComparingFieldByField(expectedAuthor);
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void getAll() {
        Author author1 = new Author("1", "Антуан", "Сент-Экзюпери", null);
        Author author2 = new Author("2", "Льюис", "Керролл", null);
        Author author3 = new Author("3", "Джоан", "Роулинг", null);
        List<Author> expectedAuthors = Arrays.asList(author1, author2, author3);
        List<Author> actualAuthors = authorsDaoJdbc.getAll();
        assertThat(actualAuthors.toString()).isEqualTo(expectedAuthors.toString());
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void insert() {
        Author expectedAuthor = new Author("4", "Айзек", "Азимов", "");
        authorsDaoJdbc.insert(expectedAuthor);
        System.out.println(authorsDaoJdbc.getAll());
        Author actualAuthor = authorsDaoJdbc.getByName("Азимов");
        assertThat(actualAuthor).isEqualToComparingFieldByField(expectedAuthor);
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void nameUpdate() {
        Author expectedAuthor = new Author("3", "Антуан", "Керролл", "");
        authorsDaoJdbc.nameUpdate(expectedAuthor.getLastName(), expectedAuthor.getName());
        Author actualAuthor = authorsDaoJdbc.getByName(expectedAuthor.getLastName());
        assertThat(actualAuthor.getName()).isEqualTo(expectedAuthor.getName());
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void lastNameUpdate() {
        Author expectedAuthor = new Author("3", "Льюис", "Азимов", "");
        authorsDaoJdbc.lastNameUpdate("Керролл", expectedAuthor.getLastName());
        Author actualAuthor = authorsDaoJdbc.getByName(expectedAuthor.getLastName());
        assertThat(actualAuthor.getLastName()).isEqualTo(expectedAuthor.getLastName());
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void surnameUpdate() {
        Author expectedAuthor = new Author("3", "Льюис", "Керролл", "Лютвидж");
        authorsDaoJdbc.surnameUpdate(expectedAuthor.getLastName(), expectedAuthor.getSurname());
        Author actualAuthor = authorsDaoJdbc.getByName(expectedAuthor.getLastName());
        assertThat(actualAuthor.getSurname()).isEqualTo(expectedAuthor.getSurname());
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void deleteById() {
        Author author2 = new Author("2", "Льюис", "Керролл", null);
        Author author3 = new Author("3", "Джоан", "Роулинг", null);
        List<Author> expectedAuthors = Arrays.asList(author2, author3);
        authorsDaoJdbc.deleteById(1);
        List<Author> actualAuthors = authorsDaoJdbc.getAll();
        assertThat(actualAuthors.toString()).isEqualTo(expectedAuthors.toString());
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void deleteByName() {
        Author author2 = new Author("2", "Льюис", "Керролл", null);
        Author author3 = new Author("3", "Джоан", "Роулинг", null);
        List<Author> expectedAuthors = Arrays.asList(author2, author3);
        authorsDaoJdbc.deleteByName("Сент-Экзюпери");
        List<Author> actualAuthors = authorsDaoJdbc.getAll();
        assertThat(actualAuthors.toString()).isEqualTo(expectedAuthors.toString());
    }
}