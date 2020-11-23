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

import java.util.Collection;
import java.util.Set;
import org.spring.samples.swa.model.Cathedra;
import org.spring.samples.swa.model.Employee;
import org.spring.samples.swa.model.Faculty;
import org.spring.samples.swa.model.GroupClass;
import org.spring.samples.swa.model.Student;

/**
 * Mostly used as a facade so all controllers have a single point of entry.
 *
 * @author Ilya Vdovenko
 */

public interface InstituteService {

  Faculty findFacultyById(int id);

  Collection<Faculty> getFaculties();

  Cathedra findCathedraById(int id);

  Collection<Employee> getFacultyEmployees(Set<Employee> employees, int facultyId);

  Collection<Employee> getFacultySoviet(Set<Employee> employees, int facultyId);

  Collection<Employee> getCathedraLecturers(Set<Employee> employees, int cathedraId);

  void saveStudent(Student student);

  Student findStudentById(int id);

  Collection<Student> getStudents();

  Employee findEmployeeById(int id);

  Collection<Employee> getEmployees();

  GroupClass findGroupClassById(int id);
}
