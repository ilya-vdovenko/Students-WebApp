package org.spring.samples.service;

import org.spring.samples.model.Cathedra;
import org.spring.samples.model.Employee;
import org.spring.samples.model.Faculty;
import org.spring.samples.model.Group_class;
import org.spring.samples.model.Student;
import org.spring.samples.repository.EmployeeRepository;
import org.spring.samples.repository.InstituteRepository;
import org.spring.samples.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Mostly used as a facade for all controllers.
 * Also a placeholder for @Transactional annotations.
 **/

@Service
public class InstituteServiceImpl implements InstituteService {

  private final StudentRepository studentRepository;
  private final InstituteRepository instituteRepository;
  private final EmployeeRepository employeeRepository;

  @Autowired
  public InstituteServiceImpl(StudentRepository sr, InstituteRepository is, EmployeeRepository er) {
    this.studentRepository = sr;
    this.instituteRepository = is;
    this.employeeRepository = er;
  }

  @Override
  @Transactional(readOnly = true)
  public Faculty findFacultyById(int id) throws DataAccessException {
    return instituteRepository.findFacultyById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Collection<Faculty> getFaculties() throws DataAccessException {
    return instituteRepository.getAllFaculties();
  }

  @Override
  @Transactional(readOnly = true)
  public Cathedra findCathedraById(int cathedraId) throws DataAccessException {
    return instituteRepository.findCathedraById(cathedraId);
  }

  @Override
  @Transactional(readOnly = true)
  public Collection<Employee> getFacultyEmployees(int facultyId) throws DataAccessException {
    return employeeRepository.getFacultyEmployees(facultyId);
  }

  @Override
  @Transactional(readOnly = true)
  public Collection<Employee> getFacultySoviet(int facultyId) throws DataAccessException {
    return employeeRepository.getFacultySoviet(facultyId);
  }

  @Override
  @Transactional(readOnly = true)
  public Collection<Employee> getCathedraLecturers(int cathedraId) throws DataAccessException {
    return employeeRepository.getCathedraLecturers(cathedraId);
  }

  @Override
  @Transactional
  public void saveStudent(Student student) throws DataAccessException {
    studentRepository.save(student);
  }

  @Override
  @Transactional(readOnly = true)
  public Student findStudentById(int id) throws DataAccessException {
    return studentRepository.findById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Collection<Student> getStudents() throws DataAccessException {
    return studentRepository.getAllStudents();
  }

  @Override
  @Transactional(readOnly = true)
  public Employee findEmployeeById(int id) throws DataAccessException {
    return employeeRepository.findById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Collection<Employee> getEmployees() throws DataAccessException {
    return employeeRepository.getAllEmployees();
  }

  @Override
  @Transactional(readOnly = true)
  public Group_class findGroup_classById(int id) throws DataAccessException {
    return instituteRepository.findGroup_classById(id);
  }
}
