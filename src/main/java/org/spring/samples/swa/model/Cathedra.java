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
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

/**
 * Simple JavaBean domain object representing a cathedra.
 *
 * @author Ilya Vdovenko
 */

@Entity
@Table(name = "cathedras")
@JsonSerialize(using = UnitEntitySerializer.class)
public class Cathedra extends UnitEntity {

  @NotEmpty
  @Column(name = "edu_programs")
  private String eduPrograms;

  @NotEmpty
  @OneToMany(mappedBy = "cathedra", fetch = FetchType.EAGER)
  private Set<Employee> employees;

  @NotEmpty
  @ManyToOne
  @Cascade(CascadeType.SAVE_UPDATE)
  @JoinColumn(name = "faculty_id")
  private Faculty faculty;

  @OneToMany(mappedBy = "cathedra", fetch = FetchType.EAGER)
  private Set<Group_class> group_classes;

  public String getEduPrograms() {
    return eduPrograms;
  }

  public void setEduPrograms(String programs) {
    this.eduPrograms = programs;
  }

  public Set<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(Set<Employee> employees) {
    this.employees = employees;
  }

  public Faculty getFaculty() {
    return faculty;
  }

  public void setFaculty(Faculty faculty) {
    this.faculty = faculty;
  }

  public Set<Group_class> getGroup_classes() {
    return group_classes;
  }

  public void setGroup_classes(Set<Group_class> group_classes) {
    this.group_classes = group_classes;
  }

  @Override
  public String toString() {
    return "Cathedra{" +
      "id=" + id +
      ", eduPrograms='" + eduPrograms + '\'' +
      ", employees=" + employees +
      ", faculty id=" + faculty.getId() +
      ", group_classes=" + group_classes +
      '}';
  }
}
