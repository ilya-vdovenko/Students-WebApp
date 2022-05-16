/*
 * Copyright 2019-2021, Ilya Vdovenko and the Students-WebApp contributors.
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
import org.spring.samples.swa.model.BaseEntity;
import org.spring.samples.swa.model.Cathedra;
import org.spring.samples.swa.model.Employee;
import org.spring.samples.swa.model.Faculty;
import org.spring.samples.swa.model.GroupClass;
import org.spring.samples.swa.model.Student;
import org.spring.samples.swa.model.UnitEntity;
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

  private Faculty faculty = null;
  private Cathedra cathedra = null;
  private List<Faculty> facultyList = null;
  private Map<Integer, List<Cathedra>> facultyData = null;
  private int facultyId;

  @Override
  public List<Faculty> extractData(ResultSet rs) throws SQLException {
    facultyList = new ArrayList<>();
    facultyData = new LinkedHashMap<>();
    Map<Integer, List<GroupClass>> cathedraData = new LinkedHashMap<>();
    Map<Integer, List<Student>> groupData = new LinkedHashMap<>();
    GroupClass groupClass = null;
    while (rs.next()) {
      facultyId = rs.getInt("facultyID");
      dataSet(facultyData, facultyId, rs, new Faculty());
      int cathedraId = rs.getInt("cathedraID");
      dataSet(cathedraData, cathedraId, rs, new Cathedra());
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
      Student student = fillInStudent(rs, groupClass, cathedra);
      student.setFaculty(faculty);
      groupData.get(groupId).add(student);
    }

    facultyList.parallelStream().forEach(fac -> {
      List<Cathedra> cathedraList = facultyData.get(fac.getId());
      cathedraList.parallelStream().forEach(cat -> {
        List<GroupClass> groupClassList = cathedraData.get(cat.getId());
        groupClassList.parallelStream().forEach(grp -> grp.setGroupStudents(
            new HashSet<>(groupData.get(grp.getId()))));
        cat.setGroupClasses(new HashSet<>(groupClassList));
      });
      fac.setCathedras(new HashSet<>(cathedraList));
    });
    return facultyList;
  }

  private <T extends UnitEntity, K extends BaseEntity> void dataSet(Map<Integer, List<K>> data, 
                                                                    int id, 
                                                                    ResultSet rs, 
                                                                    T entity) throws SQLException {
    if (!data.containsKey(id)) {
      String type = entity.getClass().getSimpleName().toLowerCase();
      data.put(id, new ArrayList<>());
      entity.setId(id);
      entity.setTitle(rs.getString(type + "Title"));
      entity.setInformation(rs.getString(type + "Info"));
      entity.setContactInf(rs.getString(type + "ContInf"));
      Employee boss = new Employee();
      boss.setId(rs.getInt(type + "BossID"));
      boss.setFullName(rs.getString(type + "BossName"));
      boss.setPosition(rs.getString(type + "BossPosition"));
      boss.setDegree(rs.getString(type + "BossDegree"));
      if (entity instanceof Faculty) {
        faculty = (Faculty) entity;
        boss.setFaculty(faculty);
        facultyList.add(faculty);
      } else {
        cathedra = (Cathedra) entity;
        boss.setCathedra(cathedra);
        cathedra.setEduPrograms(rs.getString("edu_programs"));
        cathedra.setFaculty(faculty);
        facultyData.get(facultyId).add(cathedra);
      }
      entity.setBoss(boss);
    }
  }
}
