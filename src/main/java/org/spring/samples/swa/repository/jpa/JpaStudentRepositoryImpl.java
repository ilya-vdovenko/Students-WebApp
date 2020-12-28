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
import org.spring.samples.swa.model.Student;
import org.spring.samples.swa.repository.StudentRepository;
import org.springframework.stereotype.Repository;

/**
 * A jpa implementation of the {@link StudentRepository} interface.
 *
 * @author Ilya vdovenko
 **/

@Repository
public class JpaStudentRepositoryImpl implements StudentRepository {

  @PersistenceContext
  private EntityManager em;

  @Override
  @SuppressWarnings("unchecked")
  public Collection<Student> findAllByOrderByFullNameAsc() {
    return this.em.createQuery("from Student Order by fullName").getResultList();
  }

  @Override
  public void save(Student student) {
    if (student.getId() == null) {
      this.em.persist(student);
    } else {
      this.em.merge(student);
    }
  }

  @Override
  public Student findById(int id) {
    Query query = this.em.createQuery("from Student as s where s.id =:id");
    query.setParameter("id", id);
    return (Student) query.getSingleResult();
  }
}
