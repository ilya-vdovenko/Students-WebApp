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

package org.spring.samples.swa.repository.jdbc;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.spring.samples.swa.model.Employee;
import org.spring.samples.swa.repository.EmployeeRepository;
import org.spring.samples.swa.repository.jdbc.extractor.EmployeeExtractor;
import org.spring.samples.swa.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

/**
 * A jdbc-based implementation of the {@link EmployeeRepository} interface.
 *
 * @author Ilya Vdovenko
 */

@Repository
public class JdbcEmployeeRepositoryImpl implements EmployeeRepository {

  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  private final JdbcTemplate jdbcTemplate;
  private final EmployeeExtractor employeeExtractor;
  private Employee tempEmployee;

  /**
   * Constructor of {@link JdbcEmployeeRepositoryImpl} class.
   *
   * @param namedParameterJdbcTemplate used for named query.
   * @param jdbcTemplate               used for query.
   * @param employeeExtractor          used for extract data to {@link Employee} model.
   */
  @Autowired
  public JdbcEmployeeRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
      JdbcTemplate jdbcTemplate,
      EmployeeExtractor employeeExtractor) {
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    this.jdbcTemplate = jdbcTemplate;
    this.employeeExtractor = employeeExtractor;
  }

  @Override
  public Employee findById(int id) {
    if (tempEmployee != null && (tempEmployee.getId()).equals(id)) {
      return tempEmployee;
    }
    Employee employee;
    try {
      Map<String, Object> params = new HashMap<>();
      params.put("id", id);
      List<Employee> employeeList = this.namedParameterJdbcTemplate.query(
          "SELECT * FROM employees WHERE id= :id",
          params,
          employeeExtractor);
      if (EntityUtils.isValidCollection(employeeList)) {
        employee = employeeList.get(0);
      } else {
        throw new EmptyResultDataAccessException(1);
      }
    } catch (EmptyResultDataAccessException ex) {
      throw new ObjectRetrievalFailureException(Employee.class, id);
    }
    tempEmployee = employee;
    return employee;
  }

  @Override
  public Collection<Employee> findAllByOrderByFullNameAsc() {
    return jdbcTemplate.query("SELECT * FROM employees ORDER BY fullName", employeeExtractor);
  }

}
