package com.education.spring.dao;

import com.education.spring.domain.Jenre;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class JenresDaoJdbc implements JenresDao{

    private final JdbcOperations jdbc;
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public JenresDaoJdbc(JdbcOperations jdbc, NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.jdbc = jdbc;
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public Jenre getById(int jenreId) {
        Map<String, Object> params = Collections.singletonMap("jenreId", jenreId);
        return namedParameterJdbcOperations.queryForObject(
                "select * from jenres where jenres.jenreId = :jenreId", params, new JenreMapper()
        );
    }

    @Override
    public Jenre getByName(String jenre) {
        Map<String, Object> params = Collections.singletonMap("jenre", jenre);
        return namedParameterJdbcOperations.queryForObject(
                "select * from Jenres where jenres.jenre = :jenre", params, new JenreMapper()
        );
    }

    @Override
    public List<Jenre> getAll() {
        return jdbc.query("select * from jenres", new JenreMapper());
    }

    @Override
    public int insert(Jenre jenre) {
        return jdbc.update(
                "insert into Jenres(jenreId, jenre) values(?, ?)",
                jenre.getId(), jenre.getJenre()
        );
    }

    @Override
    public int deleteById(int jenreId) {
        Map<String, Object> params = Collections.singletonMap("jenreId", jenreId);
        return namedParameterJdbcOperations.update("delete from jenres where jenreId = :jenreId", params);
    }

    @Override
    public int deleteByName(String jenre) {
        Map<String, Object> params = Collections.singletonMap("jenreName", jenre);
        return namedParameterJdbcOperations.update("delete from jenres where jenre = :jenre", params);
    }

    private static class JenreMapper implements RowMapper<Jenre> {

        @Override
        public Jenre mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Jenre(resultSet.getInt("jenreId"),
                    resultSet.getString("jenre")
            );
        }
    }
}
