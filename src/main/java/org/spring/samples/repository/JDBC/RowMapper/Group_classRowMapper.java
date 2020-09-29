package org.spring.samples.repository.JDBC.RowMapper;

import org.spring.samples.model.Group_class;
import org.spring.samples.repository.InstituteRepository;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Group_classRowMapper implements RowMapper<Group_class> {

  private final InstituteRepository repository;

  public Group_classRowMapper(InstituteRepository repository) {
    this.repository = repository;
  }

  public Group_class mapRow(ResultSet rs, int num) throws SQLException {
    Group_class groupObj = new Group_class();
    groupObj.setId(rs.getInt("id"));
    groupObj.setTitle(rs.getString("title"));
    groupObj.setEduForm(rs.getString("edu_form"));
    groupObj.setCathedra(repository.findCathedraById(rs.getInt("cathedra_id")));
    return null;
  }
}
