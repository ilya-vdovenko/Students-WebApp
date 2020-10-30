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
import org.spring.samples.swa.model.Employee;
import org.spring.samples.swa.model.Faculty;
import org.spring.samples.swa.model.Group_class;
import org.spring.samples.swa.model.Student;
import org.spring.samples.swa.repository.InstituteRepository;
import org.spring.samples.swa.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Implementation mapping data from a {@link ResultSetExtractor} to the
 * corresponding properties of the entity classes.
 *
 * @author Ilya Vdovenko
 */

@Component
public class CathedraExtractor implements ResultSetExtractor<Cathedra> {

  private final InstituteRepository instituteRepo;

  @Autowired
  public CathedraExtractor(@Qualifier("JDBCInstituteRepositoryImpl") InstituteRepository instituteRepo) {
    this.instituteRepo = instituteRepo;
  }

  @Override
  public Cathedra extractData(ResultSet rs) throws SQLException, DataAccessException {
    rs.next();
    Map<Integer, List<Student>> groupData = new LinkedHashMap<>();
    Set<Group_class> group_classSet = new HashSet<>();
    Group_class group_class = null;
    Cathedra cathedra = new Cathedra();
    cathedra.setId(rs.getInt("cathedraID"));
    cathedra.setTitle(rs.getString("cathedraTitle"));
    Employee cathedraBoss = new Employee();
    cathedraBoss.setId(rs.getInt("cathedraBossID"));
    cathedraBoss.setFio(rs.getString("cathedraBossFio"));
    cathedraBoss.setPosition(rs.getString("cathedraBossPosition"));
    cathedraBoss.setDegree(rs.getString("cathedraBossDegree"));
    cathedraBoss.setCathedra(cathedra);
    cathedra.setBoss(cathedraBoss);
    cathedra.setInformation(rs.getString("cathedraInfo"));
    cathedra.setContact_inf(rs.getString("cathedraContInf"));
    cathedra.setEduPrograms(rs.getString("edu_programs"));
    Faculty faculty = instituteRepo.findFacultyById(rs.getInt("catFac"));
    cathedra.setFaculty(faculty);
    cathedraBoss.setFaculty(faculty);
    do {
      int grpID = rs.getInt("groupID");
      if (!groupData.containsKey(grpID)) {
        groupData.put(grpID, new ArrayList<>());
        group_class = new Group_class();
        group_class.setId(grpID);
        group_class.setTitle(rs.getString("groupTitle"));
        group_class.setEduForm(rs.getString("edu_form"));
        group_class.setCathedra(cathedra);
        group_classSet.add(group_class);
      }
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
      student.setFaculty(faculty);
      groupData.get(grpID).add(student);
    } while (rs.next());
    if (EntityUtils.isValidCollection(group_classSet)) {
      for (Group_class grp : group_classSet) {
        grp.setGroup_students(new HashSet<>(groupData.get(grp.getId())));
      }
    }
    cathedra.setGroup_classes(group_classSet);
    return cathedra;
  }
}
