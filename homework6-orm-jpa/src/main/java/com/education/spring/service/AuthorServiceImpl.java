package com.education.spring.service;

import com.education.spring.dao.AuthorsDao;
import com.education.spring.domain.Author;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService, AppService {

    private final AuthorsDao authorsDao;

    public AuthorServiceImpl(AuthorsDao authorsDao) {
        this.authorsDao = authorsDao;
    }

    public String insertAuthorInfo(Author author) {
        if (checkDublicateAuthor(author) != null)
            return "Автор уже есть в базе!";
        authorsDao.save(author);
        return "Автор успешно сохранен!";
    }

    public Author checkDublicateAuthor(Author author) {
        List<Author> authors = authorsDao.findAll();
        if (authors == null)
            return null;
        for (Author authorExample: authors) {
            author.setId(authorExample.getId());
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

    public Author findAuthor(Author author) {
        try {
            List<Author> authorsList = authorsDao.findByName(author.getLastName());
            if (authorsList.isEmpty())
                return null;
            for (Author authorExample: authorsList) {
                author.setId(authorExample.getId());
                if (author.equals(authorExample))
                    return authorExample;
            }
            return null;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public String updateAuthorLastName(Author authorInfo, String newLastName) {
        Author author = this.findAuthor(authorInfo);
        if (author == null)
            return DONT_HAVE_AUTHOR;
        author.setLastName(newLastName);
        if(authorsDao.update(author) != null)
            return String.format("Новая фамилия автора книги: %s", author.getLastName());
        return "Фамилия автора книги не изменена!";
    }

    public String updateAuthorName(Author authorInfo, String newName) {
        Author author = this.findAuthor(authorInfo);
        if (author == null)
            return DONT_HAVE_AUTHOR;
        author.setName(newName);
        if(authorsDao.update(author) != null)
            return String.format("Новое имя автора книги: %s", author.getName());
        return "Имя автора книги не изменено!";
    }

    public String updateAuthorSurname(Author authorInfo, String newSurname) {
        Author author = this.findAuthor(authorInfo);
        if (author == null)
            return DONT_HAVE_AUTHOR;
        author.setSurname(newSurname);
        if(authorsDao.update(author) != null)
            return String.format("Новое отчество автора книги: %s", author.getSurname());
        return "Отчество автора книги не изменено!";
    }

    public String deleteAuthorByName(Author authorInfo) {
        Author author = this.findAuthor(authorInfo);
        if (author == null)
            return DONT_HAVE_AUTHOR;
        authorsDao.deleteById(author.getId());
        if (this.findAuthor(authorInfo) == null)
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
}
