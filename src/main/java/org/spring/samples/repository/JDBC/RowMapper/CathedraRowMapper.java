package org.spring.samples.repository.JDBC.RowMapper;

import org.spring.samples.model.Cathedra;
import org.spring.samples.repository.EmployeeRepository;
import org.spring.samples.repository.InstituteRepository;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CathedraRowMapper implements RowMapper<Cathedra> {

  private final InstituteRepository instituteRepository;
  private final EmployeeRepository employeeRepository;

  public CathedraRowMapper(InstituteRepository instituteRepository, EmployeeRepository employeeRepository) {
    this.instituteRepository = instituteRepository;
    this.employeeRepository = employeeRepository;
  }

  public Cathedra mapRow(ResultSet rs, int num) throws SQLException {
    Cathedra catObj = new Cathedra();
    catObj.setId(rs.getInt("id"));
    catObj.setTitle(rs.getString("title"));
    catObj.setBoss(employeeRepository.findById(rs.getInt("boss")));
    catObj.setInformation(rs.getString("information"));
    catObj.setContact_inf(rs.getString("contact_inf"));
    catObj.setEduPrograms(rs.getString("edu_programs"));
    catObj.setFaculty(instituteRepository.findFacultyById(rs.getInt("faculty_id")));
    return null;
  }
}
