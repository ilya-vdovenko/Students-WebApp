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

package org.spring.samples.swa.repository.JDBC.Extractor;

import org.spring.samples.swa.model.Cathedra;
import org.spring.samples.swa.model.Group_class;
import org.spring.samples.swa.model.Student;
import org.spring.samples.swa.repository.InstituteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementation mapping data from a {@link ResultSetExtractor} to the
 * corresponding properties of the entity classes.
 *
 * @author Ilya Vdovenko
 */

@Component
public class Group_classExtractor implements ResultSetExtractor<Group_class> {

  private final InstituteRepository instituteRepo;

  @Autowired
  public Group_classExtractor(@Qualifier("JDBCInstituteRepositoryImpl") InstituteRepository instituteRepo) {
    this.instituteRepo = instituteRepo;
  }

  @Override
  public Group_class extractData(ResultSet rs) throws SQLException, DataAccessException {
    rs.next();
    Group_class group_class = new Group_class();
    group_class.setId(rs.getInt("groupID"));
    group_class.setTitle(rs.getString("groupTitle"));
    group_class.setEduForm(rs.getString("edu_form"));
    Cathedra cathedra = instituteRepo.findCathedraById(rs.getInt("grpCat"));
    group_class.setCathedra(cathedra);
    Set<Student> studentSet = new HashSet<>();
    do {
      Student student = new Student();
      student.setId(rs.getInt("studentID"));
      student.setFio(rs.getString("fio"));
      student.setBirthday(rs.getObject("birthday", LocalDate.class));
      student.setSex(rs.getString("sex"));
      student.setFact_address(rs.getString("fact_address"));
      student.setAddress(rs.getString("address"));
      student.setTelephone(rs.getString("telephone"));
      student.setGroup_class(group_class);
      student.setCathedra(cathedra);
      student.setFaculty(cathedra.getFaculty());
      studentSet.add(student);
    } while (rs.next());
    group_class.setGroup_students(studentSet);
    return group_class;
  }
}
