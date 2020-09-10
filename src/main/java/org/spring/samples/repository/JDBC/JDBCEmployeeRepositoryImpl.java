package org.spring.samples.repository.JDBC;

import org.spring.samples.model.Employee;
import org.spring.samples.repository.EmployeeRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;

@Repository
public class JDBCEmployeeRepositoryImpl implements EmployeeRepository {

  @Override
  public Employee findById(int id) {
    return null;
  }

  @Override
  public Collection<Employee> getAllEmployees() {
    return null;
  }

  @Override
  public Collection<Employee> getFacultyEmployees(Set<Employee> employees, int facultyId) {
    return null;
  }

  @Override
  public Collection<Employee> getFacultySoviet(Set<Employee> employees, int facultyId) {
    return null;
  }

  @Override
  public Collection<Employee> getCathedraLecturers(Set<Employee> employees, int cathedraId) {
    return null;
  }
}
