package org.spring.samples.repository;

import org.spring.samples.model.Cathedra;
import org.spring.samples.model.Faculty;
import org.spring.samples.model.Group_class;

import java.util.Collection;
import java.util.Map;

/**
 * Repository class for Faculty, Cathedra, Group_class domain objects.
 **/

public interface InstituteRepository {

  Map<Integer, Faculty> getInternalFaculties();

  Map<Integer, Cathedra> getInternalCathedras();

  Map<Integer, Group_class> getInternalGroup_classes();

  Faculty findFacultyById(int id);

  Collection<Faculty> getAllFaculties();

  Cathedra findCathedraById(int id);

  Group_class findGroup_classById(int id);
}
