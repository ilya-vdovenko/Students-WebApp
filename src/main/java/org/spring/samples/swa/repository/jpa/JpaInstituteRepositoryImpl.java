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

package org.spring.samples.swa.repository.jpa;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.spring.samples.swa.model.Cathedra;
import org.spring.samples.swa.model.Faculty;
import org.spring.samples.swa.model.GroupClass;
import org.spring.samples.swa.repository.InstituteRepository;
import org.springframework.stereotype.Repository;

/**
 * A jpa implementation of the {@link InstituteRepository} interface.
 *
 * @author Ilya vdovenko
 **/

@Repository
public class JpaInstituteRepositoryImpl implements InstituteRepository {

  @PersistenceContext
  private EntityManager em;

  @Override
  public Faculty findFacultyById(int id) {
    Query query = this.em.createQuery("from Faculty as f where f.id =:id");
    query.setParameter("id", id);
    return (Faculty) query.getSingleResult();
  }

  @Override
  @SuppressWarnings("unchecked")
  public Collection<Faculty> findAllByOrderByTitleAsc() {
    return this.em.createQuery("from Faculty Order by title").getResultList();
  }

  @Override
  public Cathedra findCathedraById(int id) {
    Query query = this.em.createQuery("from Cathedra as c where c.id =:id");
    query.setParameter("id", id);
    return (Cathedra) query.getSingleResult();
  }

  @Override
  public GroupClass findGroupClassById(int id) {
    Query query = this.em.createQuery("from GroupClass as gc where gc.id =:id");
    query.setParameter("id", id);
    return (GroupClass) query.getSingleResult();
  }
}
