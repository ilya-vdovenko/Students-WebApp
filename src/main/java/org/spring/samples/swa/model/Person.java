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

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * Simple JavaBean domain object adds a multiple property to {@link BaseEntity}. Used as a base
 * class for objects needing these properties.
 *
 * @author Ilya Vdovenko
 */

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Person extends BaseEntity {

  @NotEmpty
  @Column(name = "fullName")
  private String fullName;

  @NotEmpty
  @ManyToOne
  @EqualsAndHashCode.Exclude
  @Cascade(CascadeType.SAVE_UPDATE)
  @JoinColumn(name = "faculty_id")
  private Faculty faculty;

  @ManyToOne
  @EqualsAndHashCode.Exclude
  @Cascade(CascadeType.SAVE_UPDATE)
  @JoinColumn(name = "cathedra_id")
  private Cathedra cathedra;

}
