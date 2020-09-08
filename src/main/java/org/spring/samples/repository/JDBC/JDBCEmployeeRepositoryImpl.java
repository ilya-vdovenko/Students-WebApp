package org.spring.samples.repository.JDBC;

import org.spring.samples.model.Employee;
import org.spring.samples.repository.EmployeeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class JDBCEmployeeRepositoryImpl implements EmployeeRepository {

  @Override
  public Employee findById(int id) throws DataAccessException {
    return null;
  }

  @Override
  public Collection<Employee> getAllEmployees() throws DataAccessException {
    return null;
  }

  @Override
  public Collection<Employee> getFacultyEmployees(int facultyId) {
    return null;
  }

  @Override
  public Collection<Employee> getFacultySoviet(int facultyId) {
    return null;
  }

  @Override
  public Collection<Employee> getCathedraLecturers(int cathedraId) {
    return null;
  }
}
