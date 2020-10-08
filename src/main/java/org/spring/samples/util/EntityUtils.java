package org.spring.samples.util;

import org.spring.samples.model.BaseEntity;
import org.spring.samples.model.Cathedra;
import org.spring.samples.model.Faculty;
import org.spring.samples.model.Group_class;
import org.spring.samples.model.Student;
import org.spring.samples.repository.InstituteRepository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class EntityUtils {

  private EntityUtils() {
    // Utility class
  }

  public static boolean equalsLoad(BaseEntity obj, int id) {
    return obj != null && obj.getId().equals(id);
  }

  public static <E> boolean isValidCollection(Collection<E> collection) {
    return collection != null && !collection.isEmpty();
  }

  public static void setEntityMaps(List<Faculty> facultyList,
                                   Map<Integer, Faculty> facultiesMap,
                                   Map<Integer, Cathedra> cathedrasMap,
                                   Map<Integer, Group_class> group_classesMap) {

    facultiesMap.clear();
    cathedrasMap.clear();
    group_classesMap.clear();

    for (Faculty faculty : facultyList) {
      facultiesMap.putIfAbsent(faculty.getId(), faculty);
      for (Cathedra cathedra : faculty.getCathedras()) {
        cathedrasMap.putIfAbsent(cathedra.getId(), cathedra);
        for (Group_class group_class : cathedra.getGroup_classes()) {
          group_classesMap.putIfAbsent(group_class.getId(), group_class);
        }
      }
    }
  }

  public static void clearAfterSetStudent(InstituteRepository repo,
                                          Student student) {
    repo.getInternalGroup_classes().remove(student.getGroup_class().getId());
    repo.getInternalCathedras().remove(student.getCathedra().getId());
    repo.getInternalFaculties().remove(student.getFaculty().getId());
  }

}
