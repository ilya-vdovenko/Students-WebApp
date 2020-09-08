package org.spring.samples.repository.JDBC;

import org.spring.samples.model.Cathedra;
import org.spring.samples.model.Faculty;
import org.spring.samples.model.Group_class;
import org.spring.samples.model.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class StudentRowMapper implements RowMapper<Student> {

  @Override
  public Student mapRow(ResultSet rs, int num) throws SQLException {
    Student studObj = new Student();
    studObj.setId(rs.getInt("id"));
    studObj.setFio(rs.getString("fio"));
    studObj.setBirthday(rs.getObject("birthday", LocalDate.class));
    studObj.setSex(rs.getString("sex"));
    studObj.setFact_address(rs.getString("fact_address"));
    studObj.setAddress(rs.getString("address"));
    studObj.setTelephone(rs.getString("telephone"));
//    studObj.setGroup_class(rs.getInt("group_class_id"));
//    studObj.setCathedra(rs.getInt("cathedra_id"));
//    studObj.setFaculty(rs.getInt("faculty_id"));
    studObj.setGroup_class(new Group_class());
    studObj.setCathedra(new Cathedra());
    studObj.setFaculty(new Faculty());
    return studObj;
  }
}
