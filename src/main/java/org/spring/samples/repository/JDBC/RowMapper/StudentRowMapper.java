package org.spring.samples.repository.JDBC.RowMapper;

import org.spring.samples.model.Cathedra;
import org.spring.samples.model.Faculty;
import org.spring.samples.model.Group_class;
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
    Faculty faculty = repository.findFacultyById(rs.getInt("faculty_id"));
    studObj.setFaculty(faculty);
    for (Cathedra cathedra : faculty.getCathedras()) {
      if (cathedra.getId().equals(rs.getInt("cathedra_id"))) {
        studObj.setCathedra(cathedra);
        for (Group_class group_class : cathedra.getGroup_classes()) {
          if (group_class.getId().equals(rs.getInt("group_class_id"))) {
            studObj.setGroup_class(group_class);
            break;
          }
        }
        break;
      }
    }
    return studObj;
  }
}
