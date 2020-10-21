/*
 * Copyright 2019-2020, Ilya Vdovenko and the Students-WebApp contributors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.spring.samples.swa.util;

import org.spring.samples.swa.model.BaseEntity;
import org.spring.samples.swa.model.Cathedra;
import org.spring.samples.swa.model.Faculty;
import org.spring.samples.swa.model.Group_class;
import org.spring.samples.swa.model.Student;
import org.spring.samples.swa.repository.InstituteRepository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Class of useful methods for manipulating Entities
 *
 * @author Ilya Vdovenko
 */

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
