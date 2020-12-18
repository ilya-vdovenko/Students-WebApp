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
import org.spring.samples.swa.model.Employee;
import org.spring.samples.swa.repository.EmployeeRepository;
import org.springframework.stereotype.Repository;

/**
 * A jpa implementation of the {@link EmployeeRepository} interface.
 *
 * @author Ilya vdovenko
 **/

@Repository
public class JpaEmployeeRepositoryImpl implements EmployeeRepository {


  @PersistenceContext
  private EntityManager em;

  @Override
  public Employee findById(int id) {
    Query query = this.em.createQuery("from Employee as e where e.id =:id");
    query.setParameter("id", id);
    return (Employee) query.getSingleResult();
  }

  @Override
  @SuppressWarnings("unchecked")
  public Collection<Employee> findAllByOrderByFioAsc() {
    return this.em.createQuery("from Employee Order by fio").getResultList();
  }

}
