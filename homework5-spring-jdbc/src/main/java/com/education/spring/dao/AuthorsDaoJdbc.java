package com.education.spring.dao;

import com.education.spring.domain.Author;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorsDaoJdbc implements AuthorsDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public AuthorsDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public Author getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select * from authors where authors.authorId = :id", params, new AuthorMapper()
        );
    }

    @Override
    public Author getByName(String lastName) {
        Map<String, Object> params = Collections.singletonMap("lastName", lastName);
        return namedParameterJdbcOperations.queryForObject(
                "select * from authors where lastname = :lastName", params, new AuthorMapper()
        );
    }

    @Override
    public List<Author> getAll() {
        return namedParameterJdbcOperations.query("select * from authors", new AuthorMapper());
    }

    @Override
    public int insert(Author author) {
        Map<String, String> authorValue = Map.of("authorName", author.getName(),
                                                 "authorLastName", author.getLastName(),
                                                 "authorSurname", author.getSurname());
        return namedParameterJdbcOperations.update(
                "insert into authors (authorname, lastname, surname) values (:authorName, :authorLastName, :authorSurname)",
                authorValue);
    }

    @Override
    public int nameUpdate(String lastName, String newName) {
        Map<String, Object> params = Map.of("newName", newName,
                                            "lastName", lastName);
        return namedParameterJdbcOperations.update(
                "update authors set authorname = :newName where lastname = :lastName", params
        );
    }

    @Override
    public int lastNameUpdate(String lastName, String newLastName) {
        Map<String, Object> params = Map.of("newLastName", newLastName,
                                            "lastName", lastName);
        return namedParameterJdbcOperations.update(
                "update authors set lastname = :newLastName where lastname = :lastName", params
        );
    }

    @Override
    public int surnameUpdate(String lastName, String newSurName) {
        Map<String, Object> params = Map.of("newSurName", newSurName,
                                            "lastName", lastName);
        return namedParameterJdbcOperations.update(
                "update authors set surname = :newSurName where lastname = :lastName", params
        );
    }

    @Override
    public int deleteById(int authorId) {
        Map<String, Object> params = Collections.singletonMap("authorId", authorId);
        return namedParameterJdbcOperations.update("delete from authors where authorId = :authorId", params);
    }

    @Override
    public int deleteByName(String authorLastName) {
        Map<String, Object> params = Collections.singletonMap("authorLastName", authorLastName);
        return namedParameterJdbcOperations.update("delete from authors where lastname = :authorLastName", params);
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Author(resultSet.getString("authorId"),
                    resultSet.getString("authorName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("surname")
            );
        }
    }
}
