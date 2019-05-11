package org.spring.samples.repository;

import org.spring.samples.model.Cathedra;
import org.spring.samples.model.Faculty;
import org.springframework.dao.DataAccessException;

import java.util.Collection;

public interface InstituteRepository {

    Faculty findById(int id) throws DataAccessException;

    Collection<Faculty> getAllFaculties() throws DataAccessException;

    Cathedra findCathedraById(int facultyId, int cathedraId) throws DataAccessException;

    Collection<Cathedra> getAllCathedras(int facultyId) throws DataAccessException;
}
