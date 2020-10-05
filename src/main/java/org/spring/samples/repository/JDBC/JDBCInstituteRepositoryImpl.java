package org.spring.samples.repository.JDBC;

import org.spring.samples.model.BaseEntity;
import org.spring.samples.model.Cathedra;
import org.spring.samples.model.Faculty;
import org.spring.samples.model.Group_class;
import org.spring.samples.model.Student;
import org.spring.samples.repository.InstituteRepository;
import org.spring.samples.repository.JDBC.JDBCmodel.JDBCCathedra;
import org.spring.samples.repository.JDBC.JDBCmodel.JDBCEmployee;
import org.spring.samples.repository.JDBC.JDBCmodel.JDBCFaculty;
import org.spring.samples.repository.JDBC.RowMapper.CathedraRowMapper;
import org.spring.samples.repository.JDBC.RowMapper.EmployeeRowMapper;
import org.spring.samples.repository.JDBC.RowMapper.FacultyRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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

  //TODO: попробуй вместо этих громоздки вещей сделать ResultExtractor
  private void loadCathedrasAndEmployees(JDBCFaculty faculty) {
    final List<JDBCEmployee> employeeList = namedParameterQuery(OneToManyEnum.EMPLOYEE, faculty, "faculty_id");
    final List<JDBCCathedra> cathedraList = namedParameterQuery(OneToManyEnum.CATHEDRA, faculty, "faculty_id");
    for (JDBCEmployee employee : employeeList) {
      employee.setFaculty(faculty);
      if (employee.getId().equals(faculty.getBoss_id())) {
        faculty.setBoss(employee);
      }
      for (JDBCCathedra cathedra : cathedraList) {
        cathedra.setFaculty(faculty);
        if (employee.getCathedra_id().equals(cathedra.getId())) {
          employee.setCathedra(cathedra);
        }
        if (employee.getId().equals(cathedra.getBoss_id())) {
          cathedra.setBoss(employee);
        }
        loadGroupClassAndEmployees(cathedra);
      }
    }
    faculty.setEmployees(new HashSet<>(employeeList));
    faculty.setCathedras(new HashSet<>(cathedraList));
  }

  private void loadGroupClassAndEmployees(Cathedra cathedra) {
    final List<JDBCEmployee> employeeList = namedParameterQuery(OneToManyEnum.EMPLOYEE, cathedra, "cathedra_id");
    final List<Group_class> groupClassList = namedParameterQuery(OneToManyEnum.GROUP, cathedra, "cathedra_id");
    for (JDBCEmployee employee : employeeList) {
      employee.setCathedra(cathedra);
      employee.setFaculty(cathedra.getFaculty());
    }
    for (Group_class group_class : groupClassList) {
      group_class.setCathedra(cathedra);
      loadGroupStudents(group_class);
    }
    cathedra.setEmployees(new HashSet<>(employeeList));
    cathedra.setGroup_classes(new HashSet<>(groupClassList));
  }

  private void loadGroupStudents(Group_class group_class) {
    group_class.setGroup_students(new HashSet<>(namedParameterQuery(OneToManyEnum.STUDENT, group_class, "group_class_id")));
  }

  @SuppressWarnings("unchecked")
  private <T> List<T> namedParameterQuery(OneToManyEnum oneToManyEnum, BaseEntity entity, String entity_id) {
    Map<String, Object> params = new HashMap<>();
    params.put("id", entity.getId());
    RowMapper<T> rowMapper = null;
    String tableName = "";
    switch (oneToManyEnum) {
      case EMPLOYEE:
        rowMapper = (RowMapper<T>) new EmployeeRowMapper();
        tableName = "employees";
        break;
      case CATHEDRA:
        rowMapper = (RowMapper<T>) new CathedraRowMapper();
        tableName = "cathedras";
        break;
      case GROUP:
        rowMapper = (RowMapper<T>) BeanPropertyRowMapper.newInstance(Group_class.class);
        tableName = "group_classes";
        break;
      case STUDENT:
        rowMapper = (RowMapper<T>) BeanPropertyRowMapper.newInstance(Student.class);
        tableName = "students";
        break;
    }
    return this.namedParameterJdbcTemplate.query("SELECT * FROM " + tableName + " WHERE " + entity_id + " = :id", params, rowMapper);
  }

  @Override
  public Collection<Faculty> getAllFaculties() {
    final List<JDBCFaculty> jdbcFacultyList = jdbcTemplate.query("SELECT * FROM faculties", new FacultyRowMapper());
    for (JDBCFaculty faculty : jdbcFacultyList) {
      loadCathedrasAndEmployees(faculty);
    }
    return new ArrayList<>(jdbcFacultyList);
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
      loadCathedrasAndEmployees(faculty);
    }
    return faculty;
  }

  @Override
  public Cathedra findCathedraById(int id) {
    Cathedra cathedra;
    try {
      Map<String, Object> params = new HashMap<>();
      params.put("id", id);
      cathedra = this.namedParameterJdbcTemplate.queryForObject(
        "SELECT * FROM cathedras WHERE id= :id",
        params,
        new CathedraRowMapper()
      );
    } catch (EmptyResultDataAccessException ex) {
      throw new ObjectRetrievalFailureException(Cathedra.class, id);
    }
    if (cathedra != null) {
      loadGroupClassAndEmployees(cathedra);
    }
    return cathedra;
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
        BeanPropertyRowMapper.newInstance(Group_class.class)
      );
    } catch (EmptyResultDataAccessException ex) {
      throw new ObjectRetrievalFailureException(Group_class.class, id);
    }
    if (group_class != null) {
      loadGroupStudents(group_class);
    }
    return group_class;
  }

  private enum OneToManyEnum {
    CATHEDRA,
    EMPLOYEE,
    GROUP,
    STUDENT
  }

}
