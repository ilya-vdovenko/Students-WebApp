package org.spring.samples.repository.JDBC;

import org.spring.samples.model.Cathedra;
import org.spring.samples.model.Faculty;
import org.spring.samples.model.Group_class;
import org.spring.samples.repository.InstituteRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class JDBCInstituteRepositoryImpl implements InstituteRepository {

  @Override
  public Faculty findFacultyById(int id) throws DataAccessException {
    return null;
  }

  @Override
  public Collection<Faculty> getAllFaculties() throws DataAccessException {
    return null;
  }

  @Override
  public Cathedra findCathedraById(int cathedraId) throws DataAccessException {
    return null;
  }

  @Override
  public Group_class findGroup_classById(int id) throws DataAccessException {
    return null;
  }
}
