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

package org.spring.samples.service;

import org.spring.samples.model.Cathedra;
import org.spring.samples.model.Employee;
import org.spring.samples.model.Faculty;
import org.spring.samples.model.Group_class;
import org.spring.samples.model.Student;
import org.spring.samples.repository.EmployeeRepository;
import org.spring.samples.repository.InstituteRepository;
import org.spring.samples.repository.StudentRepository;
import org.spring.samples.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Mostly used as a facade for all controllers.
 * Also a placeholder for @Transactional annotations.
 *
 * @author Ilya Vdovenko
 */

@Service
public class InstituteServiceImpl implements InstituteService {

  private final StudentRepository studentRepository;
  private final InstituteRepository instituteRepository;
  private final EmployeeRepository employeeRepository;
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  public InstituteServiceImpl(StudentRepository sr, InstituteRepository is, EmployeeRepository er, DataSource dataSource) {
    this.studentRepository = sr;
    this.instituteRepository = is;
    this.employeeRepository = er;
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  @Override
  @Transactional(readOnly = true)
  public Faculty findFacultyById(int id) {
    return instituteRepository.findFacultyById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Collection<Faculty> getFaculties() {
    return instituteRepository.findAllFaculties();
  }

  @Override
  @Transactional(readOnly = true)
  public Cathedra findCathedraById(int id) {
    return instituteRepository.findCathedraById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Collection<Employee> getFacultyEmployees(Set<Employee> employees, int facultyId) {
    return getList(jdbcTemplate.queryForList("SELECT employee_id FROM facultyWorker WHERE faculty_id = ?",
      Integer.class, facultyId), employees);
  }

  @Override
  @Transactional(readOnly = true)
  public Collection<Employee> getFacultySoviet(Set<Employee> employees, int facultyId) {
    return getList(jdbcTemplate.queryForList("SELECT employee_id FROM facultySoviet WHERE faculty_id = ?",
      Integer.class, facultyId), employees);
  }

  @Override
  @Transactional(readOnly = true)
  public Collection<Employee> getCathedraLecturers(Set<Employee> employees, int cathedraId) {
    return getList(jdbcTemplate.queryForList("SELECT employee_id FROM cathedraLectures WHERE cathedra_id = ?",
      Integer.class, cathedraId), employees);
  }

  private Collection<Employee> getList(List<Integer> list_of_employersId, Set<Employee> employees) {
    Collection<Employee> list_of_employers = new ArrayList<>();
    if (EntityUtils.isValidCollection(list_of_employersId) &
      EntityUtils.isValidCollection(employees)) {
      for (int id : list_of_employersId) {
        for (Employee employee : employees) {
          if (employee.getId().equals(id)) {
            list_of_employers.add(employee);
          }
        }
      }
    }
    return list_of_employers;
  }

  @Override
  @Transactional
  public void saveStudent(Student student) {
    studentRepository.save(student);
  }

  @Override
  @Transactional(readOnly = true)
  public Student findStudentById(int id) {
    return studentRepository.findById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Collection<Student> getStudents() {
    return studentRepository.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public Employee findEmployeeById(int id) {
    return employeeRepository.findById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Collection<Employee> getEmployees() {
    return employeeRepository.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public Group_class findGroup_classById(int id) {
    return instituteRepository.findGroup_classById(id);
  }
}
