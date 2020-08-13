package org.spring.samples.service;

import org.spring.samples.model.Cathedra;
import org.spring.samples.model.Employee;
import org.spring.samples.model.Faculty;
import org.spring.samples.model.Group_class;
import org.spring.samples.model.Student;
import org.springframework.dao.DataAccessException;

import java.util.Collection;

/**
 * Mostly used as a facade so all controllers have a single point of entry.
 **/

public interface InstituteService {

  Faculty findFacultyById(int id) throws DataAccessException;

  Collection<Faculty> getFaculties() throws DataAccessException;

  Cathedra findCathedraById(int cathedraId) throws DataAccessException;

  Collection<Employee> getFacultyEmployees(int facultyId) throws DataAccessException;

  Collection<Employee> getFacultySoviet(int facultyId) throws DataAccessException;

  Collection<Employee> getCathedraLecturers(int cathedraId) throws DataAccessException;

  void saveStudent(Student student) throws DataAccessException;

  Student findStudentById(int id) throws DataAccessException;

  Collection<Student> getStudents() throws DataAccessException;

  Employee findEmployeeById(int id) throws DataAccessException;

  Collection<Employee> getEmployees() throws DataAccessException;

  Group_class findGroup_classById(int id) throws DataAccessException;
}
