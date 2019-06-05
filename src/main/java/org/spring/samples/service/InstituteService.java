package org.spring.samples.service;

import org.spring.samples.model.Cathedra;
import org.spring.samples.model.Employee;
import org.spring.samples.model.Faculty;
import org.spring.samples.model.Student;
import org.springframework.dao.DataAccessException;

import java.util.Collection;

public interface InstituteService {

    Faculty findFacultyById(int id) throws DataAccessException;

    Collection<Faculty> getFaculties() throws DataAccessException;

    Cathedra findCathedraById(int facultyId, int cathedraId) throws DataAccessException;

    Collection<Cathedra> getCathedras(int facultyId) throws DataAccessException;

    void saveStudent(Student student) throws DataAccessException;

    Student findStudentById(int id) throws DataAccessException;

    Collection<Student> getStudents() throws DataAccessException;

    Employee findEmployeeById(int id) throws DataAccessException;

    Collection<Employee> getEmployees() throws DataAccessException;
}