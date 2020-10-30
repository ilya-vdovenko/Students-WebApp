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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * Simple JavaBean domain object representing an employee.
 *
 * @author Ilya Vdovenko
 **/

@Entity
@Table(name = "employees")
public class Employee extends Person {

  @NotEmpty
  @Column(name = "position")
  private String position;

  @Column(name = "degree")
  private String degree;

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public String getDegree() {
    return degree;
  }

  public void setDegree(String degree) {
    this.degree = degree;
  }

  @Override
  public String toString() {
    return "Employee{" +
      "id=" + id +
      ", position='" + position + '\'' +
      ", degree='" + degree + '\'' +
      ", faculty id=" + id +
      ", cathedra id=" + id +
      '}';
  }
}
