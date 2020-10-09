package org.spring.samples.repository.SpringDataJpa;

import org.spring.samples.model.Student;
import org.spring.samples.repository.StudentRepository;
import org.springframework.data.repository.Repository;

public interface SpringDataStudentRepository extends StudentRepository, Repository<Student, Integer> {
}
