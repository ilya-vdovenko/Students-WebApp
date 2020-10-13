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

package org.spring.samples.repository.JPA;

import org.spring.samples.model.Student;
import org.spring.samples.repository.InstituteRepository;
import org.spring.samples.repository.StudentRepository;
import org.spring.samples.util.EntityUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A JPA implementation of the {@link StudentRepository} interface.
 *
 * @author Ilya vdovenko
 **/

@Repository
public class JPAStudentRepositoryImpl implements StudentRepository {

  private final InstituteRepository instituteRepo;
  private final Map<Integer, Student> studentsMap = new LinkedHashMap<>();
  @PersistenceContext
  private EntityManager em;

  public JPAStudentRepositoryImpl(@Qualifier("JPAInstituteRepositoryImpl") InstituteRepository instituteRepo) {
    this.instituteRepo = instituteRepo;
  }

  @Override
  @SuppressWarnings("unchecked")
  public Collection<Student> findAll() {
    List<Student> studentList = this.em.createQuery("from Student").getResultList();
    studentsMap.clear();
    if (EntityUtils.isValidCollection(studentList)) {
      for (Student student : studentList) {
        studentsMap.putIfAbsent(student.getId(), student);
      }
    }
    return studentList;
  }

  @Override
  public void save(Student student) {
    if (student.getId() == null) {
      this.em.persist(student);
    } else {
      this.em.merge(student);
    }
    studentsMap.replace(student.getId(), student);
    EntityUtils.clearAfterSetStudent(instituteRepo, student);
  }

  @Override
  public Student findById(int id) {
    if (EntityUtils.isValidCollection(studentsMap.values())) {
      if (studentsMap.containsKey(id)) {
        return studentsMap.get(id);
      }
    }
    Query query = this.em.createQuery("from Student as s where s.id =:id");
    query.setParameter("id", id);
    Student student = (Student) query.getSingleResult();
    studentsMap.putIfAbsent(id, student);
    return student;
  }
}
