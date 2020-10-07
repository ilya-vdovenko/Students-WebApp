package org.spring.samples.repository.JDBC.Extractor;

import org.spring.samples.model.Student;
import org.spring.samples.repository.InstituteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class StudentExtractor implements ResultSetExtractor<List<Student>> {

  private final InstituteRepository instituteRepo;

  @Autowired
  public StudentExtractor(@Qualifier("JDBCInstituteRepositoryImpl") InstituteRepository instituteRepo) {
    this.instituteRepo = instituteRepo;
  }

  @Override
  public List<Student> extractData(ResultSet rs) throws SQLException, DataAccessException {
    List<Student> studentList = new ArrayList<>();
    while (rs.next()) {
      Student student = new Student();
      student.setId(rs.getInt("id"));
      student.setFio(rs.getString("fio"));
      student.setBirthday(rs.getObject("birthday", LocalDate.class));
      student.setSex(rs.getString("sex"));
      student.setFact_address(rs.getString("fact_address"));
      student.setAddress(rs.getString("address"));
      student.setTelephone(rs.getString("telephone"));
      student.setGroup_class(instituteRepo.findGroup_classById(rs.getInt("group_class_id")));
      student.setCathedra(instituteRepo.findCathedraById(rs.getInt("cathedra_id")));
      student.setFaculty(instituteRepo.findFacultyById(rs.getInt("faculty_id")));
      studentList.add(student);
    }
    return studentList;
  }
}
