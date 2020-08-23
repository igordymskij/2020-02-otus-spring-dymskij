package com.education.spring.service;

import com.education.spring.dao.BooksDao;
import com.education.spring.domain.Author;
import com.education.spring.domain.Book;
import com.education.spring.domain.Jenre;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookServiceImpl implements BookService, AppService {

    private final BooksDao booksDao;
    private final AuthorService authorService;
    private final JenreService jenreService;

    public BookServiceImpl(BooksDao booksDao, AuthorService authorService, JenreService jenreService) {
        this.booksDao = booksDao;
        this.authorService = authorService;
        this.jenreService = jenreService;
    }

    public String insertBookInfo(Book book) {
        if (checkDublicateBook(book))
            return "Данная книга уже есть в базе!";
        Author authorDB = authorService.checkDublicateAuthor(book.getAuthor());
        if (authorDB != null) {
            book.setAuthor(null);
            booksDao.save(book);
            book.setAuthor(authorDB);
            booksDao.update(book);
            return "Книга успешно сохранена!";
        }
        booksDao.save(book);
        return "Книга успешно сохранена!";
    }

    public boolean checkDublicateBook(Book book) {
        List<Book> books = booksDao.findAll();
        for (Book bookExample: books) {
            book.setId(bookExample.getId());
            book.getAuthor().setId(bookExample.getAuthor().getId());
            book.getJenre().setId(bookExample.getJenre().getId());
            if (book.equals(bookExample)) {
                return true;
            }
        }
        return false;
    }

    public String updateBookName(Book book, String newName) {
        book.setName(newName);
        if(booksDao.update(book) != null)
            return String.format("Новое название книги: %s", book.getName());
        return "Название книги не изменено!";
    }

    public String updateBookAuthorName(Book book, Author author) {
        Author newAuthor = authorService.findAuthor(author);
        if (newAuthor == null) {
            return DONT_HAVE_AUTHOR;
        }
        book.setAuthor(newAuthor);
        if(booksDao.update(book) != null)
            return String.format("Новый автор книги: %s %s", book.getAuthor().getName(),
                    book.getAuthor().getLastName());
        return "Автор книги не изменён!";
    }

    public String updateBookJenreName(Book book, String newJenreName) {
        Jenre jenre = jenreService.getJenreByName(newJenreName);
        if (jenre == null) {
            return DONT_HAVE_JENRE;
        }
        book.getJenre().setJenre(newJenreName);
        if(booksDao.update(book) != null)
            return String.format("Новый жанр книги: %s", book.getJenre().getJenre());
        return "Жанр книги не изменён!";
    }

    public String updateBookYear(Book book, String newYear) {
        book.setYear(newYear);
        if(booksDao.update(book) != null)
            return String.format("Изменённый год выпуска книги: %s", book.getYear());
        return "Год выпуска книги не изменен!";
    }

    public List<Book> findBookByName(String lastName) {
        try {
            return booksDao.findByName(lastName);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public Book findBook(Book book) {
        try {
            List<Book> booksList = booksDao.findByName(book.getName());
            for (Book bookExample: booksList) {
                book.setId(bookExample.getId());
                book.getAuthor().setId(bookExample.getAuthor().getId());
                book.getJenre().setId(bookExample.getJenre().getId());
                if (book.equals(bookExample))
                    return bookExample;
            }
            return null;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public String deleteBookByName(Book bookInfo) {
        Book book = findBook(bookInfo);
        if (book == null)
            return DONT_HAVE_BOOK;
        booksDao.deleteById(book.getId());
        if (findBook(bookInfo) == null)
            return "Книга успешно удалена!";
        return "Книга не удалена!";
    }

    public List<Book> getAllBooks() {
        try {
            return booksDao.findAll();
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
}
