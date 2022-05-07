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
import org.spring.samples.swa.model.Cathedra;
import org.spring.samples.swa.model.GroupClass;
import org.spring.samples.swa.model.Student;
import org.springframework.cache.ehcache.EhCacheCacheManager;

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
    student.setFullName(rs.getString("fullName"));
    student.setBirthday(rs.getObject("birthday", LocalDate.class));
    student.setSex(rs.getString("sex"));
    student.setActualAddress(rs.getString("actualAddress"));
    student.setAddress(rs.getString("address"));
    student.setTelephone(rs.getString("telephone"));
    student.setGroupClass(groupClass);
    student.setCathedra(cathedra);
    return student;
  }

  public static void evictAllCaches(EhCacheCacheManager cacheManager) {
    cacheManager.getCacheNames().stream()
      .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
  }

}
