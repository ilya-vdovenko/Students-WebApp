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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;

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
  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  public InstituteServiceImpl(StudentRepository sr, InstituteRepository is, EmployeeRepository er) {
    this.studentRepository = sr;
    this.instituteRepository = is;
    this.employeeRepository = er;
  }

  @Override
  @Transactional(readOnly = true)
  public Faculty findFacultyById(int id) {
    return instituteRepository.findFacultyById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Collection<Faculty> getFaculties() {
    return instituteRepository.getAllFaculties();
  }

  @Override
  @Transactional(readOnly = true)
  public Cathedra findCathedraById(int id) {
    return instituteRepository.findCathedraById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Collection<Employee> getFacultyEmployees(Set<Employee> employees, int facultyId) {
    return employeeRepository.getFacultyEmployees(employees, facultyId);
  }

  @Override
  @Transactional(readOnly = true)
  public Collection<Employee> getFacultySoviet(Set<Employee> employees, int facultyId) {
    return employeeRepository.getFacultySoviet(employees, facultyId);
  }

  @Override
  @Transactional(readOnly = true)
  public Collection<Employee> getCathedraLecturers(Set<Employee> employees, int cathedraId) {
    return employeeRepository.getCathedraLecturers(employees, cathedraId);
  }

  @Override
  @Transactional
  public void saveStudent(Student student) {
    studentRepository.save(student);
  }

  @Override
  @Transactional(readOnly = true)
  public Student findStudentById(int id) {
    return studentRepository.findById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Collection<Student> getStudents() {
    return studentRepository.getAllStudents();
  }

  @Override
  @Transactional(readOnly = true)
  public Employee findEmployeeById(int id) {
    return employeeRepository.findById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Collection<Employee> getEmployees() {
    return employeeRepository.getAllEmployees();
  }

  @Override
  @Transactional(readOnly = true)
  public Group_class findGroup_classById(int id) {
    return instituteRepository.findGroup_classById(id);
  }
}
