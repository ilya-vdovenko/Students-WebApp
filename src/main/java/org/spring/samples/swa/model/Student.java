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

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Simple JavaBean domain object representing a student.
 *
 * @author Ilya Vdovenko
 */

@Entity
@NotEmpty
@Table(name = "students")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Student extends Person {

  @Column(name = "birthday")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate birthday;

  @Column(name = "sex")
  private String sex;

  @Column(name = "actualAddress")
  private String actualAddress;

  @Column(name = "address")
  private String address;

  @Column(name = "telephone")
  @Digits(fraction = 0, integer = 10)
  private String telephone;

  @ManyToOne
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @Cascade(CascadeType.SAVE_UPDATE)
  @JoinColumn(name = "group_class_id")
  private GroupClass groupClass;

}
