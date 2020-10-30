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

package org.spring.samples.swa.repository.JDBC;

import org.spring.samples.swa.model.Cathedra;
import org.spring.samples.swa.model.Employee;
import org.spring.samples.swa.model.Faculty;
import org.spring.samples.swa.model.Group_class;
import org.spring.samples.swa.repository.InstituteRepository;
import org.spring.samples.swa.repository.JDBC.Extractor.CathedraExtractor;
import org.spring.samples.swa.repository.JDBC.Extractor.EmployeeExtractor;
import org.spring.samples.swa.repository.JDBC.Extractor.FacultyExtractor;
import org.spring.samples.swa.repository.JDBC.Extractor.Group_classExtractor;
import org.spring.samples.swa.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A JDBC-based implementation of the {@link InstituteRepository} interface.
 *
 * @author Ilya Vdovenko
 */

@Repository
public class JDBCInstituteRepositoryImpl implements InstituteRepository {

  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  private final JdbcTemplate jdbcTemplate;
  private final FacultyExtractor facultyExtractor;

  @Autowired
  private EmployeeExtractor employeeExtractor;

  @Autowired
  private CathedraExtractor cathedraExtractor;

  @Autowired
  private Group_classExtractor group_classExtractor;

  private final Map<Integer, Faculty> facultiesMap = new LinkedHashMap<>();
  private final Map<Integer, Cathedra> cathedrasMap = new LinkedHashMap<>();
  private final Map<Integer, Group_class> group_classesMap = new LinkedHashMap<>();

  @Autowired
  public JDBCInstituteRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                                     FacultyExtractor facultyExtractor,
                                     JdbcTemplate jdbcTemplate) {
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    this.facultyExtractor = facultyExtractor;
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Collection<Faculty> findAllFaculties() {
    List<Faculty> facultyList = this.jdbcTemplate.query(
      "SELECT f.id as facultyID, f.title as facultyTitle, f.information as facultyInfo,\n" +
        "f.boss as facultyBossID, fb.fio as facultyBossFio, fb.position as facultyBossPosition,\n" +
        "fb.degree as facultyBossDegree, f.contact_inf as facultyContInf, c.id as cathedraID,\n" +
        "c.title as cathedraTitle, c.information as cathedraInfo, c.boss as cathedraBossID,\n" +
        "cb.fio as cathedraBossFio, cb.position as cathedraBossPosition, cb.degree as cathedraBossDegree,\n" +
        "c.contact_inf as cathedraContInf, edu_programs, g.id as groupID,\n" +
        "g.title as groupTitle, edu_form, s.id as studentID, s.fio, birthday, sex,\n" +
        "fact_address, address, telephone from faculties as f\n" +
        "LEFT JOIN cathedras c on f.id = c.faculty_id\n" +
        "LEFT JOIN employees fb on f.boss = fb.id\n" +
        "LEFT JOIN employees cb on c.boss = cb.id\n" +
        "LEFT JOIN group_classes as g on c.id = g.cathedra_id\n" +
        "LEFT JOIN students as s on g.id = s.group_class_id ORDER BY f.title", facultyExtractor);

    if (EntityUtils.isValidCollection(facultyList)) {
      EntityUtils.setEntityMaps(facultyList, facultiesMap, cathedrasMap, group_classesMap);
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
    if (EntityUtils.isValidCollection(facultiesMap.values())) {
      if (facultiesMap.containsKey(id)) {
        return facultiesMap.get(id);
      }
    }
    Faculty faculty;
    try {
      Map<String, Object> params = new HashMap<>();
      params.put("id", id);
      List<Faculty> facultyList = this.namedParameterJdbcTemplate.query(
        "SELECT f.id as facultyID, f.title as facultyTitle, f.information as facultyInfo,\n" +
          "f.boss as facultyBossID, fb.fio as facultyBossFio, fb.position as facultyBossPosition,\n" +
          "fb.degree as facultyBossDegree, f.contact_inf as facultyContInf, c.id as cathedraID,\n" +
          "c.title as cathedraTitle, c.information as cathedraInfo, c.boss as cathedraBossID,\n" +
          "cb.fio as cathedraBossFio, cb.position as cathedraBossPosition, cb.degree as cathedraBossDegree,\n" +
          "c.contact_inf as cathedraContInf, edu_programs, g.id as groupID,\n" +
          "g.title as groupTitle, edu_form, s.id as studentID, s.fio, birthday, sex,\n" +
          "fact_address, address, telephone from faculties as f\n" +
          "LEFT JOIN cathedras c on f.id = c.faculty_id\n" +
          "LEFT JOIN employees fb on f.boss = fb.id\n" +
          "LEFT JOIN employees cb on c.boss = cb.id\n" +
          "LEFT JOIN group_classes as g on c.id = g.cathedra_id\n" +
          "LEFT JOIN students as s on g.id = s.group_class_id WHERE f.id = :id",
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
    if (EntityUtils.isValidCollection(cathedrasMap.values())) {
      if (cathedrasMap.containsKey(id)) {
        return cathedrasMap.get(id);
      }
    }
    Cathedra cathedra;
    try {
      Map<String, Object> params = new HashMap<>();
      params.put("id", id);
      cathedra = this.namedParameterJdbcTemplate.query(
        "SELECT c.id as cathedraID, c.title as cathedraTitle, c.information as cathedraInfo, c.boss as cathedraBossID,\n" +
          "cb.fio as cathedraBossFio, cb.position as cathedraBossPosition, cb.degree as cathedraBossDegree,\n" +
          "c.contact_inf as cathedraContInf, edu_programs, c.faculty_id as catFac, g.id as groupID,\n" +
          "g.title as groupTitle, edu_form, s.id as studentID, s.fio, birthday, sex,\n" +
          "fact_address, address, telephone from cathedras as c\n" +
          "LEFT JOIN employees as cb on c.boss = cb.id\n" +
          "LEFT JOIN group_classes as g on c.id = g.cathedra_id\n" +
          "LEFT JOIN students as s on g.id = s.group_class_id WHERE c.id = :id",
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
  public Group_class findGroup_classById(int id) {
    if (EntityUtils.isValidCollection(group_classesMap.values())) {
      if (group_classesMap.containsKey(id)) {
        return group_classesMap.get(id);
      }
    }
    Group_class group_class;
    try {
      Map<String, Object> params = new HashMap<>();
      params.put("id", id);
      group_class = this.namedParameterJdbcTemplate.query(
        "SELECT g.id as groupID, g.title as groupTitle, edu_form, g.cathedra_id as grpCat,\n" +
          "s.id as studentID, fio, birthday, sex, fact_address,\n" +
          "address, telephone from group_classes as g\n" +
          "LEFT JOIN students as s on g.id = s.group_class_id WHERE g.id = :id",
        params,
        group_classExtractor
      );
    } catch (EmptyResultDataAccessException ex) {
      throw new ObjectRetrievalFailureException(Group_class.class, id);
    }
    group_classesMap.putIfAbsent(id, group_class);
    return group_class;
  }

  private Set<Employee> findEmployeesByEntity(int id, String entity_id) {
    List<Employee> employeeList;
    try {
      Map<String, Object> params = new HashMap<>();
      params.put("id", id);
      employeeList = this.namedParameterJdbcTemplate.query(
        "SELECT * FROM employees WHERE " + entity_id + " = :id",
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
  public Map<Integer, Group_class> getInternalGroup_classes() {
    return group_classesMap;
  }
}
