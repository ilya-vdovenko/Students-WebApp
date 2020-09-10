package org.spring.samples.repository.JDBC;

import org.spring.samples.model.Student;
import org.spring.samples.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collection;

@Repository
public class JDBCStudentRepositoryImpl implements StudentRepository {

  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  private SimpleJdbcInsert insertStudent;

  @Autowired
  public JDBCStudentRepositoryImpl(/*DataSource dataSource*/) {
//    this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
//
//    this.insertStudent = new SimpleJdbcInsert(dataSource)
//      .withTableName("students")
//      .usingGeneratedKeyColumns("id");
  }

  @Override
  public void save(Student student) {

    /*jdbc.update("Insert into students values (?,?,?,?,?,?,?,1,1,1)",
      student.getId(),
      student.getFio(),
      student.getBirthday(),
      student.getSex(),
      student.getFact_address(),
      student.getAddress(),
      student.getTelephone()
      student.getGroup_class().getId(),
      student.getCathedra().getId(),
      student.getFaculty().getId());*/

    BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(student);
    if (student.isNew()) {
      Number newKey = this.insertStudent.executeAndReturnKey(parameterSource);
      student.setId(newKey.intValue());
    } else {
      this.namedParameterJdbcTemplate.update(
        "UPDATE students SET fio=:fio, birthday=:birthday, sex=:sex, fact_address=:fact_address" +
          "address=:address, telephone=:telephone, group_class_id=:group_class_id," +
          "cathedra_id=:cathedra_id, faculty_id=:faculty_id WHERE id=:id", parameterSource);
    }
  }

  @Override
  public Collection<Student> getAllStudents() {
    //return jdbc.query("Select * from students order by fio", new StudentRowMapper());
    return null;
  }

  @Override
  public Student findById(int id) {
    //return jdbc.queryForObject("Select * from students where id = ?", new StudentRowMapper(), id);
    return null;
  }

//  private MapSqlParameterSource createPetParameterSource(Student student) {
//    return new MapSqlParameterSource()
//      .addValue("id", student.getId())
//      .addValue("fio", student.getFio())
//      .addValue("birthday", student.getBirthday())
//      .addValue("sex", student.getSex())
//      .addValue("fact_address", student.getFact_address())
//      .addValue("address", student.getAddress())
//      .addValue("telephone", student.getTelephone())
//      .addValue("group_class_id", student.getGroup_class().getId())
//      .addValue("cathedra_id", student.getCathedra().getId())
//      .addValue("faculty_id", student.getFaculty().getId());
//  }
}
