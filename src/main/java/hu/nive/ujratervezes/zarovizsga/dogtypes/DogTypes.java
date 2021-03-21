package hu.nive.ujratervezes.zarovizsga.dogtypes;

import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DogTypes {

  private JdbcTemplate jdbcTemplate;

  public DogTypes(MariaDbDataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  public List<String> getDogsByCountry(String country) {
    return jdbcTemplate.query("SELECT LOWER(name) AS name FROM dog_types WHERE LOWER(country)=? ORDER BY name", new Object[] {country},
            (rs, i) -> rs.getString("name"));
  }
}
