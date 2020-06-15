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
                "select * from books, authors, jenres where books.authorId=authors.authorId and books.jenreId=jenres.jenreId and books.id = :id", params, new BookMapper()
        );
    }

    @Override
    public Book getByName(String name) {
        Map<String, Object> params = Collections.singletonMap("name", name);
        return namedParameterJdbcOperations.queryForObject(
                "select * from books, authors, jenres where books.authorId=authors.authorId and books.jenreId=jenres.jenreId and books.name = :name", params, new BookMapper()
        );
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query(
                "select * from books, authors, jenres where books.authorId=authors.authorId and books.jenreId=jenres.jenreId", new BookMapper()
        );
    }

    public int insert(Book book) {
        return jdbc.update(
                "insert into books(id, name, authorId, jenreId, years) values(?, ?, ?, ?, ?)",
                    book.getId(), book.getName(), book.getAuthor().getId(), book.getJenre().getId(), book.getYear()
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
            return new Book(resultSet.getInt("id"),
                            resultSet.getString("name"),
                            new Author(resultSet.getInt("authorId"),
                                       resultSet.getString("authorName"),
                                       resultSet.getString("lastname"),
                                       resultSet.getString("surname")),
                            new Jenre(resultSet.getInt("jenreId"),
                                      resultSet.getString("jenre")),
                            resultSet.getString("year")
            );
        }
    }
}
