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
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Data Transfer Object for student model.
 *
 * @author Ilya Vdovenko
 */

public class StudentDto extends Person {

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate birthday;
  private String sex;
  private String factAddress;
  private String address;
  private String telephone;
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
}
