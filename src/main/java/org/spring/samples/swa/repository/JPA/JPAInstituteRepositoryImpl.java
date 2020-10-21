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

package org.spring.samples.swa.repository.JPA;

import org.spring.samples.swa.model.Cathedra;
import org.spring.samples.swa.model.Faculty;
import org.spring.samples.swa.model.Group_class;
import org.spring.samples.swa.repository.InstituteRepository;
import org.spring.samples.swa.util.EntityUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A JPA implementation of the {@link InstituteRepository} interface.
 *
 * @author Ilya vdovenko
 **/

@Repository
public class JPAInstituteRepositoryImpl implements InstituteRepository {

  private final Map<Integer, Faculty> facultiesMap = new LinkedHashMap<>();
  private final Map<Integer, Cathedra> cathedrasMap = new LinkedHashMap<>();
  private final Map<Integer, Group_class> group_classesMap = new LinkedHashMap<>();

  @PersistenceContext
  private EntityManager em;

  @Override
  public Faculty findFacultyById(int id) {
    if (EntityUtils.isValidCollection(facultiesMap.values())) {
      if (facultiesMap.containsKey(id)) {
        return facultiesMap.get(id);
      }
    }
    Query query = this.em.createQuery("from Faculty as f where f.id =:id");
    query.setParameter("id", id);
    Faculty faculty = (Faculty) query.getSingleResult();
    facultiesMap.putIfAbsent(id, faculty);
    return faculty;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Collection<Faculty> findAllFaculties() {
    List<Faculty> facultyList = this.em.createQuery("from Faculty").getResultList();
    if (EntityUtils.isValidCollection(facultyList)) {
      EntityUtils.setEntityMaps(facultyList, facultiesMap, cathedrasMap, group_classesMap);
    }
    return facultyList;
  }

  @Override
  public Cathedra findCathedraById(int id) {
    if (EntityUtils.isValidCollection(cathedrasMap.values())) {
      if (cathedrasMap.containsKey(id)) {
        return cathedrasMap.get(id);
      }
    }
    Query query = this.em.createQuery("from Cathedra as c where c.id =:id");
    query.setParameter("id", id);
    Cathedra cathedra = (Cathedra) query.getSingleResult();
    cathedrasMap.putIfAbsent(id, cathedra);
    return cathedra;
  }

  @Override
  public Group_class findGroup_classById(int id) {
    if (EntityUtils.isValidCollection(group_classesMap.values())) {
      if (group_classesMap.containsKey(id)) {
        return group_classesMap.get(id);
      }
    }
    Query query = this.em.createQuery("from Group_class as gc where gc.id =:id");
    query.setParameter("id", id);
    Group_class group_class = (Group_class) query.getSingleResult();
    group_classesMap.putIfAbsent(id, group_class);
    return group_class;
  }

  @Override
  public Map<Integer, Faculty> getInternalFaculties() {
    return facultiesMap;
  }

  @Override
  public Map<Integer, Cathedra> getInternalCathedras() {
    return cathedrasMap;
  }

  @Override
  public Map<Integer, Group_class> getInternalGroup_classes() {
    return group_classesMap;
  }
}
