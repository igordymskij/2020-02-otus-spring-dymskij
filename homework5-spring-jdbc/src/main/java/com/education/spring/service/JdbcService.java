package com.education.spring.service;

import com.education.spring.dao.AuthorsDao;
import com.education.spring.dao.BooksDao;
import com.education.spring.dao.JenresDao;
import com.education.spring.domain.Author;
import com.education.spring.domain.Book;
import com.education.spring.domain.Jenre;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JdbcService {

    private final AuthorsDao authorsDao;
    private final BooksDao booksDao;
    private final JenresDao jenresDao;

    public JdbcService(AuthorsDao authorsDao, BooksDao booksDao, JenresDao jenresDao) {
        this.authorsDao = authorsDao;
        this.booksDao = booksDao;
        this.jenresDao = jenresDao;
    }

    public Book insertBookInfo(Map<String, String> bookInfo) {
        Author author = new Author(null,
                bookInfo.get("authorName"),
                bookInfo.get("authorLastName"),
                bookInfo.get("authorSurName"));
        authorsDao.insert(author);
        author.setId(getAuthorByName(author.getLastName()).getId());
        Jenre jenre = new Jenre(null, bookInfo.get("bookJenre"));
        jenresDao.insert(jenre);
        jenre.setId(getJenreByName(jenre.getJenre()).getId());
        Book book = new Book(null,
                bookInfo.get("bookName"),
                author, jenre,
                bookInfo.get("bookYear"));
        booksDao.insert(book);
        return getBookByName(book.getName());
    }

    public String updateBookName(String name, String newName) {
        if(booksDao.nameUpdate(name, newName) == 1)
            return String.format("Новое название книги: %s", booksDao.getByName(newName).getName());
        return "Название книги не изменено!";
    }

    public String updateBookAuthorName(String name, String newAuthorName) {
        Author author = this.getAuthorByName(newAuthorName);
        if (author == null) {
            return "Данного автора нет в базе!";
        }
        else if(booksDao.authorUpdate(name, author.getId()) == 1)
            return String.format("Новый автор книги: %s %s", author.getName(), author.getLastName());
        return "Автор книги не изменён";
    }

    public String updateBookJenreName(String name, String newJenreName) {
        Jenre jenre = this.getJenreByName(newJenreName);
        if (jenre == null) {
            return "Данного жанра нет в базе!";
        }
        else if(booksDao.jenreUpdate(name, jenre.getId()) == 1)
            return String.format("Новый жанр книги: %s", jenre.getJenre());
        return "Жанр книги не изменён!";
    }

    public String updateBookYear(String name, String newYear) {
        if(booksDao.yearUpdate(name, newYear) == 1)
            return String.format("Изменённый год выпуска книги: %s", booksDao.getByName(name).getYear());
        return "Год выпуска книги не изменен!";
    }

    public Book getBookByName(String bookName) {
        try {
            return booksDao.getByName(bookName);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public String deleteBookByName(String bookName) {
        if (booksDao.deleteByName(bookName) == 1)
            return "Книга успешно удалена!";
        return "Книга не удалена!";
    }

    public List<Book> getAllBooks() {
        try {
            return booksDao.getAll();
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public Author insertAuthorInfo(Map<String, String> authorInfo) {
        Author author = new Author(null,
                authorInfo.get("authorName"),
                authorInfo.get("authorLastName"),
                authorInfo.get("authorSurName"));
        authorsDao.insert(author);
        return getAuthorByName(author.getName());
    }

    public Author getAuthorByName(String authorName) {
        try {
            return authorsDao.getByName(authorName);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public String updateAuthorLastName(String lastName, String newLastName) {
        if(authorsDao.lastNameUpdate(lastName, newLastName) == 1)
            return String.format("Новая фамилия автора книги: %s", authorsDao.getByName(newLastName).getLastName());
        return "Фамилия автора книги не изменена!";
    }

    public String updateAuthorName(String lastName, String newName) {
        if(authorsDao.nameUpdate(lastName, newName) == 1)
            return String.format("Новое имя автора книги: %s", authorsDao.getByName(lastName).getName());
        return "Имя автора книги не изменено!";
    }

    public String updateAuthorSurname(String lastName, String newSurname) {
        if(authorsDao.surnameUpdate(lastName, newSurname) == 1)
            return String.format("Новое отчество автора книги: %s", authorsDao.getByName(lastName).getSurname());
        return "Отчество автора книги не изменено!";
    }

    public String deleteAuthorByName(String authorName) {
        if (authorsDao.deleteByName(authorName) == 1)
            return "Автор успешно удалён!";
        return "Автор не удалён!";
    }

    public List<Author> getAllAuthors() {
        try {
            return authorsDao.getAll();
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public Jenre insertJenreInfo(String name) {
        Jenre jenre = new Jenre(null, name);
        jenresDao.insert(jenre);
        return jenresDao.getByName(jenre.getJenre());
    }

    public Jenre getJenreByName(String jenre) {
        try {
            return jenresDao.getByName(jenre);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public String updateJenre(String jenre, String newJenre) {
        if(jenresDao.jenreUpdate(jenre, newJenre) == 1)
            return String.format("Новый жанр книг: %s", jenresDao.getByName(newJenre).getJenre());
        return "Жанр книг не изменен!";
    }

    public String deleteJenreByName(String jenre) {
        if (jenresDao.deleteByName(jenre) == 1)
            return "Жанр успешно удалён!";
        return "Жанр не удалён!";
    }

    public List<Jenre> getAllJenres() {
        try {
            return jenresDao.getAll();
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }


}
