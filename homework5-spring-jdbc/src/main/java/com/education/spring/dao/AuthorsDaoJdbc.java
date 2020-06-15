package com.education.spring.dao;

import com.education.spring.domain.Author;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AuthorsDaoJdbc implements AuthorsDao {

    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public AuthorsDaoJdbc(JdbcOperations jdbc, NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbc = jdbc;
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
        Map<String, Object> params = Collections.singletonMap("name", lastName);
        return namedParameterJdbcOperations.queryForObject(
                "select * from authors where authors.lastname = :lastname", params, new AuthorMapper()
        );
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("select * from authors", new AuthorMapper());
    }

    @Override
    public int insert(Author author) {
        return jdbc.update(
                "insert into authors(authorId, authorName, lastName, surname) values(?, ?, ?, ?)",
                author.getId(), author.getName(), author.getLastName(), author.getSurname()
        );
    }

    @Override
    public int deleteById(int authorId) {
        Map<String, Object> params = Collections.singletonMap("authorId", authorId);
        return namedParameterJdbcOperations.update("delete from authors where authorId = :authorId", params);
    }

    @Override
    public int deleteByName(String authorName) {
        Map<String, Object> params = Collections.singletonMap("authorName", authorName);
        return namedParameterJdbcOperations.update("delete from authors where authorName = :authorName", params);
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Author(resultSet.getInt("authorId"),
                    resultSet.getString("authorName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("surname")
            );
        }
    }
}
