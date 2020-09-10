package org.spring.samples.repository;

import org.spring.samples.model.Employee;

import java.util.Collection;
import java.util.Set;

/**
 * Repository class for Employee domain objects.
 **/

public interface EmployeeRepository {

  Employee findById(int id);

  Collection<Employee> getAllEmployees();

  Collection<Employee> getFacultyEmployees(Set<Employee> employees, int facultyId);

  Collection<Employee> getFacultySoviet(Set<Employee> employees, int facultyId);

  Collection<Employee> getCathedraLecturers(Set<Employee> employees, int cathedraId);
}
