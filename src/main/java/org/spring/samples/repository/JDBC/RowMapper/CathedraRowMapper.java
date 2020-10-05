package org.spring.samples.repository.JDBC.RowMapper;

import org.spring.samples.repository.JDBC.JDBCmodel.JDBCCathedra;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CathedraRowMapper implements RowMapper<JDBCCathedra> {

  public JDBCCathedra mapRow(ResultSet rs, int num) throws SQLException {
    JDBCCathedra catObj = new JDBCCathedra();
    catObj.setId(rs.getInt("id"));
    catObj.setTitle(rs.getString("title"));
    catObj.setBoss_id(rs.getInt("boss"));
    catObj.setInformation(rs.getString("information"));
    catObj.setContact_inf(rs.getString("contact_inf"));
    catObj.setEduPrograms(rs.getString("edu_programs"));
    catObj.setFaculty_id(rs.getInt("faculty_id"));
    return catObj;
  }
}
