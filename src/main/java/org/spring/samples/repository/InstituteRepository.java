package org.spring.samples.repository;

import org.spring.samples.model.Cathedra;
import org.spring.samples.model.Faculty;
import org.spring.samples.model.Group_class;
import org.springframework.dao.DataAccessException;

import java.util.Collection;

/**
 *Repository class for Faculty, Cathedra, Group_class domain objects.
 *
 **/

public interface InstituteRepository {

    Faculty findFacultyById(int id) throws DataAccessException;

    Collection<Faculty> getAllFaculties() throws DataAccessException;

    Cathedra findCathedraById(int cathedraId) throws DataAccessException;

    Group_class findGroup_classById(int id) throws DataAccessException;
}