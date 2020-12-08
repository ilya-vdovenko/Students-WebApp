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

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Collection;
import org.junit.jupiter.api.Test;
import org.spring.samples.swa.model.Cathedra;
import org.spring.samples.swa.model.Employee;
import org.spring.samples.swa.model.Faculty;
import org.spring.samples.swa.model.GroupClass;
import org.spring.samples.swa.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Base class for {@link InstituteService} integration tests.
 *
 * @author Ilya Vdovenko
 */
abstract class AbstractInstituteServiceTests {

  @Autowired
  protected InstituteService service;

  @Test
  void shouldFindFacultyById() {
    Faculty faculty = this.service.findFacultyById(1);
    assertThat(faculty.getTitle()).isEqualTo("Энергетики и систем управления");
    assertThat(faculty.getCathedras().size()).isEqualTo(2);
    assertThat(faculty.getEmployees().isEmpty()).isFalse();
  }

  @Test
  void shouldGetFaculties() {
    Collection<Faculty> faculties = this.service.getFaculties();
    assertThat(faculties).hasSize(2);
  }

  @Test
  void shouldFindCathedraById() {
    Cathedra cathedra = this.service.findCathedraById(2);
    assertThat(cathedra.getTitle()).isEqualTo("Электромеханических систем и электроснабжения");
    assertThat(cathedra.getFaculty().getTitle()).isEqualTo("Энергетики и систем управления");
    assertThat(cathedra.getEmployees().isEmpty()).isFalse();
    assertThat(cathedra.getGroupClasses().isEmpty()).isFalse();
  }

  @Test
  void shouldGetFacultyEmployees() {
    Faculty faculty = this.service.findFacultyById(1);
    Collection<Employee> employees = this.service.getFacultyEmployees(faculty.getEmployees(), 1);
    assertThat(employees).hasSize(3);
  }

  @Test
  void shouldGetFacultySoviet() {
    Faculty faculty = this.service.findFacultyById(1);
    Collection<Employee> employees = this.service.getFacultySoviet(faculty.getEmployees(), 1);
    assertThat(employees).hasSize(5);
  }

  @Test
  void shouldGetCathedraLecturers() {
    Cathedra cathedra = this.service.findCathedraById(3);
    Collection<Employee> employees = this.service.getCathedraLecturers(cathedra.getEmployees(), 3);
    assertThat(employees).hasSize(4);
  }

  @Test
  @Transactional
  void shouldInsertStudent() {
    Collection<Student> students = this.service.getStudents();
    int found = students.size();

    Student student = new Student();
    student.setFio("Иванов Иван Иванович");
    student.setBirthday(LocalDate.parse("1994-06-23"));
    student.setSex("муж");
    student.setFactAddress("-");
    student.setAddress("-");
    student.setTelephone("89081355694");
    student.setFaculty(this.service.findFacultyById(1));
    student.setCathedra(this.service.findCathedraById(2));
    student.setGroupClass(this.service.findGroupClassById(3));

    this.service.saveStudent(student);
    assertThat(student.getId()).isNotZero();
    students = this.service.getStudents();
    assertThat(students).hasSize(found + 1);
  }

  @Test
  @Transactional
  void shouldUpdateStudent() {
    Student student = this.service.findStudentById(2);
    String newFio = "Сидоров Иван Иванович";
    student.setFio(newFio);
    this.service.saveStudent(student);
    student = this.service.findStudentById(2);
    assertThat(student.getFio()).isEqualTo(newFio);
  }

  @Test
  void shouldFindStudentById() {
    Student student = this.service.findStudentById(1);
    assertThat(student.getFio()).isEqualTo("Вдовенко Илья Сергеевич");
    assertThat(student.getFaculty().getTitle()).isEqualTo("Энергетики и систем управления");
    assertThat(student.getCathedra().getTitle())
        .isEqualTo("Электропривода, автоматики и управления в технических системах");
  }

  @Test
  void shouldGetStudents() {
    Collection<Student> students = this.service.getStudents();
    assertThat(students).hasSize(19);
  }

  @Test
  void shouldFindEmployeeById() {
    Employee employee = this.service.findEmployeeById(1);
    assertThat(employee.getFio()).isEqualTo("Бурковский Виктор Леонидович");
    assertThat(employee.getFaculty().getTitle()).isEqualTo("Энергетики и систем управления");
  }

  @Test
  void shouldGetEmployees() {
    Collection<Employee> employees = this.service.getEmployees();
    assertThat(employees).hasSize(26);
  }

  @Test
  void shouldFindGroupClassById() {
    GroupClass groupClass = this.service.findGroupClassById(3);
    assertThat(groupClass.getTitle()).isEqualTo("ПТ-111");
    assertThat(groupClass.getCathedra().getTitle())
        .isEqualTo("Электромеханических систем и электроснабжения");
  }
}
