package org.spring.samples.repository.JDBC;

import org.spring.samples.model.Cathedra;
import org.spring.samples.model.Faculty;
import org.spring.samples.model.Group_class;
import org.spring.samples.repository.InstituteRepository;
import org.spring.samples.repository.JDBC.JDBCmodel.JDBCEmployee;
import org.spring.samples.repository.JDBC.JDBCmodel.JDBCFaculty;
import org.spring.samples.repository.JDBC.RowMapper.EmployeeRowMapper;
import org.spring.samples.repository.JDBC.RowMapper.FacultyRowMapper;
import org.spring.samples.repository.JDBC.RowMapper.Group_classRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Repository
public class JDBCInstituteRepositoryImpl implements InstituteRepository {

  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  private final JdbcTemplate jdbcTemplate;

  public JDBCInstituteRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                                     JdbcTemplate jdbcTemplate) {
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Faculty findFacultyById(int id) {
    JDBCFaculty faculty;
    try {
      Map<String, Object> params = new HashMap<>();
      params.put("id", id);
      faculty = this.namedParameterJdbcTemplate.queryForObject(
        "SELECT * FROM faculties WHERE id= :id",
        params,
        new FacultyRowMapper()
      );
    } catch (EmptyResultDataAccessException ex) {
      throw new ObjectRetrievalFailureException(Faculty.class, id);
    }
    if (faculty != null) {
      loadEmployees(faculty);
    }
    return faculty;
  }

  public void loadEmployees(JDBCFaculty faculty) {
    Map<String, Object> params = new HashMap<>();
    params.put("id", faculty.getId());
    final List<JDBCEmployee> employeeList = this.namedParameterJdbcTemplate.query(
      "SELECT * FROM employees WHERE faculty_id = :id",
      params,
      new EmployeeRowMapper()
    );
    for (JDBCEmployee employee : employeeList) {
      employee.setFaculty(faculty);
      if (employee.getId().equals(faculty.getBoss_id())) {
        faculty.setBoss(employee);
      }
    }
    faculty.setEmployees(new HashSet<>(employeeList));
  }

  @Override
  public Collection<Faculty> getAllFaculties() {
    final List<JDBCFaculty> jdbcFacultyList = jdbcTemplate.query("SELECT * FROM faculties", new FacultyRowMapper());
    for (JDBCFaculty faculty : jdbcFacultyList) {
      loadEmployees(faculty);
    }
    return new ArrayList<>(jdbcFacultyList);
  }

  @Override
  public Cathedra findCathedraById(int id) {
/*
    Cathedra cathedra;
    try {
      Map<String, Object> params = new HashMap<>();
      params.put("id", id);
      cathedra = this.namedParameterJdbcTemplate.queryForObject(
        "SELECT * FROM cathedras WHERE id= :id",
        params,
        new CathedraRowMapper(this, employeeRepository)
      );
    } catch (EmptyResultDataAccessException ex) {
      throw new ObjectRetrievalFailureException(Cathedra.class, id);
    }
    return cathedra;
*/
    return null;
  }

  @Override
  public Group_class findGroup_classById(int id) {
    Group_class group_class;
    try {
      Map<String, Object> params = new HashMap<>();
      params.put("id", id);
      group_class = this.namedParameterJdbcTemplate.queryForObject(
        "SELECT * FROM group_classes WHERE id= :id",
        params,
        new Group_classRowMapper(this)
      );
    } catch (EmptyResultDataAccessException ex) {
      throw new ObjectRetrievalFailureException(Group_class.class, id);
    }
    return group_class;
  }

}
