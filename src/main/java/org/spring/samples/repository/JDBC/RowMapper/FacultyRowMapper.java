package org.spring.samples.repository.JDBC.RowMapper;

import org.spring.samples.repository.JDBC.JDBCmodel.JDBCFaculty;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FacultyRowMapper implements RowMapper<JDBCFaculty> {

  public JDBCFaculty mapRow(ResultSet rs, int num) throws SQLException {
    JDBCFaculty facObj = new JDBCFaculty();
    facObj.setId(rs.getInt("id"));
    facObj.setTitle(rs.getString("title"));
    facObj.setBoss_id(rs.getInt("boss"));
    facObj.setInformation(rs.getString("information"));
    facObj.setContact_inf(rs.getString("contact_inf"));
    return facObj;
  }
}
