package org.spring.samples.service;

import org.spring.samples.model.Faculty;
import org.spring.samples.model.Student;
import org.spring.samples.repository.InstituteRepository;
import org.spring.samples.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class InstituteServiceImpl implements InstituteService {

    private StudentRepository studentRepository;
    private InstituteRepository instituteRepository;

    @Autowired
    public InstituteServiceImpl(StudentRepository sr, InstituteRepository is) {

        this.instituteRepository = is;
        this.studentRepository = sr;
    }

    @Override
    @Transactional
    public void saveStudent(Student student) throws DataAccessException {
        studentRepository.save(student);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Faculty> getFaculties() throws DataAccessException {
        return instituteRepository.getAllFaculties();
    }
}