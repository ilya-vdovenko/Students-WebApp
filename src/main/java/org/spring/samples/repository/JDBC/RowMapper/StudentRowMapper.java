package org.spring.samples.repository.JDBC.RowMapper;

import org.spring.samples.model.Student;
import org.spring.samples.repository.InstituteRepository;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class StudentRowMapper implements RowMapper<Student> {

  private final InstituteRepository repository;

  public StudentRowMapper(InstituteRepository instituteRepository) {
    this.repository = instituteRepository;
  }

  public Student mapRow(ResultSet rs, int num) throws SQLException {
    Student studObj = new Student();
    studObj.setId(rs.getInt("id"));
    studObj.setFio(rs.getString("fio"));
    studObj.setBirthday(rs.getObject("birthday", LocalDate.class));
    studObj.setSex(rs.getString("sex"));
    studObj.setFact_address(rs.getString("fact_address"));
    studObj.setAddress(rs.getString("address"));
    studObj.setTelephone(rs.getString("telephone"));
    studObj.setGroup_class(repository.findGroup_classById(rs.getInt("group_class_id")));
    studObj.setCathedra(repository.findCathedraById(rs.getInt("cathedra_id")));
    studObj.setFaculty(repository.findFacultyById(rs.getInt("faculty_id")));
    return studObj;
  }
}
