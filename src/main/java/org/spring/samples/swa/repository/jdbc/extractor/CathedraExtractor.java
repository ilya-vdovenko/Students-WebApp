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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.spring.samples.swa.model.Cathedra;
import org.spring.samples.swa.model.Employee;
import org.spring.samples.swa.model.Faculty;
import org.spring.samples.swa.model.GroupClass;
import org.spring.samples.swa.model.Student;
import org.spring.samples.swa.service.InstituteService;
import org.spring.samples.swa.util.EntityUtils;
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
public class CathedraExtractor implements ResultSetExtractor<Cathedra> {

  private final InstituteService service;

  @Autowired
  public CathedraExtractor(@Lazy InstituteService service) {
    this.service = service;
  }

  @Override
  public Cathedra extractData(ResultSet rs) throws SQLException {
    rs.next();
    Map<Integer, List<Student>> groupData = new LinkedHashMap<>();
    Set<GroupClass> groupClassSet = new HashSet<>();
    GroupClass groupClass = null;
    Cathedra cathedra = new Cathedra();
    cathedra.setId(rs.getInt("cathedraID"));
    cathedra.setTitle(rs.getString("cathedraTitle"));
    Employee cathedraBoss = new Employee();
    cathedraBoss.setId(rs.getInt("cathedraBossID"));
    cathedraBoss.setFullName(rs.getString("cathedraBossName"));
    cathedraBoss.setPosition(rs.getString("cathedraBossPosition"));
    cathedraBoss.setDegree(rs.getString("cathedraBossDegree"));
    cathedraBoss.setCathedra(cathedra);
    cathedra.setBoss(cathedraBoss);
    cathedra.setInformation(rs.getString("cathedraInfo"));
    cathedra.setContactInf(rs.getString("cathedraContInf"));
    cathedra.setEduPrograms(rs.getString("edu_programs"));
    Faculty faculty = service.findFacultyById(rs.getInt("catFac"));
    cathedra.setFaculty(faculty);
    cathedraBoss.setFaculty(faculty);
    do {
      int groupId = rs.getInt("groupID");
      if (!groupData.containsKey(groupId)) {
        groupData.put(groupId, new ArrayList<>());
        groupClass = new GroupClass();
        groupClass.setId(groupId);
        groupClass.setTitle(rs.getString("groupTitle"));
        groupClass.setEduForm(rs.getString("edu_form"));
        groupClass.setCathedra(cathedra);
        groupClassSet.add(groupClass);
      }
      Student student = fillInStudent(rs, groupClass, cathedra);
      student.setFaculty(faculty);
      groupData.get(groupId).add(student);
    } while (rs.next());
    if (EntityUtils.isValidCollection(groupClassSet)) {
      for (GroupClass grp : groupClassSet) {
        grp.setGroupStudents(new HashSet<>(groupData.get(grp.getId())));
      }
    }
    cathedra.setGroupClasses(groupClassSet);
    return cathedra;
  }
}
