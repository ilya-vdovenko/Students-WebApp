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

package org.spring.samples.swa.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.sql.DataSource;
import org.spring.samples.swa.model.Cathedra;
import org.spring.samples.swa.model.Employee;
import org.spring.samples.swa.model.Faculty;
import org.spring.samples.swa.model.GroupClass;
import org.spring.samples.swa.model.Student;
import org.spring.samples.swa.repository.EmployeeRepository;
import org.spring.samples.swa.repository.InstituteRepository;
import org.spring.samples.swa.repository.StudentRepository;
import org.spring.samples.swa.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all controllers. Also a placeholder for @Transactional annotations.
 *
 * @author Ilya Vdovenko
 */

@Service
public class InstituteServiceImpl implements InstituteService {

  private final StudentRepository studentRepository;
  private final InstituteRepository instituteRepository;
  private final EmployeeRepository employeeRepository;
  private final JdbcTemplate jdbcTemplate;

  /**
   * Constructor of {@link InstituteServiceImpl} class.
   *
   * @param sr         injected {@link StudentRepository} class.
   * @param is         injected {@link InstituteRepository} class.
   * @param er         injected {@link EmployeeRepository} class.
   * @param dataSource injected {@link DataSource} class. Used for JdbcTemplate.
   */
  @Autowired
  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  public InstituteServiceImpl(StudentRepository sr, InstituteRepository is, EmployeeRepository er,
      DataSource dataSource) {
    this.studentRepository = sr;
    this.instituteRepository = is;
    this.employeeRepository = er;
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  @Override
  @Cacheable(value = "faculties")
  @Transactional(readOnly = true)
  public Faculty findFacultyById(int id) {
    return instituteRepository.findFacultyById(id);
  }

  @Override
  @Caching(
      evict = {
          @CacheEvict(value = "faculties", allEntries = true),
          @CacheEvict(value = "cathedras", allEntries = true),
          @CacheEvict(value = "groups", allEntries = true)
      }
  )
  @Transactional(readOnly = true)
  public Collection<Faculty> getFaculties() {
    return instituteRepository.findAllByOrderByTitleAsc();
  }

  @Override
  @Cacheable(value = "cathedras")
  @Transactional(readOnly = true)
  public Cathedra findCathedraById(int id) {
    return instituteRepository.findCathedraById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Collection<Employee> getFacultyEmployees(Set<Employee> employees, int facultyId) {
    return getList(
        jdbcTemplate.queryForList("SELECT employee_id FROM facultyWorker WHERE faculty_id = ?",
            Integer.class, facultyId), employees);
  }

  @Override
  @Transactional(readOnly = true)
  public Collection<Employee> getFacultyBoard(Set<Employee> employees, int facultyId) {
    return getList(
        jdbcTemplate.queryForList("SELECT employee_id FROM facultyBoard WHERE faculty_id = ?",
            Integer.class, facultyId), employees);
  }

  @Override
  @Transactional(readOnly = true)
  public Collection<Employee> getCathedraLecturers(Set<Employee> employees, int cathedraId) {
    return getList(
        jdbcTemplate.queryForList("SELECT employee_id FROM cathedraLectures WHERE cathedra_id = ?",
            Integer.class, cathedraId), employees);
  }

  private Collection<Employee> getList(List<Integer> listOfEmployersId, Set<Employee> employees) {
    Collection<Employee> listOfEmployers = new ArrayList<>();
    if (EntityUtils.isValidCollection(listOfEmployersId)
        && EntityUtils.isValidCollection(employees)) {
      for (int id : listOfEmployersId) {
        for (Employee employee : employees) {
          if (employee.getId().equals(id)) {
            listOfEmployers.add(employee);
          }
        }
      }
    }
    return listOfEmployers;
  }

  @Override
  @Transactional
  public void saveStudent(Student student) {
    studentRepository.save(student);
  }

  @Override
  @Cacheable(value = "students")
  @Transactional(readOnly = true)
  public Student findStudentById(int id) {
    return studentRepository.findById(id);
  }

  @Override
  @CacheEvict(value = "students", allEntries = true)
  @Transactional(readOnly = true)
  public Collection<Student> getStudents() {
    return studentRepository.findAllByOrderByFullNameAsc();
  }

  @Override
  @Cacheable(value = "employees")
  @Transactional(readOnly = true)
  public Employee findEmployeeById(int id) {
    return employeeRepository.findById(id);
  }

  @Override
  @CacheEvict(value = "employees", allEntries = true)
  @Transactional(readOnly = true)
  public Collection<Employee> getEmployees() {
    return employeeRepository.findAllByOrderByFullNameAsc();
  }

  @Override
  @Cacheable(value = "groups")
  @Transactional(readOnly = true)
  public GroupClass findGroupClassById(int id) {
    return instituteRepository.findGroupClassById(id);
  }
}
