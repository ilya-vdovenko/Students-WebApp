package org.spring.samples.repository.JDBC.RowMapper;

import org.spring.samples.repository.JDBC.JDBCmodel.JDBCEmployee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<JDBCEmployee> {

  public JDBCEmployee mapRow(ResultSet rs, int num) throws SQLException {
    JDBCEmployee employeeObj = new JDBCEmployee();
    employeeObj.setId(rs.getInt("id"));
    employeeObj.setFio(rs.getString("fio"));
    employeeObj.setPosition(rs.getString("position"));
    employeeObj.setPosition(rs.getString("degree"));
    employeeObj.setCathedra_id(rs.getInt("cathedra_id"));
    employeeObj.setFaculty_id(rs.getInt("faculty_id"));
    return employeeObj;
  }
}
