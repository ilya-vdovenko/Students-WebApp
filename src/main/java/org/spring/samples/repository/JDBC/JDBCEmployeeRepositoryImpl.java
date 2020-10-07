package org.spring.samples.repository.JDBC;

import org.spring.samples.model.Employee;
import org.spring.samples.repository.EmployeeRepository;
import org.spring.samples.repository.JDBC.Extractor.EmployeeExtractor;
import org.spring.samples.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public class JDBCEmployeeRepositoryImpl implements EmployeeRepository {

  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  private final JdbcTemplate jdbcTemplate;
  private final EmployeeExtractor employeeExtractor;
  private Map<Integer, Employee> employeesMap = new LinkedHashMap<>();

  @Autowired
  public JDBCEmployeeRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                                    JdbcTemplate jdbcTemplate,
                                    EmployeeExtractor employeeExtractor) {
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    this.jdbcTemplate = jdbcTemplate;
    this.employeeExtractor = employeeExtractor;
  }

  @Override
  public Employee findById(int id) {
    if (EntityUtils.isValidCollection(employeesMap.values())) {
      if (employeesMap.containsKey(id)) {
        return employeesMap.get(id);
      }
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
    employeesMap.putIfAbsent(id, employee);
    return employee;
  }

  @Override
  public Collection<Employee> getAllEmployees() {
    List<Employee> employeeList = jdbcTemplate.query("SELECT * FROM employees ORDER BY fio", employeeExtractor);
    employeesMap.clear();
    if (EntityUtils.isValidCollection(employeeList)) {
      for (Employee employee : employeeList) {
        employeesMap.putIfAbsent(employee.getId(), employee);
      }
    }
    return employeeList;
  }

  @Override
  public Collection<Employee> getFacultyEmployees(Set<Employee> employees, int facultyId) {
    return getList(jdbcTemplate.queryForList("SELECT employee_id FROM facultyWorker WHERE faculty_id = ?", Integer.class, facultyId), employees);
  }

  @Override
  public Collection<Employee> getFacultySoviet(Set<Employee> employees, int facultyId) {
    return getList(jdbcTemplate.queryForList("SELECT employee_id FROM facultySoviet WHERE faculty_id = ?", Integer.class, facultyId), employees);
  }

  @Override
  public Collection<Employee> getCathedraLecturers(Set<Employee> employees, int cathedraId) {
    return getList(jdbcTemplate.queryForList("SELECT employee_id FROM cathedraLectures WHERE cathedra_id = ?", Integer.class, cathedraId), employees);
  }

  private Collection<Employee> getList(List<Integer> list_of_employersId, Set<Employee> employees) {
    Collection<Employee> list_of_employers = new ArrayList<>();
    if (EntityUtils.isValidCollection(list_of_employersId) &
      EntityUtils.isValidCollection(employees)) {
      for (int id : list_of_employersId) {
        for (Employee employee : employees) {
          if (employee.getId().equals(id)) {
            list_of_employers.add(employee);
          }
        }
      }
    }
    return list_of_employers;
  }

}
