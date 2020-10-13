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

package org.spring.samples.repository.SpringDataJpa;

import org.spring.samples.model.Employee;
import org.spring.samples.repository.EmployeeRepository;
import org.springframework.data.repository.Repository;

/**
 * Spring Data JPA specialization of the {@link EmployeeRepository} interface
 *
 * @author Ilya Vdovenko
 */

public interface SpringDataEmployeeRepository extends EmployeeRepository, Repository<Employee, Integer> {
}
