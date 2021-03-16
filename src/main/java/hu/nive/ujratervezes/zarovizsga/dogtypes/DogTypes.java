package hu.nive.ujratervezes.zarovizsga.dogtypes;

import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DogTypes {

  private MariaDbDataSource dataSource;

  public DogTypes(MariaDbDataSource dataSource) {
    this.dataSource = dataSource;
  }

  public List<String> getDogsByCountry(String country) {
    try (Connection conn = dataSource.getConnection();
         PreparedStatement ps = conn.prepareStatement("SELECT name FROM dog_types WHERE country=?");
    ) {
      ps.setString(1, country.toUpperCase());
      ps.executeQuery();

      return findDogTypesInCountry(ps);

    } catch (SQLException e) {
      throw new IllegalStateException("Can not connect to datadase", e);
    }
  }

  private List<String> findDogTypesInCountry(PreparedStatement ps) {
    List<String> result = new ArrayList<>();
    try (
            ResultSet rs = ps.executeQuery();
    ) {
      while (rs.next()) {
        result.add(rs.getString("name").toLowerCase());
      }
      return getDogTypes(result);
    } catch (SQLException sqle) {
      throw new IllegalArgumentException("Can not select", sqle);
    }
  }

  private List<String> getDogTypes(List<String> result) {
    if (result.size() == 0) {
      throw new IllegalArgumentException("No result found");
    } else {
      return result;
    }
  }
}
