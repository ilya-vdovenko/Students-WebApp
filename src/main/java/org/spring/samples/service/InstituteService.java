package org.spring.samples.service;

import org.spring.samples.model.*;
import org.springframework.dao.DataAccessException;

import java.util.Collection;

public interface InstituteService {

    Faculty findFacultyById(int id) throws DataAccessException;

    Collection<Faculty> getFaculties() throws DataAccessException;

    Cathedra findCathedraById(int cathedraId) throws DataAccessException;

    //Collection<Cathedra> getCathedras(int facultyId) throws DataAccessException;

    void saveStudent(Student student) throws DataAccessException;

    Student findStudentById(int id) throws DataAccessException;

    Collection<Student> getStudents() throws DataAccessException;

    Employee findEmployeeById(int id) throws DataAccessException;

    Collection<Employee> getEmployees() throws DataAccessException;

    Group_class findGroup_classById(int id) throws DataAccessException;
}