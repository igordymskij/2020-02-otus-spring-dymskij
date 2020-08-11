package com.education.spring.service;

import com.education.spring.dao.AuthorsDao;
import com.education.spring.dao.BooksDao;
import com.education.spring.dao.CommentsDao;
import com.education.spring.dao.JenresDao;
import com.education.spring.domain.Author;
import com.education.spring.domain.Book;
import com.education.spring.domain.Comment;
import com.education.spring.domain.Jenre;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class JpaService {

    private final String DONT_HAVE_BOOK = "Данного книги нет в базе!";
    private final String DONT_HAVE_AUTHOR = "Данного автора нет в базе!";
    private final String DONT_HAVE_JENRE = "Данного жанра нет в базе!";

    private final AuthorsDao authorsDao;
    private final BooksDao booksDao;
    private final JenresDao jenresDao;
    private final CommentsDao commentDao;

    public JpaService(AuthorsDao authorsDao, BooksDao booksDao, JenresDao jenresDao, CommentsDao commentDao) {
        this.authorsDao = authorsDao;
        this.booksDao = booksDao;
        this.jenresDao = jenresDao;
        this.commentDao = commentDao;
    }

    public String insertBookInfo(Map<String, String> bookInfo) {
        Author author = new Author(0,
                bookInfo.get("authorName"),
                bookInfo.get("authorLastName"),
                bookInfo.get("authorSurname"));
        Jenre jenre = new Jenre(0, bookInfo.get("bookJenre"));
        Book book = new Book(0,
                bookInfo.get("bookName"),
                author, jenre,
                bookInfo.get("bookYear"));
        if (checkDublicateBook(book))
            return "Данная книга уже есть в базе!";
        Author authorDB = checkDublicateAuthor(book.getAuthor());
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

    private boolean checkDublicateBook(Book book) {
        List<Book> books = booksDao.findAll();
        for (Book bookExample: books) {
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

    public String updateBookAuthorName(Book book, Map<String, String> authorMap) {
        Author newAuthor = this.findAuthor(authorMap);
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
        Jenre jenre = this.getJenreByName(newJenreName);
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

    public Book findBook(Map<String, String> bookName) {
        try {
            Book book = new Book(0, bookName.get("bookName"),
                                    new Author(0, bookName.get("authorName"),
                                                      bookName.get("authorLastName"),
                                                      bookName.get("authorSurname")),
                                    new Jenre(0, bookName.get("bookJenre")),
                                    bookName.get("bookYear"));
            List<Book> booksList = booksDao.findByName(bookName.get("bookName"));
            for (Book bookExample: booksList) {
                if (book.equals(bookExample))
                    return bookExample;
            }
            return null;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public String deleteBookByName(Map<String, String> bookInfo) {
        Book book = this.findBook(bookInfo);;
        if (book == null)
            return DONT_HAVE_BOOK;
        booksDao.deleteById(book.getId());
        if (this.findBook(bookInfo) == null)
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

    public String insertAuthorInfo(Map<String, String> authorInfo) {
        Author author = new Author(0,
                authorInfo.get("authorName"),
                authorInfo.get("authorLastName"),
                authorInfo.get("authorSurname"));
        if (checkDublicateAuthor(author) != null)
            return "Автор уже есть в базе!";
        authorsDao.save(author);
        return "Автор успешно сохранен!";
    }

    private Author checkDublicateAuthor(Author author) {
        List<Author> authors = authorsDao.findAll();
        if (authors == null)
            return null;
        for (Author authorExample: authors) {
            if (author.equals(authorExample)) {
                return authorExample;
            }
        }
        return null;
    }

    public List<Author> findAuthorByName(String authorName) {
        try {
            return authorsDao.findByName(authorName);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public Author findAuthor(Map<String, String> authorName) {
        try {
            Author author = new Author(0, authorName.get("authorName"),
                                          authorName.get("authorLastName"),
                                          authorName.get("authorSurname"));
            List<Author> authorsList = authorsDao.findByName(authorName.get("authorLastName"));
            if (authorsList.isEmpty())
                return null;
            for (Author authorExample: authorsList) {
                if (author.equals(authorExample))
                    return authorExample;
            }
            return null;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public String updateAuthorLastName(Map<String, String> authorName, String newLastName) {
        Author author = this.findAuthor(authorName);
        if (author == null)
            return DONT_HAVE_AUTHOR;
        author.setLastName(newLastName);
        if(authorsDao.update(author) != null)
            return String.format("Новая фамилия автора книги: %s", author.getLastName());
        return "Фамилия автора книги не изменена!";
    }

    public String updateAuthorName(Map<String, String> authorName, String newName) {
        Author author = this.findAuthor(authorName);
        if (author == null)
            return DONT_HAVE_AUTHOR;
        author.setName(newName);
        if(authorsDao.update(author) != null)
            return String.format("Новое имя автора книги: %s", author.getName());
        return "Имя автора книги не изменено!";
    }

    public String updateAuthorSurname(Map<String, String> authorName, String newSurname) {
        Author author = this.findAuthor(authorName);
        if (author == null)
            return DONT_HAVE_AUTHOR;
        author.setSurname(newSurname);
        if(authorsDao.update(author) != null)
            return String.format("Новое отчество автора книги: %s", author.getSurname());
        return "Отчество автора книги не изменено!";
    }

    public String deleteAuthorByName(Map<String, String> authorName) {
        Author author = this.findAuthor(authorName);
        if (author == null)
            return DONT_HAVE_AUTHOR;
        authorsDao.deleteById(author.getId());
        if (this.findAuthor(authorName) == null)
            return "Автор успешно удалён!";
        return "Автор не удалён!";
    }

    public List<Author> getAllAuthors() {
        try {
            return authorsDao.findAll();
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public Jenre findJenreByName(String jenre) {
        try {
            return jenresDao.findByName(jenre);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public String insertJenreInfo(Map<String, String> jenreInfo) {
        Jenre jenre = new Jenre(0, jenreInfo.get("jenre"));
        if (checkDublicateJenre(jenre))
            return "Жанр уже есть в базе!";
        jenresDao.save(jenre);
        return "Жанр успешно сохранен!";
    }

    private boolean checkDublicateJenre(Jenre jenre) {
        List<Jenre> jenres = jenresDao.findAll();
        for (Jenre jenreExample: jenres) {
            if (jenre.equals(jenreExample)) {
                return true;
            }
        }
        return false;
    }

    public Jenre getJenreByName(String jenre) {
        try {
            return jenresDao.findByName(jenre);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public String updateJenre(String name, String newJenre) {
        Jenre jenre = this.findJenreByName(name);
        if (jenre == null)
            return DONT_HAVE_JENRE;
        jenre.setJenre(newJenre);
        if(jenresDao.update(jenre) != null)
            return String.format("Новый жанр книг: %s", jenre.getJenre());
        return "Жанр книг не изменен!";
    }

    public String deleteJenreByName(String name) {
        Jenre jenre = this.findJenreByName(name);
        if (jenre == null)
            return DONT_HAVE_JENRE;
        jenresDao.deleteById(jenre.getId());
        if (this.findJenreByName(name) == null)
            return "Жанр успешно удалён!";
        return "Жанр не удалён!";
    }

    public List<Jenre> getAllJenres() {
        try {
            return jenresDao.findAll();
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public String insertCommentInfo(Book book, String bookComment) {
        Comment comment = new Comment(0, book, bookComment);
        commentDao.save(comment);
        return "Комментарий успешно сохранен!";
    }

    public Comment findCommentById(int id) {
        Optional<Comment> optionalComment = commentDao.findById(id);
        Comment comment = optionalComment.get();
        if (comment == null)
            return null;
        return comment;
    }

    public String updateCommentById(int id, String updateComment) {
        Comment comment = findCommentById(id);
        if (comment == null)
            return "Комментария нет в базе";
        comment.setComment(updateComment);
        if(commentDao.update(comment) != null)
            return String.format("Новый комментарий для книги: %s", comment.getComment());
        return "Комментарий для книги не изменен!";
    }

    public String deleteCommentById(int id) {
        Comment comment = findCommentById(id);
        if (comment == null)
            return "Комментария нет в базе";
        commentDao.deleteById(id);
        if (commentDao.findById(id) == null)
            return "Комментарий успешно удалён!";
        return "Комментарий не удалён!";
    }

    public List<Comment> getAllComments() {
        try {
            return commentDao.findAll();
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }


}
