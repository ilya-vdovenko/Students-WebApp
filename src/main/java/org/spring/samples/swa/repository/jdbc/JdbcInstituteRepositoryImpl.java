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
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.spring.samples.swa.model.Cathedra;
import org.spring.samples.swa.model.Employee;
import org.spring.samples.swa.model.Faculty;
import org.spring.samples.swa.model.GroupClass;
import org.spring.samples.swa.repository.InstituteRepository;
import org.spring.samples.swa.repository.jdbc.extractor.CathedraExtractor;
import org.spring.samples.swa.repository.jdbc.extractor.EmployeeExtractor;
import org.spring.samples.swa.repository.jdbc.extractor.FacultyExtractor;
import org.spring.samples.swa.repository.jdbc.extractor.GroupClassExtractor;
import org.spring.samples.swa.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

/**
 * A jdbc-based implementation of the {@link InstituteRepository} interface.
 *
 * @author Ilya Vdovenko
 */

@Repository
public class JdbcInstituteRepositoryImpl implements InstituteRepository {

  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  private final JdbcTemplate jdbcTemplate;
  private final FacultyExtractor facultyExtractor;
  private final Map<Integer, Faculty> facultiesMap = new LinkedHashMap<>();
  private final Map<Integer, Cathedra> cathedrasMap = new LinkedHashMap<>();
  private final Map<Integer, GroupClass> groupClassesMap = new LinkedHashMap<>();

  @Autowired
  private EmployeeExtractor employeeExtractor;

  @Autowired
  private CathedraExtractor cathedraExtractor;

  @Autowired
  private GroupClassExtractor groupClassExtractor;

  /**
   * Constructor of {@link JdbcInstituteRepositoryImpl} class.
   *
   * @param namedParameterJdbcTemplate used for named query.
   * @param jdbcTemplate               used for query.
   * @param facultyExtractor           used for extract data to {@link Faculty} model.
   */
  @Autowired
  public JdbcInstituteRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
      FacultyExtractor facultyExtractor,
      JdbcTemplate jdbcTemplate) {
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    this.facultyExtractor = facultyExtractor;
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Collection<Faculty> findAllFaculties() {
    List<Faculty> facultyList = this.jdbcTemplate.query(
        "SELECT f.id as facultyID, f.title as facultyTitle, f.information as facultyInfo,\n"
            + "f.boss as facultyBossID, fb.fio as facultyBossFio,\n"
            + "fb.position as facultyBossPosition, fb.degree as facultyBossDegree,\n"
            + "f.contact_inf as facultyContInf, c.id as cathedraID, c.title as cathedraTitle,\n"
            + "c.information as cathedraInfo, c.boss as cathedraBossID,\n"
            + "cb.fio as cathedraBossFio, cb.position as cathedraBossPosition,\n"
            + "cb.degree as cathedraBossDegree, c.contact_inf as cathedraContInf, edu_programs,\n"
            + "g.id as groupID, g.title as groupTitle, edu_form, s.id as studentID, s.fio,\n"
            + "birthday, sex, fact_address, address, telephone from faculties as f\n"
            + "LEFT JOIN cathedras c on f.id = c.faculty_id\n"
            + "LEFT JOIN employees fb on f.boss = fb.id\n"
            + "LEFT JOIN employees cb on c.boss = cb.id\n"
            + "LEFT JOIN group_classes as g on c.id = g.cathedra_id\n"
            + "LEFT JOIN students as s on g.id = s.group_class_id ORDER BY f.title",
        facultyExtractor);

    if (EntityUtils.isValidCollection(facultyList)) {
      EntityUtils.setEntityMaps(facultyList, facultiesMap, cathedrasMap, groupClassesMap);
      for (Faculty faculty : facultyList) {
        faculty.setEmployees(findEmployeesByEntity(faculty.getId(), "faculty_id"));
        for (Cathedra cathedra : faculty.getCathedras()) {
          cathedra.setEmployees(findEmployeesByEntity(cathedra.getId(), "cathedra_id"));
        }
      }
    }
    return facultyList;
  }

  @Override
  public Faculty findFacultyById(int id) {
    if (EntityUtils.isValidCollection(facultiesMap.values())
        && facultiesMap.containsKey(id)) {
      return facultiesMap.get(id);
    }
    Faculty faculty;
    try {
      Map<String, Object> params = new HashMap<>();
      params.put("id", id);
      List<Faculty> facultyList = this.namedParameterJdbcTemplate.query(
          "SELECT f.id as facultyID, f.title as facultyTitle, f.information as facultyInfo,\n"
              + "f.boss as facultyBossID, fb.fio as facultyBossFio,\n"
              + "fb.position as facultyBossPosition, fb.degree as facultyBossDegree,\n"
              + "f.contact_inf as facultyContInf, c.id as cathedraID, c.title as cathedraTitle,\n"
              + "c.information as cathedraInfo, c.boss as cathedraBossID,\n"
              + "cb.fio as cathedraBossFio, cb.position as cathedraBossPosition,\n"
              + "cb.degree as cathedraBossDegree, c.contact_inf as cathedraContInf, edu_programs,\n"
              + "g.id as groupID, g.title as groupTitle, edu_form, s.id as studentID, s.fio,\n"
              + "birthday, sex, fact_address, address, telephone from faculties as f\n"
              + "LEFT JOIN cathedras c on f.id = c.faculty_id\n"
              + "LEFT JOIN employees fb on f.boss = fb.id\n"
              + "LEFT JOIN employees cb on c.boss = cb.id\n"
              + "LEFT JOIN group_classes as g on c.id = g.cathedra_id\n"
              + "LEFT JOIN students as s on g.id = s.group_class_id WHERE f.id = :id",
          params,
          facultyExtractor);
      if (EntityUtils.isValidCollection(facultyList)) {
        faculty = facultyList.get(0);
      } else {
        throw new EmptyResultDataAccessException(1);
      }
    } catch (EmptyResultDataAccessException ex) {
      throw new ObjectRetrievalFailureException(Faculty.class, id);
    }
    facultiesMap.putIfAbsent(id, faculty);
    faculty.setEmployees(findEmployeesByEntity(faculty.getId(), "faculty_id"));
    return faculty;
  }

  @Override
  public Cathedra findCathedraById(int id) {
    if (EntityUtils.isValidCollection(cathedrasMap.values())
        && cathedrasMap.containsKey(id)) {
      return cathedrasMap.get(id);
    }
    Cathedra cathedra;
    try {
      Map<String, Object> params = new HashMap<>();
      params.put("id", id);
      cathedra = this.namedParameterJdbcTemplate.query(
          "SELECT c.id as cathedraID, c.title as cathedraTitle, c.information as cathedraInfo,\n"
              + "c.boss as cathedraBossID, cb.fio as cathedraBossFio,\n"
              + "cb.position as cathedraBossPosition, cb.degree as cathedraBossDegree,\n"
              + "c.contact_inf as cathedraContInf, edu_programs, c.faculty_id as catFac,\n"
              + "g.id as groupID, g.title as groupTitle, edu_form, s.id as studentID, s.fio,\n"
              + "birthday, sex, fact_address, address, telephone from cathedras as c\n"
              + "LEFT JOIN employees as cb on c.boss = cb.id\n"
              + "LEFT JOIN group_classes as g on c.id = g.cathedra_id\n"
              + "LEFT JOIN students as s on g.id = s.group_class_id WHERE c.id = :id",
          params,
          cathedraExtractor);
      if (cathedra == null) {
        throw new EmptyResultDataAccessException(1);
      }
    } catch (EmptyResultDataAccessException ex) {
      throw new ObjectRetrievalFailureException(Cathedra.class, id);
    }
    cathedrasMap.putIfAbsent(id, cathedra);
    cathedra.setEmployees(findEmployeesByEntity(cathedra.getId(), "cathedra_id"));
    return cathedra;
  }

  @Override
  public GroupClass findGroupClassById(int id) {
    if (EntityUtils.isValidCollection(groupClassesMap.values())
        && groupClassesMap.containsKey(id)) {
      return groupClassesMap.get(id);
    }
    GroupClass groupClass;
    try {
      Map<String, Object> params = new HashMap<>();
      params.put("id", id);
      groupClass = this.namedParameterJdbcTemplate.query(
          "SELECT g.id as groupID, g.title as groupTitle, edu_form, g.cathedra_id as grpCat,\n"
              + "s.id as studentID, fio, birthday, sex, fact_address,\n"
              + "address, telephone from group_classes as g\n"
              + "LEFT JOIN students as s on g.id = s.group_class_id WHERE g.id = :id",
          params,
          groupClassExtractor
      );
    } catch (EmptyResultDataAccessException ex) {
      throw new ObjectRetrievalFailureException(GroupClass.class, id);
    }
    groupClassesMap.putIfAbsent(id, groupClass);
    return groupClass;
  }

  private Set<Employee> findEmployeesByEntity(int id, String entityId) {
    List<Employee> employeeList;
    try {
      Map<String, Object> params = new HashMap<>();
      params.put("id", id);
      employeeList = this.namedParameterJdbcTemplate.query(
          "SELECT * FROM employees WHERE " + entityId + " = :id",
          params,
          employeeExtractor
      );
      if (!EntityUtils.isValidCollection(employeeList)) {
        throw new EmptyResultDataAccessException(1);
      }
    } catch (EmptyResultDataAccessException ex) {
      throw new ObjectRetrievalFailureException(Faculty.class, id);
    }
    return new HashSet<>(employeeList);
  }

  @Override
  public Map<Integer, Faculty> getInternalFaculties() {
    return facultiesMap;
  }

  @Override
  public Map<Integer, Cathedra> getInternalCathedras() {
    return cathedrasMap;
  }

  @Override
  public Map<Integer, GroupClass> getInternalGroupClasses() {
    return groupClassesMap;
  }
}
