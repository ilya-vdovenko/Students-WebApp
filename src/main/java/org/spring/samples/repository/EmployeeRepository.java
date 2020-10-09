package org.spring.samples.repository;

import org.spring.samples.model.Employee;

import java.util.Collection;

/**
 * Repository class for Employee domain objects.
 **/

public interface EmployeeRepository {

  Employee findById(int id);

  Collection<Employee> findAll();

}
