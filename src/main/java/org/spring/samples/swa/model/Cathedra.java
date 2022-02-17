/*
 * Copyright 2019-2021, Ilya Vdovenko and the Students-WebApp contributors.
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
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * Simple JavaBean domain object representing a cathedra.
 *
 * @author Ilya Vdovenko
 */

@Entity
@Table(name = "cathedras")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
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
  @ToString.Exclude
  @Cascade(CascadeType.SAVE_UPDATE)
  @JoinColumn(name = "faculty_id")
  private Faculty faculty;

  @OneToMany(mappedBy = "cathedra", fetch = FetchType.EAGER)
  private Set<GroupClass> groupClasses;

}
