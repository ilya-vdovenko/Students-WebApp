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

import static org.spring.samples.swa.util.EntityUtils.fillInStudent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import org.spring.samples.swa.model.Cathedra;
import org.spring.samples.swa.model.GroupClass;
import org.spring.samples.swa.model.Student;
import org.spring.samples.swa.repository.InstituteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

/**
 * Implementation mapping data from a {@link ResultSetExtractor} to the corresponding properties of
 * the entity classes.
 *
 * @author Ilya Vdovenko
 */

@Component
public class GroupClassExtractor implements ResultSetExtractor<GroupClass> {

  private final InstituteRepository instituteRepo;

  @Autowired
  public GroupClassExtractor(
      @Qualifier("jdbcInstituteRepositoryImpl") InstituteRepository instituteRepo) {
    this.instituteRepo = instituteRepo;
  }

  @Override
  public GroupClass extractData(ResultSet rs) throws SQLException {
    rs.next();
    GroupClass groupClass = new GroupClass();
    groupClass.setId(rs.getInt("groupID"));
    groupClass.setTitle(rs.getString("groupTitle"));
    groupClass.setEduForm(rs.getString("edu_form"));
    Cathedra cathedra = instituteRepo.findCathedraById(rs.getInt("grpCat"));
    groupClass.setCathedra(cathedra);
    Set<Student> studentSet = new HashSet<>();
    do {
      Student student = fillInStudent(rs, groupClass, cathedra);
      student.setFaculty(cathedra.getFaculty());
      studentSet.add(student);
    } while (rs.next());
    groupClass.setGroupStudents(studentSet);
    return groupClass;
  }
}
