package org.spring.samples.service;

import org.spring.samples.model.Cathedra;
import org.spring.samples.model.Employee;
import org.spring.samples.model.Faculty;
import org.spring.samples.model.Group_class;
import org.spring.samples.model.Student;

import java.util.Collection;
import java.util.Set;

/**
 * Mostly used as a facade so all controllers have a single point of entry.
 **/

public interface InstituteService {

  Faculty findFacultyById(int id);

  Collection<Faculty> getFaculties();

  Cathedra findCathedraById(int cathedraId);

  Collection<Employee> getFacultyEmployees(Set<Employee> employees, int facultyId);

  Collection<Employee> getFacultySoviet(Set<Employee> employees, int facultyId);

  Collection<Employee> getCathedraLecturers(Set<Employee> employees, int cathedraId);

  void saveStudent(Student student);

  Student findStudentById(int id);

  Collection<Student> getStudents();

  Employee findEmployeeById(int id);

  Collection<Employee> getEmployees();

  Group_class findGroup_classById(int id);
}
