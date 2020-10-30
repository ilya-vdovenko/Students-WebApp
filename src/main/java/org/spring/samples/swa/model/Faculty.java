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

package org.spring.samples.swa.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

/**
 * Simple JavaBean domain object representing a faculty.
 *
 * @author Ilya Vdovenko
 */

@Entity
@Table(name = "faculties")
@JsonSerialize(using = UnitEntitySerializer.class)
public class Faculty extends UnitEntity {

  @OneToMany(mappedBy = "faculty", fetch = FetchType.EAGER)
  private Set<Cathedra> cathedras;

  @NotEmpty
  @OneToMany(mappedBy = "faculty", fetch = FetchType.EAGER)
  private Set<Employee> employees;

  public Set<Cathedra> getCathedras() {
    return cathedras;
  }

  public void setCathedras(Set<Cathedra> cathedras) {
    this.cathedras = cathedras;
  }

  public Set<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(Set<Employee> employees) {
    this.employees = employees;
  }

  @Override
  public String toString() {
    return "Faculty{" +
      "id=" + id +", " +
      ", title='" + super.getTitle() + '\'' +
      ", cathedras=" + cathedras +
      ", employees=" + employees +
      '}';
  }
}
