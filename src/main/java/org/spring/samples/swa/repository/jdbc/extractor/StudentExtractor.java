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

package org.spring.samples.swa.repository.jdbc.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.spring.samples.swa.model.Student;
import org.spring.samples.swa.service.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

/**
 * Implementation mapping data from a {@link ResultSetExtractor} to the corresponding properties of
 * the entity classes.
 *
 * @author Ilya Vdovenko
 */

@Component
public class StudentExtractor implements ResultSetExtractor<List<Student>> {

  private final InstituteService service;

  @Autowired
  public StudentExtractor(@Lazy InstituteService service) {
    this.service = service;
  }

  @Override
  public List<Student> extractData(ResultSet rs) throws SQLException {
    List<Student> studentList = new ArrayList<>();
    while (rs.next()) {
      Student student = new Student();
      student.setId(rs.getInt("id"));
      student.setFullName(rs.getString("fullName"));
      student.setBirthday(rs.getObject("birthday", LocalDate.class));
      student.setSex(rs.getString("sex"));
      student.setActualAddress(rs.getString("actualAddress"));
      student.setAddress(rs.getString("address"));
      student.setTelephone(rs.getString("telephone"));
      student.setGroupClass(service.findGroupClassById(rs.getInt("group_class_id")));
      student.setCathedra(service.findCathedraById(rs.getInt("cathedra_id")));
      student.setFaculty(service.findFacultyById(rs.getInt("faculty_id")));
      studentList.add(student);
    }
    return studentList;
  }
}
