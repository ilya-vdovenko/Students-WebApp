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

package org.spring.samples.swa.cache;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.spring.samples.swa.model.Cathedra;
import org.spring.samples.swa.model.Employee;
import org.spring.samples.swa.model.Faculty;
import org.spring.samples.swa.model.GroupClass;
import org.spring.samples.swa.model.Student;
import org.spring.samples.swa.repository.EmployeeRepository;
import org.spring.samples.swa.repository.InstituteRepository;
import org.spring.samples.swa.repository.StudentRepository;
import org.spring.samples.swa.service.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(locations = "classpath:SpringConfigs/cache-test-config.xml")
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
class CacheImplementationTests {

  private final int TEST_ID = 1;

  @Autowired
  private InstituteService service;

  @Autowired
  private InstituteRepository instituteRepository;

  @Autowired
  private EmployeeRepository employeeRepository;

  @Autowired
  private StudentRepository studentRepository;

  @BeforeEach
  void setup() {
    Faculty faculty = new Faculty();
    faculty.setId(TEST_ID);
    faculty.setTitle("Энергетики и систем управления");
    Cathedra cathedra = new Cathedra();
    cathedra.setId(TEST_ID);
    cathedra.setTitle("Электропривода, автоматики и управления в технических системах");
    GroupClass groupClass = new GroupClass();
    groupClass.setId(TEST_ID);
    groupClass.setTitle("АТ-121");
    Employee employee = new Employee();
    employee.setId(TEST_ID);
    employee.setFio("Бурковский Виктор Леонидович");
    Student student = new Student();
    student.setId(TEST_ID);
    student.setFio("Вдовенко Илья Сергеевич");

    given(this.instituteRepository.findFacultyById(TEST_ID)).willReturn(faculty);
    given(this.instituteRepository.findCathedraById(TEST_ID)).willReturn(cathedra);
    given(this.instituteRepository.findGroupClassById(TEST_ID)).willReturn(groupClass);
    given(this.employeeRepository.findById(TEST_ID)).willReturn(employee);
    given(this.studentRepository.findById(TEST_ID)).willReturn(student);
  }

  @RepeatedTest(2)
  void shouldFindFacultyById() {
    Faculty faculty = this.service.findFacultyById(TEST_ID);
    assertThat(faculty.getTitle()).isEqualTo("Энергетики и систем управления");
    verify(instituteRepository, times(1)).findFacultyById(TEST_ID);
  }

  @RepeatedTest(2)
  void shouldFindCathedraById() {
    Cathedra cathedra = this.service.findCathedraById(TEST_ID);
    assertThat(cathedra.getTitle())
        .isEqualTo("Электропривода, автоматики и управления в технических системах");
    verify(instituteRepository, times(1)).findCathedraById(TEST_ID);
  }

  @RepeatedTest(2)
  void shouldFindGroupClassById() {
    GroupClass groupClass = this.service.findGroupClassById(TEST_ID);
    assertThat(groupClass.getTitle()).isEqualTo("АТ-121");
    verify(instituteRepository, times(1)).findGroupClassById(TEST_ID);
  }

  @RepeatedTest(2)
  void shouldFindStudentById() {
    Student student = this.service.findStudentById(TEST_ID);
    assertThat(student.getFio()).isEqualTo("Вдовенко Илья Сергеевич");
    verify(studentRepository, times(1)).findById(TEST_ID);
  }

  @RepeatedTest(2)
  void shouldFindEmployeeById() {
    Employee employee = this.service.findEmployeeById(TEST_ID);
    assertThat(employee.getFio()).isEqualTo("Бурковский Виктор Леонидович");
    verify(employeeRepository, times(1)).findById(TEST_ID);
  }
}
