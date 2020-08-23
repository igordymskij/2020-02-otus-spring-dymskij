package com.education.spring.repository;

import com.education.spring.domain.Author;
import com.education.spring.domain.Book;
import com.education.spring.domain.Jenre;
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
@Import(BookRepository.class)
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager em;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    public void findByNameTest() {
        Book actualBook = new Book(0, "Маленький принц",
                                new Author(0, "Антуан", "Сент-Экзюпери", null),
                                new Jenre(0, "Приключения"),
                                "1942");
        List<Book> actualBookList = Arrays.asList(actualBook);
        em.persist(actualBook);
        List<Book> expectedBookList = bookRepository.findByName("Маленький принц");
        assertThat(actualBookList).isEqualTo(expectedBookList);
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    public void findByIdTest() {
        Book actualBook = new Book(0, "Маленький принц",
                new Author(0, "Антуан", "Сент-Экзюпери", null),
                new Jenre(0, "Приключения"),
                "1942");
        em.persist(actualBook);
        Optional<Book> optionalBook = bookRepository.findById(1);
        Book expectedBook = optionalBook.get();
        assertThat(actualBook).isEqualTo(expectedBook);
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    public void findByAllTest() {
        Book book1 = new Book(0, "Маленький принц",
                new Author(0, "Антуан", "Сент-Экзюпери", null),
                new Jenre(0, "Приключения"),
                "1942");
        Book book2 = new Book(0, "Приключения Алисы в Стране чудес",
                new Author(0, "Льюис", "Керролл", null),
                new Jenre(0, "Приключения"),
                "1865");
        List<Book> actualBooksList = Arrays.asList(book1, book2);
        em.persist(book1);
        em.persist(book2);
        List<Book> expectedBooksList = bookRepository.findAll();
        assertThat(actualBooksList).isEqualTo(expectedBooksList);
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    public void updateByIdTest() {
        Book actualBook = new Book(0, "Маленький принц",
                new Author(0, "Рене", "Сент-Экзюпери", ""),
                new Jenre(0, "Фантастика"),
                "1942");
        em.persist(actualBook);
        actualBook.getAuthor().setName("Керролл");
        bookRepository.update(actualBook);
        em.refresh(actualBook);
        Optional<Book> optionalBook = bookRepository.findById(1);
        Book expectedBook = optionalBook.get();
        assertThat(actualBook).isEqualTo(expectedBook);
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    public void deleteByIdTest() {
        Book book1 = new Book(0, "Приключения Алисы в Стране чудес",
                new Author(0, "Льюис", "Керролл", ""),
                new Jenre(0, "Приключения"),
                "1865");
        Book book2 = new Book(0, "Маленький принц",
                new Author(0, "Антуан", "Сент-Экзюпери", ""),
                new Jenre(0, "Приключения"),
                "1942");
        List<Book> actualBooksList = Arrays.asList(book2);
        em.persist(book1);
        em.persist(book2);
        bookRepository.deleteById(1);
        List<Book> expectedBooksList = bookRepository.findAll();
        assertThat(actualBooksList).isEqualTo(expectedBooksList);
    }
}
