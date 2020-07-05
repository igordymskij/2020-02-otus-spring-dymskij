package com.education.spring.dao;

import com.education.spring.domain.Author;
import com.education.spring.domain.Book;
import com.education.spring.domain.Jenre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(BooksDaoJdbc.class)
public class BooksDaoJdbcTest {

    @Autowired
    private BooksDaoJdbc booksDaoJdbc;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    public void shoudReturnExpectedBookByName() {
        Book expectedBook = new Book("1", "Маленький принц",
                                new Author("1", "Антуан", "Сент-Экзюпери", null),
                                new Jenre("1", "Приключения"),
                                "1942");
        Book actualBook = booksDaoJdbc.getByName("Маленький принц");
        assertThat(actualBook.toString()).isEqualTo(expectedBook.toString());
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    public void shoudReturnExpectedBookById() {
        Book expectedBook = new Book("1", "Маленький принц",
                new Author("1", "Антуан", "Сент-Экзюпери", null),
                new Jenre("1", "Приключения"),
                "1942");
        Book actualBook = booksDaoJdbc.getById(1);
        assertThat(actualBook.toString()).isEqualTo(expectedBook.toString());
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    public void shoudReturnExpectedAllBook() {
        Book book1 = new Book("1", "Маленький принц",
                new Author("1", "Антуан", "Сент-Экзюпери", null),
                new Jenre("1", "Приключения"),
                "1942");
        Book book2 = new Book("2", "Приключения Алисы в Стране чудес",
                new Author("2", "Льюис", "Керролл", null),
                new Jenre("1", "Приключения"),
                "1865");
        List<Book> expectedBooks = Arrays.asList(book1, book2);
        List<Book> actualBooks = booksDaoJdbc.getAll();
        assertThat(actualBooks.toString()).isEqualTo(expectedBooks.toString());
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    public void shoudReturnExpectedInsertBook() {
        Book expectedBook = new Book("3", "Гарри Поттер и философский камень",
                new Author("3", "Джоан", "Роулинг", null),
                new Jenre("1", "Приключения"),
                "1997");
        int i = booksDaoJdbc.insert(expectedBook);
        Book actualBook = booksDaoJdbc.getByName("Гарри Поттер и философский камень");
        System.out.println(actualBook);
        System.out.println(expectedBook);
        assertThat(actualBook.toString()).isEqualTo(expectedBook.toString());
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    public void shoudReturnExpectedUpdateBookName() {
        Book expectedBook = new Book("1", "Принц",
                new Author("1", "Антуан", "Сент-Экзюпери", null),
                new Jenre("1", "Приключения"),
                "1942");
        booksDaoJdbc.nameUpdate("Маленький принц", "Принц");
        Book actualBook = booksDaoJdbc.getByName("Принц");
        assertThat(actualBook.toString()).isEqualTo(expectedBook.toString());
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    public void shoudReturnExpectedUpdateBookAuthor() throws InterruptedException {
        Book expectedBook = new Book("1", "Маленький принц",
                new Author("2", "Льюис", "Керролл", null),
                new Jenre("1", "Приключения"),
                "1942");
        booksDaoJdbc.authorUpdate("Маленький принц", "2");
        Book actualBook = booksDaoJdbc.getByName("Маленький принц");
        assertThat(actualBook.toString()).isEqualTo(expectedBook.toString());
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    public void shoudReturnExpectedUpdateBookJenre() {
        Book expectedBook = new Book("1", "Маленький принц",
                new Author("2", "Льюис", "Керролл", null),
                new Jenre("2", "Фантастика"),
                "1942");
        booksDaoJdbc.jenreUpdate("Маленький принц", "2");
        Book actualBook = booksDaoJdbc.getByName("Маленький принц");
        assertThat(actualBook.getJenre()).isEqualToComparingFieldByField(expectedBook.getJenre());
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    public void shoudReturnExpectedUpdateBookYear() {
        Book expectedBook = new Book("1", "Маленький принц",
                new Author("1", "Антуан", "Сент-Экзюпери", null),
                new Jenre("1", "Приключения"),
                "1943");
        booksDaoJdbc.yearUpdate("Маленький принц", "1943");
        Book actualBook = booksDaoJdbc.getByName("Маленький принц");
        assertThat(actualBook.getYear()).isEqualTo(expectedBook.getYear());
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    public void shoudReturnExpectedDeleteBookByName() {
        Book book2 = new Book("2", "Приключения Алисы в Стране чудес",
                new Author("2", "Льюис", "Керролл", null),
                new Jenre("1", "Приключения"),
                "1865");
        List<Book> expectedBooks = Arrays.asList(book2);
        booksDaoJdbc.deleteByName("Маленький принц");
        List<Book> actualBooks = booksDaoJdbc.getAll();
        assertThat(actualBooks.toString()).isEqualTo(expectedBooks.toString());
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    public void shoudReturnExpectedDeleteBookById() {
        Book book2 = new Book("2", "Приключения Алисы в Стране чудес",
                new Author("2", "Льюис", "Керролл", null),
                new Jenre("1", "Приключения"),
                "1865");
        List<Book> expectedBooks = Arrays.asList(book2);
        booksDaoJdbc.deleteById(1);
        List<Book> actualBooks = booksDaoJdbc.getAll();
        assertThat(actualBooks.toString()).isEqualTo(expectedBooks.toString());
    }
}
