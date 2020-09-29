package org.spring.samples.repository.JDBC;

import org.spring.samples.model.Student;
import org.spring.samples.repository.InstituteRepository;
import org.spring.samples.repository.JDBC.RowMapper.StudentRowMapper;
import org.spring.samples.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class JDBCStudentRepositoryImpl implements StudentRepository {

  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  private final JdbcTemplate jdbcTemplate;
  private final SimpleJdbcInsert insertStudent;
  private final InstituteRepository instituteRepository;

  @Autowired
  public JDBCStudentRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate,
                                   NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                                   @Qualifier("JDBCInstituteRepositoryImpl") InstituteRepository instituteRepository) {
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    this.jdbcTemplate = jdbcTemplate;
    this.insertStudent = new SimpleJdbcInsert(dataSource)
      .withTableName("students")
      .usingGeneratedKeyColumns("id");
    this.instituteRepository = instituteRepository;
  }

  @Override
  public void save(Student student) {
    if (student.isNew()) {
      Number newKey = this.insertStudent.executeAndReturnKey(createPetParameterSource(student));
      student.setId(newKey.intValue());
    } else {
      this.namedParameterJdbcTemplate.update(
        "UPDATE students SET fio=:fio, birthday=:birthday, sex=:sex, fact_address=:fact_address" +
          "address=:address, telephone=:telephone, group_class_id=:group_class_id," +
          "cathedra_id=:cathedra_id, faculty_id=:faculty_id WHERE id=:id", createPetParameterSource(student));
    }
  }

  @Override
  public Collection<Student> getAllStudents() {
    return jdbcTemplate.query("SELECT * FROM students ORDER BY fio", new StudentRowMapper(instituteRepository));
  }

  @Override
  public Student findById(int id) {
    Student student;
    try {
      Map<String, Object> params = new HashMap<>();
      params.put("id", id);
      student = this.namedParameterJdbcTemplate.queryForObject(
        "SELECT * FROM students WHERE id= :id",
        params,
        new StudentRowMapper(instituteRepository)
      );
    } catch (EmptyResultDataAccessException ex) {
      throw new ObjectRetrievalFailureException(Student.class, id);
    }
    return student;
  }

  private MapSqlParameterSource createPetParameterSource(Student student) {
    return new MapSqlParameterSource()
      .addValue("id", student.getId())
      .addValue("fio", student.getFio())
      .addValue("birthday", student.getBirthday())
      .addValue("sex", student.getSex())
      .addValue("fact_address", student.getFact_address())
      .addValue("address", student.getAddress())
      .addValue("telephone", student.getTelephone())
      .addValue("group_class_id", student.getGroup_class().getId())
      .addValue("cathedra_id", student.getCathedra().getId())
      .addValue("faculty_id", student.getFaculty().getId());
  }

}
