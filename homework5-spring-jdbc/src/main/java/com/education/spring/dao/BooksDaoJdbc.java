package com.education.spring.dao;

import com.education.spring.domain.Author;
import com.education.spring.domain.Book;
import com.education.spring.domain.Jenre;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class BooksDaoJdbc implements BooksDao {

    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BooksDaoJdbc(JdbcOperations jdbc, NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbc = jdbc;
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select b.id, b.name, a.authorid, a.authorname, a.lastname, a.surname, j.jenreid, j.jenre, b.year from books, authors, jenres where b.authorId=a.authorId and b.jenreId=j.jenreId and b.id = :id",
                params, new BookMapper()
        );
    }

    @Override
    public Book getByName(String name) {
        Map<String, Object> params = Collections.singletonMap("name", name);
            return namedParameterJdbcOperations.queryForObject(
                    "select b.id, b.name, a.authorid, a.authorname, a.lastname, a.surname, j.jenreid, j.jenre, b.year from books b, authors a, jenres j where b.authorId=a.authorId and b.jenreId=j.jenreId and b.name = :name",
                    params, new BookMapper()
            );
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query(
                "select b.id, b.name, a.authorid, a.authorname, a.lastname, a.surname, j.jenreid, j.jenre, b.year from books b, authors a, jenres j where b.authorId=a.authorId and b.jenreId=j.jenreId",
                new BookMapper()
        );
    }

    public int insert(Book book) {
        return jdbc.update(
                "insert into books (id, name, authorId, jenreId, year) values(?, ?, ?, ?, ?)",
                    book.getId(), book.getName(), book.getAuthor().getId(), book.getJenre().getId(), book.getYear()
        );
    }

    @Override
    public int nameUpdate(String name, String newName) {
        Map<String, Object> params = Map.of("newName", newName,
                                            "name", name);
        return namedParameterJdbcOperations.update(
                "update books set name = :newName where name = :name", params
        );
    }

    @Override
    public int authorUpdate(String name, String newAuthorId) {
        Map<String, Object> params = Map.of("newAuthorId", newAuthorId,
                                            "name", name);

        return namedParameterJdbcOperations.update(
                "update books set authorId = :newAuthorId where name = :name", params
        );
    }

    @Override
    public int jenreUpdate(String name, String newJenreId) {
        Map<String, Object> params = Map.of("newJenreId", newJenreId,
                                            "name", name);

        return namedParameterJdbcOperations.update(
                "update books set jenreId = :newJenreId where name = :name", params
        );
    }

    @Override
    public int yearUpdate(String name, String newYear) {
        Map<String, Object> params = Map.of("newYear", newYear,
                                            "name", name);
        return namedParameterJdbcOperations.update(
                "update books set year = :newYear where name = :name", params
        );
    }

    public int deleteById(int id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.update("delete from books where id = :id", params);
    }

    public int deleteByName(String name) {
        Map<String, Object> params = Collections.singletonMap("name", name);
        return namedParameterJdbcOperations.update("delete from books where name = :name", params);
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Book(resultSet.getString("id"),
                            resultSet.getString("name"),
                            new Author(resultSet.getString("authorId"),
                                       resultSet.getString("authorName"),
                                       resultSet.getString("lastname"),
                                       resultSet.getString("surname")),
                            new Jenre(resultSet.getString("jenreId"),
                                      resultSet.getString("jenre")),
                            resultSet.getString("year")
            );
        }
    }
}
