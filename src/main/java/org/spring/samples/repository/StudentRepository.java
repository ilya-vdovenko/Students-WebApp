package org.spring.samples.repository;

import org.spring.samples.model.Student;
import org.springframework.dao.DataAccessException;

import java.util.Collection;

/**
 *Repository class for Student domain objects.
 *
 **/

public interface StudentRepository {

    void save(Student student) throws DataAccessException;

    Collection<Student> getAllStudents() throws DataAccessException;

    Student findById(int id) throws DataAccessException;
}