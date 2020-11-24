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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.spring.samples.swa.model.Cathedra;
import org.spring.samples.swa.model.Faculty;
import org.spring.samples.swa.model.GroupClass;
import org.spring.samples.swa.model.Student;
import org.spring.samples.swa.repository.InstituteRepository;

/**
 * Class of useful methods for manipulating Entities.
 *
 * @author Ilya Vdovenko
 */

public class EntityUtils {

  private EntityUtils() {
    // Utility class
  }

  public static <E> boolean isValidCollection(Collection<E> collection) {
    return collection != null && !collection.isEmpty();
  }

  /**
   * Method for fill in Map variables.
   *
   * @param facultyList     contains all faculty
   * @param facultiesMap    contains faculty as value and Id as key.
   * @param cathedrasMap    contains cathedra as value and Id as key.
   * @param groupClassesMap contains groupClass as value and Id as key.
   */
  public static void setEntityMaps(List<Faculty> facultyList,
      Map<Integer, Faculty> facultiesMap,
      Map<Integer, Cathedra> cathedrasMap,
      Map<Integer, GroupClass> groupClassesMap) {

    facultiesMap.clear();
    cathedrasMap.clear();
    groupClassesMap.clear();

    for (Faculty faculty : facultyList) {
      facultiesMap.putIfAbsent(faculty.getId(), faculty);
      for (Cathedra cathedra : faculty.getCathedras()) {
        cathedrasMap.putIfAbsent(cathedra.getId(), cathedra);
        for (GroupClass groupClass : cathedra.getGroupClasses()) {
          groupClassesMap.putIfAbsent(groupClass.getId(), groupClass);
        }
      }
    }
  }

  /**
   * Method for cleaning internal variables when Student was saved.
   *
   * @param repo    {@link InstituteRepository} class.
   * @param student saved Student.
   */
  public static void clearAfterSetStudent(InstituteRepository repo,
      Student student) {
    repo.getInternalGroupClasses().remove(student.getGroupClass().getId());
    repo.getInternalCathedras().remove(student.getCathedra().getId());
    repo.getInternalFaculties().remove(student.getFaculty().getId());
  }

  /**
   * Method for fill in Student model. Reduce boilerplate code in extractors.
   *
   * @param rs         sql result set.
   * @param groupClass {@link GroupClass} class.
   * @param cathedra   {@link Cathedra} class.
   * @return {@link Student} object.
   * @throws SQLException data retrieval error.
   */
  public static Student fillInStudent(ResultSet rs, GroupClass groupClass, Cathedra cathedra)
      throws SQLException {
    Student student = new Student();
    student.setId(rs.getInt("studentID"));
    student.setFio(rs.getString("fio"));
    student.setBirthday(rs.getObject("birthday", LocalDate.class));
    student.setSex(rs.getString("sex"));
    student.setFactAddress(rs.getString("fact_address"));
    student.setAddress(rs.getString("address"));
    student.setTelephone(rs.getString("telephone"));
    student.setGroupClass(groupClass);
    student.setCathedra(cathedra);
    return student;
  }

}
