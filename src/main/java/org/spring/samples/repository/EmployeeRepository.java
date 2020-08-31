package org.spring.samples.repository;

import org.spring.samples.model.Employee;
import org.springframework.dao.DataAccessException;

import java.util.Collection;

/**
 * Repository class for Employee domain objects.
 **/

public interface EmployeeRepository {

  Employee findById(int id) throws DataAccessException;

  Collection<Employee> getAllEmployees() throws DataAccessException;

  Collection<Employee> getFacultyEmployees(int facultyId);

  Collection<Employee> getFacultySoviet(int facultyId);

  Collection<Employee> getCathedraLecturers(int cathedraId);
}
