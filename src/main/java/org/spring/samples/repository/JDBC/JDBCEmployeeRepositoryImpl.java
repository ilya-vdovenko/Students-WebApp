package org.spring.samples.repository.JDBC;

import org.spring.samples.model.Employee;
import org.spring.samples.repository.EmployeeRepository;
import org.spring.samples.repository.InstituteRepository;
import org.spring.samples.repository.JDBC.JDBCmodel.JDBCEmployee;
import org.spring.samples.repository.JDBC.RowMapper.EmployeeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public class JDBCEmployeeRepositoryImpl implements EmployeeRepository {

  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  private final JdbcTemplate jdbcTemplate;
  private final InstituteRepository instituteRepository;

  @Autowired
  public JDBCEmployeeRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                                    JdbcTemplate jdbcTemplate,
                                    @Qualifier("JDBCInstituteRepositoryImpl") InstituteRepository instituteRepository) {
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    this.jdbcTemplate = jdbcTemplate;
    this.instituteRepository = instituteRepository;
  }

  @Override
  public Employee findById(int id) {
    JDBCEmployee employee;
    try {
      Map<String, Object> params = new HashMap<>();
      params.put("id", id);
      employee = this.namedParameterJdbcTemplate.queryForObject(
        "SELECT * FROM employees WHERE id= :id",
        params,
        new EmployeeRowMapper()
      );
    } catch (EmptyResultDataAccessException ex) {
      throw new ObjectRetrievalFailureException(Employee.class, id);
    }
    if (employee != null) {
      setCathedraAndFaculty(employee);
    }
    return employee;
  }

  @Override
  public Collection<Employee> getAllEmployees() {
    final List<JDBCEmployee> jdbcEmployeeList = jdbcTemplate.query("SELECT * FROM employees ORDER BY fio", new EmployeeRowMapper());
    for (JDBCEmployee employee : jdbcEmployeeList) {
      setCathedraAndFaculty(employee);
    }
    return new ArrayList<>(jdbcEmployeeList);
  }

  private void setCathedraAndFaculty(JDBCEmployee employee) {
    employee.setFaculty(instituteRepository.findFacultyById(employee.getFaculty_id()));
    int catId = employee.getCathedra_id();
    if (catId != 0) {
      employee.setCathedra(instituteRepository.findCathedraById(catId));
    }
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
    for (int id : list_of_employersId) {
      for (Employee employee : employees) {
        if (employee.getId().equals(id)) {
          list_of_employers.add(employee);
        }
      }
    }
    return list_of_employers;
  }

}
