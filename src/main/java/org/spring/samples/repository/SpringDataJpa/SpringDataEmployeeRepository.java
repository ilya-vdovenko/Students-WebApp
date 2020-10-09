package org.spring.samples.repository.SpringDataJpa;

import org.spring.samples.model.Employee;
import org.spring.samples.repository.EmployeeRepository;
import org.springframework.data.repository.Repository;

public interface SpringDataEmployeeRepository extends EmployeeRepository, Repository<Employee, Integer> {
}
