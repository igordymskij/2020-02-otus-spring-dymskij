package com.education.spring.service;

import com.education.spring.repository.AuthorRepository;
import com.education.spring.domain.Author;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService, AppService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public String insertAuthorInfo(Author author) {
        if (checkDublicateAuthor(author) != null)
            return "Автор уже есть в базе!";
        authorRepository.save(author);
        return "Автор успешно сохранен!";
    }

    public Author checkDublicateAuthor(Author author) {
        List<Author> authors = authorRepository.findAll();
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
            return authorRepository.findByLastName(authorName);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public Author findAuthor(Author author) {
        try {
            List<Author> authorsList = authorRepository.findByLastName(author.getLastName());
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
        if(authorRepository.update(author) != null)
            return String.format("Новая фамилия автора книги: %s", author.getLastName());
        return "Фамилия автора книги не изменена!";
    }

    public String updateAuthorName(Author authorInfo, String newName) {
        Author author = this.findAuthor(authorInfo);
        if (author == null)
            return DONT_HAVE_AUTHOR;
        author.setName(newName);
        if(authorRepository.update(author) != null)
            return String.format("Новое имя автора книги: %s", author.getName());
        return "Имя автора книги не изменено!";
    }

    public String updateAuthorSurname(Author authorInfo, String newSurname) {
        Author author = this.findAuthor(authorInfo);
        if (author == null)
            return DONT_HAVE_AUTHOR;
        author.setSurname(newSurname);
        if(authorRepository.update(author) != null)
            return String.format("Новое отчество автора книги: %s", author.getSurname());
        return "Отчество автора книги не изменено!";
    }

    public String deleteAuthorByName(Author authorInfo) {
        Author author = this.findAuthor(authorInfo);
        if (author == null)
            return DONT_HAVE_AUTHOR;
        authorRepository.deleteById(author.getId());
        if (this.findAuthor(authorInfo) == null)
            return "Автор успешно удалён!";
        return "Автор не удалён!";
    }

    public List<Author> getAllAuthors() {
        try {
            return authorRepository.findAll();
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
}
