package org.spring.samples.repository;

import org.spring.samples.model.Student;

import java.util.Collection;

/**
 * Repository class for Student domain objects.
 **/

public interface StudentRepository {

  void save(Student student);

  Collection<Student> findAll();

  Student findById(int id);
}
