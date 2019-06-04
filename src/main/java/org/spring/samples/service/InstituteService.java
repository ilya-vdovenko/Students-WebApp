package org.spring.samples.service;

import org.spring.samples.model.Faculty;
import org.spring.samples.model.Student;
import org.springframework.dao.DataAccessException;

import java.util.Collection;

public interface InstituteService {

    void saveStudent(Student student) throws DataAccessException;

    Collection<Faculty> getFaculties() throws DataAccessException;
}