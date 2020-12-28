/*
 * Copyright 2019-2020, Ilya Vdovenko and the Students-WebApp contributors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.spring.samples.swa.repository.jdbc;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.spring.samples.swa.model.Student;
import org.spring.samples.swa.repository.StudentRepository;
import org.spring.samples.swa.repository.jdbc.extractor.StudentExtractor;
import org.spring.samples.swa.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

/**
 * A jdbc-based implementation of the {@link StudentRepository} interface.
 *
 * @author Ilya Vdovenko
 */

@Repository
public class JdbcStudentRepositoryImpl implements StudentRepository {

  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  private final JdbcTemplate jdbcTemplate;
  private final SimpleJdbcInsert insertStudent;
  private final StudentExtractor studentExtractor;
  private Student tempStudent;

  /**
   * Constructor of {@link JdbcStudentRepositoryImpl} class.
   *
   * @param dataSource                 injected {@link DataSource} class. Used for
   *                                   SimpleJdbcInsert.
   * @param jdbcTemplate               used for query.
   * @param namedParameterJdbcTemplate used for named query.
   * @param studentExtractor           used for extract data to {@link Student} model.
   */
  @Autowired
  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  public JdbcStudentRepositoryImpl(
      DataSource dataSource,
      JdbcTemplate jdbcTemplate,
      NamedParameterJdbcTemplate namedParameterJdbcTemplate,
      StudentExtractor studentExtractor) {
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    this.jdbcTemplate = jdbcTemplate;
    this.insertStudent = new SimpleJdbcInsert(dataSource).withTableName("students")
        .usingGeneratedKeyColumns("id");
    this.studentExtractor = studentExtractor;
  }

  @Override
  public void save(Student student) {
    if (student.isNew()) {
      Number newKey = this.insertStudent.executeAndReturnKey(createPetParameterSource(student));
      student.setId(newKey.intValue());
    } else {
      this.namedParameterJdbcTemplate.update(
          "UPDATE students SET fullName=:fullName, birthday=:birthday, sex=:sex,"
              + "actualAddress=:actualAddress, address=:address, telephone=:telephone,"
              + "group_class_id=:group_class_id, cathedra_id=:cathedra_id,"
              + "faculty_id=:faculty_id WHERE id=:id",
          createPetParameterSource(student));
    }
  }

  @Override
  public Collection<Student> findAllByOrderByFullNameAsc() {
    return jdbcTemplate.query("SELECT * FROM students ORDER BY fullName", studentExtractor);
  }

  @Override
  public Student findById(int id) {
    if (tempStudent != null && (tempStudent.getId()).equals(id)) {
      return tempStudent;
    }
    Student student;
    try {
      Map<String, Object> params = new HashMap<>();
      params.put("id", id);
      List<Student> studentList = this.namedParameterJdbcTemplate.query(
          "SELECT * FROM students WHERE id= :id",
          params,
          studentExtractor);
      if (EntityUtils.isValidCollection(studentList)) {
        student = studentList.get(0);
      } else {
        throw new EmptyResultDataAccessException(1);
      }
    } catch (EmptyResultDataAccessException ex) {
      throw new ObjectRetrievalFailureException(Student.class, id);
    }
    tempStudent = student;
    return student;
  }

  private MapSqlParameterSource createPetParameterSource(Student student) {
    return new MapSqlParameterSource()
        .addValue("id", student.getId())
        .addValue("fullName", student.getFullName())
        .addValue("birthday", student.getBirthday())
        .addValue("sex", student.getSex())
        .addValue("actualAddress", student.getActualAddress())
        .addValue("address", student.getAddress())
        .addValue("telephone", student.getTelephone())
        .addValue("group_class_id", student.getGroupClass().getId())
        .addValue("cathedra_id", student.getCathedra().getId())
        .addValue("faculty_id", student.getFaculty().getId());
  }
}
