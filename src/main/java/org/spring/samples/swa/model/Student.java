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

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
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
public class Student extends Person {

  @Column(name = "birthday")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate birthday;

  @Column(name = "sex")
  private String sex;

  @Column(name = "fact_address")
  private String factAddress;

  @Column(name = "address")
  private String address;

  @Column(name = "telephone")
  @Digits(fraction = 0, integer = 10)
  private String telephone;

  @ManyToOne
  @Cascade(CascadeType.SAVE_UPDATE)
  @JoinColumn(name = "group_class_id")
  private GroupClass groupClass;

  public LocalDate getBirthday() {
    return birthday;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getFactAddress() {
    return factAddress;
  }

  public void setFactAddress(String factAddress) {
    this.factAddress = factAddress;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public GroupClass getGroupClass() {
    return groupClass;
  }

  public void setGroupClass(GroupClass groupClass) {
    this.groupClass = groupClass;
  }

  @Override
  public String toString() {
    return "Student{"
        + "id=" + id
        + ", birthday=" + birthday
        + ", sex='" + sex + '\''
        + ", factAddress='" + factAddress + '\''
        + ", address='" + address + '\''
        + ", telephone='" + telephone + '\''
        + ", groupClass id=" + groupClass.getId()
        + ", cathedra id=" + getCathedra().getId()
        + ", faculty id=" + getFaculty().getId()
        + '}';
  }
}
