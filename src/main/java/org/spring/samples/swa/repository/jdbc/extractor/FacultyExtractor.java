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
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.spring.samples.swa.model.Cathedra;
import org.spring.samples.swa.model.Employee;
import org.spring.samples.swa.model.Faculty;
import org.spring.samples.swa.model.GroupClass;
import org.spring.samples.swa.model.Student;
import org.spring.samples.swa.util.EntityUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

/**
 * Implementation mapping data from a {@link ResultSetExtractor} to the corresponding properties of
 * the entity classes.
 *
 * @author Ilya Vdovenko
 */

@Component
public class FacultyExtractor implements ResultSetExtractor<List<Faculty>> {

  @Override
  public List<Faculty> extractData(ResultSet rs) throws SQLException, DataAccessException {
    List<Faculty> facultyList = new ArrayList<>();
    Map<Integer, List<Cathedra>> facultyData = new LinkedHashMap<>();
    Map<Integer, List<GroupClass>> cathedraData = new LinkedHashMap<>();
    Map<Integer, List<Student>> groupData = new LinkedHashMap<>();
    Faculty faculty = null;
    Cathedra cathedra = null;
    GroupClass groupClass = null;
    while (rs.next()) {
      int facultyId = rs.getInt("facultyID");
      if (!facultyData.containsKey(facultyId)) {
        facultyData.put(facultyId, new ArrayList<>());
        faculty = new Faculty();
        faculty.setId(facultyId);
        faculty.setTitle(rs.getString("facultyTitle"));
        Employee facultyBoss = new Employee();
        facultyBoss.setId(rs.getInt("facultyBossID"));
        facultyBoss.setFio(rs.getString("facultyBossFio"));
        facultyBoss.setPosition(rs.getString("facultyBossPosition"));
        facultyBoss.setDegree(rs.getString("facultyBossDegree"));
        facultyBoss.setFaculty(faculty);
        faculty.setBoss(facultyBoss);
        faculty.setInformation(rs.getString("facultyInfo"));
        faculty.setContactInf(rs.getString("facultyContInf"));
        facultyList.add(faculty);
      }
      int cathedraId = rs.getInt("cathedraID");
      if (!cathedraData.containsKey(cathedraId)) {
        cathedraData.put(cathedraId, new ArrayList<>());
        cathedra = new Cathedra();
        cathedra.setId(cathedraId);
        cathedra.setTitle(rs.getString("cathedraTitle"));
        Employee cathedraBoss = new Employee();
        cathedraBoss.setId(rs.getInt("cathedraBossID"));
        cathedraBoss.setFio(rs.getString("cathedraBossFio"));
        cathedraBoss.setPosition(rs.getString("cathedraBossPosition"));
        cathedraBoss.setDegree(rs.getString("cathedraBossDegree"));
        cathedraBoss.setFaculty(faculty);
        cathedraBoss.setCathedra(cathedra);
        cathedra.setBoss(cathedraBoss);
        cathedra.setInformation(rs.getString("cathedraInfo"));
        cathedra.setContactInf(rs.getString("cathedraContInf"));
        cathedra.setEduPrograms(rs.getString("edu_programs"));
        cathedra.setFaculty(faculty);
        facultyData.get(facultyId).add(cathedra);
      }
      int groupId = rs.getInt("groupID");
      if (!groupData.containsKey(groupId)) {
        groupData.put(groupId, new ArrayList<>());
        groupClass = new GroupClass();
        groupClass.setId(groupId);
        groupClass.setTitle(rs.getString("groupTitle"));
        groupClass.setEduForm(rs.getString("edu_form"));
        groupClass.setCathedra(cathedra);
        cathedraData.get(cathedraId).add(groupClass);
      }
      Student student = new Student();
      student.setId(rs.getInt("studentID"));
      student.setFio(rs.getString("fio"));
      student.setBirthday(rs.getObject("birthday", LocalDate.class));
      student.setSex(rs.getString("sex"));
      student.setFactAddress(rs.getString("fact_address"));
      student.setAddress(rs.getString("address"));
      student.setTelephone(rs.getString("telephone"));
      student.setGroupClass(groupClass);
      student.setCathedra(cathedra);
      student.setFaculty(faculty);
      groupData.get(groupId).add(student);
    }

    if (EntityUtils.isValidCollection(facultyList)) {
      for (Faculty fac : facultyList) {
        List<Cathedra> cathedraList = facultyData.get(fac.getId());
        if (EntityUtils.isValidCollection(cathedraList)) {
          for (Cathedra cat : cathedraList) {
            List<GroupClass> groupClassList = cathedraData.get(cat.getId());
            if (EntityUtils.isValidCollection(groupClassList)) {
              for (GroupClass grp : groupClassList) {
                grp.setGroupStudents(new HashSet<>(groupData.get(grp.getId())));
              }
              cat.setGroupClasses(new HashSet<>(groupClassList));
            }
          }
          fac.setCathedras(new HashSet<>(cathedraList));
        }
      }
    }
    return facultyList;
  }
}
