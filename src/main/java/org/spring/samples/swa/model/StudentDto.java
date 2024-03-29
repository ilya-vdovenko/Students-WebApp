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

import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Data Transfer Object for student model.
 *
 * @author Ilya Vdovenko
 */

@ToString
@NoArgsConstructor
public class StudentDto extends Student {

  /**
   * Constructor that initialise this object by student object.
   *
   * @param student object of {@link Student} class.
   */
  public StudentDto(Student student) {
    this.setId(student.getId());
    this.setFullName(student.getFullName());
    this.setSex(student.getSex());
    this.setBirthday(student.getBirthday());
    this.setActualAddress(student.getActualAddress());
    this.setAddress(student.getAddress());
    this.setTelephone(student.getTelephone());
    this.setFaculty(student.getFaculty());
    this.setCathedra(student.getCathedra());
    this.setGroupClass(student.getGroupClass());
  }

}
