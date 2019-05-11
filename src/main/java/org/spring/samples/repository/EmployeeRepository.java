package org.spring.samples.repository;

import org.spring.samples.model.Employee;
import org.springframework.dao.DataAccessException;

import java.util.Collection;

public interface EmployeeRepository {

    Employee findById(int id) throws DataAccessException;

    Collection<Employee> getAllEmployees() throws DataAccessException;
}
