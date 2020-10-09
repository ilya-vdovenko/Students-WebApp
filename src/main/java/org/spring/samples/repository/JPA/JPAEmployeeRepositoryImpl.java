package org.spring.samples.repository.JPA;

import org.spring.samples.model.Employee;
import org.spring.samples.repository.EmployeeRepository;
import org.spring.samples.util.EntityUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * JPA implementation of the {@link EmployeeRepository} interface.
 **/

@Repository
public class JPAEmployeeRepositoryImpl implements EmployeeRepository {

  private final Map<Integer, Employee> employeesMap = new LinkedHashMap<>();

  @PersistenceContext
  private EntityManager em;

  @Override
  public Employee findById(int id) {
    if (EntityUtils.isValidCollection(employeesMap.values())) {
      if (employeesMap.containsKey(id)) {
        return employeesMap.get(id);
      }
    }
    Query query = this.em.createQuery("from Employee as e where e.id =:id");
    query.setParameter("id", id);
    Employee employee = (Employee) query.getSingleResult();
    employeesMap.putIfAbsent(id, employee);
    return employee;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Collection<Employee> findAll() {
    List<Employee> employeeList = this.em.createQuery("from Employee").getResultList();
    employeesMap.clear();
    if (EntityUtils.isValidCollection(employeeList)) {
      for (Employee employee : employeeList) {
        employeesMap.putIfAbsent(employee.getId(), employee);
      }
    }
    return employeeList;
  }

}
