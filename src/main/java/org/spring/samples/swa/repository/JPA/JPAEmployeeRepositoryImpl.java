/*
 * Copyright 2019-2020, Ilya Vdovenko and the Students-WebApp contributors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.spring.samples.swa.repository.JPA;

import org.spring.samples.swa.model.Employee;
import org.spring.samples.swa.repository.EmployeeRepository;
import org.spring.samples.swa.util.EntityUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A JPA implementation of the {@link EmployeeRepository} interface.
 *
 * @author Ilya vdovenko
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
